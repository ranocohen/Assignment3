package com.bgu.assignment3.passives;

import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {

	
	//magic numbers
	private enum Status { 
		INCOMPLETE , IN_PROGRESS , COMPLETE , DELIVERED 
	}
	
	@XmlAttribute(name="id")
	private long id;
	
	public long getId() {
		return id;
	}

	private int difficulty;
	public int getDifficulty() {
		return difficulty;
	}

	private Status status;
	
	@XmlElement(name = "DeliveryAddress")
	private Address deliveryAddress;
	
	@XmlElementWrapper(name="Dishes")
	@XmlElement(name = "Dish")
	private Vector<OrderOfDish> dishes;
	
<<<<<<< HEAD
	public int calcDistance(Address src) {
		int distance = 0;
		
		long dis = Math.round( Math.sqrt(Math.pow(src.x-this.deliveryAddress.getX(), 2) + 
							 Math.pow(src.x-this.deliveryAddress.getX(), 2)) );
		
		distance = (int) dis;
		
		return distance;
	}


=======
	
	public void init(Menu m) {
		calcOrderDifficulty(m);
		this.status = Status.INCOMPLETE;
	}
>>>>>>> branch 'master' of https://github.com/ranocohen/Assignment3.git
	public void calcOrderDifficulty(Menu m) {
		for (int i = 0; i < dishes.size(); i++){
			Dish dish = m.getDishByName(dishes.get(i).getDishName());
			dishes.get(i).setDish(dish);
			this.difficulty += dish.getDifficulty();
		}
	}
	
	public int calculateTotalDishes () {
		int dishesCount = 0;
		for(OrderOfDish current : dishes) {
			dishesCount+= current.getQuantity();
		}
		
		return dishesCount;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[ Order ] ")
		.append("id = "+getId() +" \n ");
		
		for(OrderOfDish ood : dishes) 
			builder.append(ood.getDishName() + " " + ood.getQuantity()+ "\n ");
		
		
		return builder.toString();
		
	}
	public Vector<OrderOfDish> getDishes() {
		return dishes;
	}
	public void setIsInProgress() {
		this.status = Status.IN_PROGRESS;
	}
	public boolean isInComplete() {
		return status == Status.INCOMPLETE;
	}
}
