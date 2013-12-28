package com.bgu.assignment3.passives;

import java.util.Iterator;
import java.util.Vector;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

import com.bgu.assignment3.actions.RunnableChef;
import com.bgu.assignment3.passives.Order.Status;

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
		// TODO catch null
		return null;
	}

	public boolean deployOrder(Staff staff, Warehouse wh) {
		Logger.getLogger(Management.class).info(
				"Managment starting to look for chefs");
		boolean foundChef = false;
		iterator = orders.iterator();
		while (iterator.hasNext()) {
			Order current = iterator.next();
			if (current.getStatus() == Status.INCOMPLETE) {

				Logger.getLogger(Management.class).info(
						"Looking for chef to cook " + current.getId());
				RunnableChef acceptingChef = staff.getApprovingChef(current);
				if (acceptingChef != null) {
					foundChef = true;
					acceptingChef.acceptOrder(current, wh);

				}
			}
		}
		return foundChef;

	}

	/**
	 * Calculates orders difficulty , and updates the dish inside each order
	 * 
	 * @param menu
	 *            Menu of all dishes
	 */
	public void calcDifficulty(Menu menu) {
		for (Order order : orders) {
			order.init(menu);
		}
	}

	public boolean allDelivered() {
		boolean allDelivered = true;
		for(Order current : orders) {
			if(current.getStatus() != Status.DELIVERED)
				return false;
			
		}
		return allDelivered;
	}

	public void removeOrder(Order order) {
		orders.remove(order);
	}

	
}
