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
	
	
}
