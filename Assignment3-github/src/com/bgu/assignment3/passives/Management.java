package com.bgu.assignment3.passives;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Restaurant")
public class Management {
	
	@XmlElement(name = "Repository")
	private Warehouse warehouse;
	
	@XmlElement(name = "Address")
	private Address address;
	
	@XmlElement(name = "Staff")
	private Staff staff;

	private Orders orders;
	private Menu menu;
	public Management() {
		
	}


	public void addOrders(Orders orders) {
		this.orders = orders;	
	}
	public void addMenu(Menu menu) {
		this.menu = menu;	
	}
	
	public void simulate() {
		orders.getNextOrder();
	}
	

}
