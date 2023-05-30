package com.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Game {

	private List<Player> players;
	private Deck deck = new Deck();
	private Player currPlayer;
	private boolean isGameEnded = false;

	public void createPlayers(List<String> playerNames) {
		players = new ArrayList<>();
		for (int i = 0; i < playerNames.size(); i++) {
			players.add(new Player(i + 1, playerNames.get(i)));
		}
	}

	public void startGame() {
		deck = new Deck(deck.defineCards());
		distributeCards();
		playGame();
	}

	public void distributeCards() {
		int i = 0;
		int count = 0;
		List<Card> distributedCards = new ArrayList<>();
		for (Card c : deck.getCards()) {
			count++;
			Player player = players.get(i);
			player.getCards().add(c);
			distributedCards.add(c);
			if (count == 7 * players.size())
				break;
			if (i == players.size() - 1)
				i = 0;
			else
				i++;
		}
		List<Card> allCards = deck.getCards();
		allCards.removeAll(distributedCards);
		deck.setCards(allCards);
	}

	public Card drawCard() {
		List<Card> cards = deck.getCards();
		if (!cards.isEmpty()) {
			return cards.remove(cards.size() - 1);
		} else {
			System.out.println("Deck is empty");
			return null;
		}
	}

	public void playGame() {
		currPlayer = players.get(0);
		while (!isGameOver()) {
			Card card = drawCard();
			handleTurn(card);
		}
		declareWinner();
	}
	
	public void handleTurn(Card card) {
	    if (card == null) return;
	    Scanner scanner = new Scanner(System.in);
	    System.out.println(currPlayer.getPlayerName() + "'s turn");
	    System.out.println("Current card: " + card);

	    System.out.println(currPlayer.getPlayerName()+" cards:");
	    System.out.println("***************************");
	    int cardIndex = 1;
	    for (Card c : currPlayer.getCards()) {
	        System.out.println(cardIndex + " ---> " + c);
	        cardIndex++;
	    }
	    System.out.println("***************************");
	    System.out.println();
	    System.out.print("Choose a card number to play (or enter 0 to draw a card): ");
	    int choice = scanner.nextInt();
	    if (choice == 0) {
	        System.out.println(currPlayer.getPlayerName() + " couldn't play a card. Drawing a card...");
	        Card drawnCard = drawCard();
	        if (drawnCard != null) {
	            currPlayer.getCards().add(drawnCard);
	            System.out.println(currPlayer.getPlayerName() + " drew a card: " + drawnCard);
	        }
	    } else if (choice >= 1 && choice <= currPlayer.getCards().size()) {
	        Card selectedCard = currPlayer.getCards().get(choice - 1);
	        if (selectedCard.getValue().equals(card.getValue()) || selectedCard.getColor().equals(card.getColor())) {
	            currPlayer.getCards().remove(selectedCard);
	            System.out.println(currPlayer.getPlayerName() + " played " + selectedCard);
	            handleCardAction(selectedCard);
	        } else {
	            System.out.println("Invalid card selection. Try again.");
	            handleTurn(card);
	        }
	    } else {
	        System.out.println("Invalid choice. Try again.");
	        handleTurn(card);
	    }

	    currPlayer = getNextPlayer();
	}


	public void handleCardAction(Card card) {
		if(deck==null) return;
		if(card==null) return;
		Action action = card.getAction();
		if (action == Action.Normal) {

		} else if (action == Action.Skip) {
			currPlayer = getNextPlayer();
			System.out.println(currPlayer.getPlayerName() + "'s turn is skipped!");
		} else if (action == Action.Reverse) {
			Collections.reverse(players);
			System.out.println("Direction of play is reversed!");
		} else if (action == Action.DrawTwo) {
			Player nextPlayer = getNextPlayer();
			Card drawnCard1 = drawCard();
			Card drawnCard2 = drawCard();
			if (drawnCard1 != null && drawnCard2 != null) {
				nextPlayer.getCards().add(drawnCard1);
				nextPlayer.getCards().add(drawnCard2);
				System.out.println(nextPlayer.getPlayerName() + " drew two cards: " + drawnCard1 + ", " + drawnCard2);
				currPlayer = getNextPlayer();
				System.out.println(currPlayer.getPlayerName() + "'s turn is skipped!");
			}
		} else if (action == Action.Wild) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter what you want to do for wild ");
			System.out.println(currPlayer.toString());
			System.out.println("Enter 1 for Change the card color ");
			System.out.println("Enter 2 for skip");
			System.out.println("Enter 3 for draw two");
			int q = scanner.nextInt();
			if (q == 1) {
				System.out.println("Enter the card you want to change color");
				System.out.println("Value: ");
				int v = scanner.nextInt();
				System.out.println("Color: ");
				String colorString = scanner.next();
				System.out.println("Action: ");
				String actionString = scanner.next();
				Optional<Card> cardOptional = currPlayer.getCards().stream().filter(p -> p.getValue() == v
						&& p.getAction().equals(Action.valueOf(actionString)) && p.getColor().equals(Color.valueOf(colorString))).findAny();
				if (cardOptional.isPresent()) {
					List<Card> currCards = currPlayer.getCards();
					currCards.remove(cardOptional.get());
					currPlayer.setCards(currCards);
				} else
					System.out.println("Card you want to change is not present in your card list");
			} else if (q == 2) {
				currPlayer = getNextPlayer();
				System.out.println(currPlayer.getPlayerName() + "'s turn is skipped!");
			} else if (q == 3) {
				Player nextPlayer = getNextPlayer();
				Card drawnCard1 = drawCard();
				Card drawnCard2 = drawCard();
				if (drawnCard1 != null && drawnCard2 != null) {
					nextPlayer.getCards().add(drawnCard1);
					nextPlayer.getCards().add(drawnCard2);
					System.out
							.println(nextPlayer.getPlayerName() + " drew two cards: " + drawnCard1 + ", " + drawnCard2);
					currPlayer = getNextPlayer();
					System.out.println(currPlayer.getPlayerName() + "'s turn is skipped!");
				}
			}

		} else if (action == Action.WildDrowFour) {
			Player nextPlayer = getNextPlayer();
			Card drawnCard1 = drawCard();
			Card drawnCard2 = drawCard();
			Card drawnCard3 = drawCard();
			Card drawnCard4 = drawCard();
			if (drawnCard1 != null && drawnCard2 != null && drawnCard3 != null && drawnCard4 != null) {
				nextPlayer.getCards().add(drawnCard1);
				nextPlayer.getCards().add(drawnCard2);
				nextPlayer.getCards().add(drawnCard3);
				nextPlayer.getCards().add(drawnCard4);
				System.out.println(nextPlayer.getPlayerName() + " drew four cards: " + drawnCard1 + ", " + drawnCard2
						+ ", " + drawnCard3 + ", " + drawnCard4);
				currPlayer = getNextPlayer();
				System.out.println(currPlayer.getPlayerName() + "'s turn is skipped!");
			}
		}
	}

	public boolean isGameOver() {
		for (Player player : players) {
			if (player.getCards().isEmpty()) {
				isGameEnded = true;
				return true;
			}
		}
		return false;
	}

	public void declareWinner() {
		int minScore = Integer.MAX_VALUE;
		Player winner = null;
		for (Player player : players) {
			int score = calculateScore(player);
			if (score < minScore) {
				minScore = score;
				winner = player;
			}
		}
		System.out.println("Game over! The winner is " + winner.getPlayerName());
	}

	private int calculateScore(Player player) {
		int score = 0;
		for (Card card : player.getCards()) {
			if (card.getAction() == Action.Normal) {
				score += card.getValue();
			} else if (card.getAction() == Action.DrawTwo || card.getAction() == Action.Reverse
					|| card.getAction() == Action.Skip) {
				score += 20;
			} else if (card.getAction() == Action.Wild || card.getAction() == Action.WildDrowFour) {
				score += 50;
			}
		}
		return score;
	}

	private Player getNextPlayer() {
		int currentIndex = players.indexOf(currPlayer);
		int nextIndex = (currentIndex + 1) % players.size();
		return players.get(nextIndex);
	}

}
