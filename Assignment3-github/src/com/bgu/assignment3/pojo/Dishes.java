package com.bgu.assignment3.pojo;

import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)
public class Dishes {
	
	@XmlElement(name="Dish")
	Vector<Dish> dishes;

	public Vector<Dish> getDishesA() {
		return dishes;
	}

	public void setDishes(Vector<Dish> dishes) {
		this.dishes = dishes;
	}
}
