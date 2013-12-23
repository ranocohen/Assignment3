package com.bgu.assignment3.passives;

import java.util.Iterator;
import java.util.Vector;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OrderList")
public class Orders {

	public Vector<Order> getOrders() {
		return orders;
	}

	Iterator<Order> iterator;

	@XmlElementWrapper(name = "Orders")
	@XmlElement(name = "Order")
	private Vector<Order> orders;

	public Orders() {

	}

	public Order getNextOrder() {
		iterator = orders.iterator();
		if (iterator.hasNext())
			return iterator.next();
		
		return null;
	}

	

	/**
	 * Calculates orders difficulty , and updates the dish inside each order
	 * 
	 * @param menu
	 *            Menu of all dishes
	 */
	public void calcDifficulty(Menu menu) {
		for (Order order : orders) {
			order.calcOrderDifficulty(menu);
		}
	}

	public boolean hasOrders() {
		return orders.size() != 0;
	}
}
