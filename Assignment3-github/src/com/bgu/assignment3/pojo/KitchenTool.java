package com.bgu.assignment3.pojo;

import javax.xml.bind.annotation.XmlElement;


public class KitchenTool {
	private String name;
	private int quantity;

	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}
	@XmlElement
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
