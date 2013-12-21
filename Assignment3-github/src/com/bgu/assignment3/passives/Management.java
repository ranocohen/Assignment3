package com.bgu.assignment3.passives;

import java.util.Vector;

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
		calcDifficulty();
		System.out.println(this);
		
		orders.getNextOrder();
	}
	
	public void calcDifficulty() {
		// first copies the dishes object for ease of calculation (and future use)
		for (int i = 0; i < orders.getOrders().size(); i++) {
			Vector<OrderOfDish> temp = orders.getOrders().get(i).getDishes(); //dishes to update 
			for (int j = 0; j < temp.size(); j++) {
				temp.get(j).setDish(menu.getDishByName(temp.get(j).getDishName()));
			}
			orders.getOrders().get(i).setDishes(temp);
		}
		//now we can calc each orders' difficulty
		for (int i = 0; i < orders.getOrders().size(); i++) {
			orders.getOrders().get(i).calcOrderDifficulty();
		}
	}
	private void cookDish(Order order) {
		
	}

}
