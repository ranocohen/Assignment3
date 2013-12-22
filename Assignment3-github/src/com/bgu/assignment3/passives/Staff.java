package com.bgu.assignment3.passives;

import java.util.Collections;
import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.bgu.assignment3.actions.RunnableChef;
import com.bgu.assignment3.actions.RunnableDeliveryPerson;

@XmlAccessorType(XmlAccessType.FIELD)
public class Staff {

	@XmlElementWrapper(name="Chefs")
	@XmlElement(name = "Chef")
	private Vector<RunnableChef> chefs;
	
	@XmlElementWrapper(name="DeliveryPersonals")
	@XmlElement(name = "DeliveryPerson")
	private Vector<RunnableDeliveryPerson> deliveryPersons;
	
	/**
	 * Sorts the chefs by efficiency
	 */
	public void sortChefs() {
		Collections.sort(chefs);
	}
	public RunnableChef getApprovingChef(Order order) {
		for(RunnableChef chef : chefs) {
			if(chef.acceptingOrder(order.getDifficulty()));
				return chef;
		}
		//couldnt find approving chef , returning null
		return null;
	}
	public int chefCount() {
		return chefs.size();
	}
}
