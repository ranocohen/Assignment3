package com.bgu.assignment3.pojos;

public class DeliveryPerson {
	
	public DeliveryPerson() {
		
	}
	
	public DeliveryPerson(String name, int speed) {
		super();
		this.name = name;
		this.speed = speed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	String name;
	int speed;

}
