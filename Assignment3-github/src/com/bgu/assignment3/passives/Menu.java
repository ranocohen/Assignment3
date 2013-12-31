package com.bgu.assignment3.passives;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.bgu.assignment3.FancyStringBuilder;


@XmlRootElement(name = "Menu")
@XmlAccessorType(XmlAccessType.FIELD)
public class Menu {

	@XmlElementWrapper(name="Dishes")
	@XmlElement(name = "Dish")
	private ArrayList<Dish> dishes;

	public Dish getDishByName(String dishName) {
		for (int i = 0; i < dishes.size(); i++) {
			if(dishes.get(i).getName().equals(dishName))
				return dishes.get(i);
		}
		return null;
	}

	@Override
	public String toString() {
		FancyStringBuilder builder = new FancyStringBuilder();
		
		for(Dish dish : dishes)
			builder.append(dish.toString());
		
		return builder.toString();

	}
}
