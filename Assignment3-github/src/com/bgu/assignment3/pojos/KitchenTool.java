package com.bgu.assignment3.pojos;

public class KitchenTool {
	
	public KitchenTool() {
		
	}
	
	public KitchenTool(String name, int quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	String name;
	int quantity;

}
