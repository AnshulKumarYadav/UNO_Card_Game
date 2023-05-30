package com.app;

public class Card {
	private Integer value;
	private Color color;
	private Action action;
	
	public Card() {}
	
	public Card(Integer value, Color color, Action action) {
		super();
		this.value = value;
		this.color = color;
		this.action = action;
	}
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "Card [value=" + value + ", color=" + color + ", action=" + action + "]";
	}
	
	
	
	

}
