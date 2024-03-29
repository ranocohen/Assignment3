package com.bgu.assignment3.passives;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrderOfDish {

	@XmlElement(name = "quantity")
	private int quantity;
	@XmlElement(name = "name")
	private String dishName;
	private Dish dish;

	public Dish getDish() {
		return dish;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(dishName).append(" ").append(quantity);
		return builder.toString();
	}
}
