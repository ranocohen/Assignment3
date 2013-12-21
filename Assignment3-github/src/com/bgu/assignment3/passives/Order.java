package com.bgu.assignment3.passives;

import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {

	
	private enum Status { 
		INCOMPLETE , IN_PROGRESS , COMPLETE , DELIVERED 
	}
	
	@XmlAttribute(name="id")
	private long id;
	
	private double difficulty;
	private Status status;
	
	@XmlElement(name = "DeliveryAddress")
	private Address deliveryAddress;
	
	@XmlElementWrapper(name="Dishes")
	@XmlElement(name = "Dish")
	private Vector<OrderOfDish> dishes;
	
	
}
