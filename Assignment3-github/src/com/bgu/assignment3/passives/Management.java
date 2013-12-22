package com.bgu.assignment3.passives;

import java.util.Vector;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

import com.bgu.assignment3.Driver;

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
		//first we calculate the difficulties
		orders.calcDifficulty(menu);
		staff.sortChefs();
		Order next = orders.getNextOrder();
		cookDish(next);
		Logger.getLogger(Management.class).fatal("Fatal-2");
		
	}
	
	
	private void cookDish(Order order) {
		
	}

}
