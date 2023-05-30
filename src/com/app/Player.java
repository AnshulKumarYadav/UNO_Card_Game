package com.app;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private Integer playerId;
	private String playerName;
	private List<Card> cards = new ArrayList<>();
	
	public Player() {
		// TODO Auto-generated constructor stub
	}

	public Player(Integer playerId, String playerName) {
		super();
		this.playerId = playerId;
		this.playerName = playerName;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", playerName=" + playerName + ", cards=" + cards + "]";
	}
	
	

}
