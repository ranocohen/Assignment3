package com.bgu.assignment3.passives;

import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {

	
	public void setDishes(Vector<OrderOfDish> dishes) {
		this.dishes = dishes;
	}

	public Vector<OrderOfDish> getDishes() {
		return dishes;
	}

	private enum Status { 
		INCOMPLETE , IN_PROGRESS , COMPLETE , DELIVERED 
	}
	
	@XmlAttribute(name="id")
	private long id;
	
	private int difficulty;
	private Status status;
	
	@XmlElement(name = "DeliveryAddress")
	private Address deliveryAddress;
	
	@XmlElementWrapper(name="Dishes")
	@XmlElement(name = "Dish")
	private Vector<OrderOfDish> dishes;
	
	public void calcOrderDifficulty() {
		for (int i = 0; i < dishes.size(); i++){
			this.difficulty += dishes.get(i).getDish().getDifficulty();
		}
	}
	
	
	
	
}
