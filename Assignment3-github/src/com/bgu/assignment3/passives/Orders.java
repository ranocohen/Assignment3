package com.bgu.assignment3.passives;

import java.util.Vector;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OrderList")
public class Orders {

	@XmlElementWrapper(name="Orders")
	@XmlElement(name = "Order")
	private Vector<Order> orders;

	public void getNextOrder() {
		
		
	}
	
}
