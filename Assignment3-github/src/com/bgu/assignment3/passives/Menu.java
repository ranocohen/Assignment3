package com.bgu.assignment3.passives;

import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Menu")
@XmlAccessorType(XmlAccessType.FIELD)
public class Menu {
	
	
	@XmlElementWrapper(name="Dishes")
	@XmlElement(name = "Dish")
	private Vector<Dish> dishes;
	
	 

	 @Override
	public String toString() {
		 String ans = "";
		for(Dish d : dishes) 
			ans += d.toString() +" \n ------------------------------------------ \n";
		
		return ans;
	}
}
