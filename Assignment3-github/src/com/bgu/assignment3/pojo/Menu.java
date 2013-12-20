package com.bgu.assignment3.pojo;

import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Menu")
@XmlAccessorType(XmlAccessType.FIELD)
public class Menu {

	String name;
	@XmlElementWrapper(name="Shit")
	@XmlElement(name = "KitchenTool")
	Vector<KitchenTool> kitchenTool;
	
	@XmlElement(name = "Dishes")
	Dishes dishes;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

/*	private Dishes dishes;

	public Dishes getDishes() {
		return dishes;
	}
	
	
	public void setDishes(Dishes dishes) {
		this.dishes = dishes;
	}*/


}
