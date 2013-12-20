package com.bgu.assignment3.passives;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class KitchenTool  {
	
	@XmlElement(name="name")
	private String name;
	@XmlElement(name="quantity")
	private int quantity;

	@Override
	public String toString() {
		return name + " : " + quantity +"\n";
	}
}