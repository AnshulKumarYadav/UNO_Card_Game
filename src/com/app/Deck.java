package com.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	private List<Card> cards = new ArrayList<>();
	
	public Deck() {
		// TODO Auto-generated constructor stub
	}

	public Deck(List<Card> cards) {
		super();
		this.cards = cards;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	
	
	@Override
	public String toString() {
		return "Deck [cards=" + cards + "]";
	}
	
	public void suffle(List<Card> cards)
	{
		Collections.shuffle(cards);
	}

	public List<Card> defineCards()
	{
		cards = new ArrayList<>();
		 
//		 Adding 0 for each color
		 
		 cards.add(new Card(0,Color.Blue,Action.Normal));
		 cards.add(new Card(0,Color.Red,Action.Normal));
		 cards.add(new Card(0,Color.Yellow,Action.Normal));
		 cards.add(new Card(0,Color.Green,Action.Normal));
		 
//		 Adding 1 to 9 for everyColor
		 for(int i=1;i<10;i++)
		 {
			 for(int j=0;j<2;j++)
			 {
				 cards.add(new Card(i,Color.Blue,Action.Normal));
				 cards.add(new Card(i,Color.Red,Action.Normal));
				 cards.add(new Card(i,Color.Yellow,Action.Normal));
				 cards.add(new Card(i,Color.Green,Action.Normal));				 
			 }
		 }
		 
//		 Adding special action cards
		 
		 for(Action a: Action.values())
		 {
			 if(a==Action.Skip || a==Action.Reverse || a==Action.DrawTwo)
			 {
				 for(int i=10;i<12;i++)
				 {
					 cards.add(new Card(i,Color.Blue,a));
					 cards.add(new Card(i,Color.Red,a));
					 cards.add(new Card(i,Color.Yellow,a));
					 cards.add(new Card(i,Color.Green,a)); 			 				 
				 }
			 }
			 else if(a==Action.Wild || a==Action.WildDrowFour)
			 {
				 for(int i=13;i<17;i++)
				 {					 
					 cards.add(new Card(i,Color.Black,a));
				 }
			 }
		 }
		 suffle(cards);
		 return cards;
		 
	}
	
	

}
