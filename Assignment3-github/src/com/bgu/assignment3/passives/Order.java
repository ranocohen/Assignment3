package com.bgu.assignment3.passives;

import java.util.Vector;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import com.bgu.assignment3.FancyStringBuilder;

@XmlAccessorType(XmlAccessType.FIELD)
public class Order implements Comparable<Order> {

	// magic numbers
	public enum Status {
		INCOMPLETE, IN_PROGRESS, COMPLETE, DELIVERED
	}

	@XmlAttribute(name = "id")
	private long id;
	private int difficulty;
	private long expectedTime;
	private long actualCookTime;
	private long expectedDeliveryTime;
	private int reward;
	private boolean fullReward;
	private Status status;

	@XmlElement(name = "DeliveryAddress")
	private Address deliveryAddress;

	@XmlElementWrapper(name = "Dishes")
	@XmlElement(name = "Dish")
	private Vector<OrderOfDish> dishes;

	public static Order posionOrder() {
		Order poison = new Order();
		poison.id = -1;
		return poison;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public long getId() {
		return id;
	}

	public int calcDistance(Address src) {
		int distance = 0;

		long dis = Math.round(Math.sqrt(Math.pow(
				src.x - this.deliveryAddress.getX(), 2)
				+ Math.pow(src.x - this.deliveryAddress.getX(), 2)));

		distance = (int) dis;
		this.expectedDeliveryTime = distance;
		return distance;
	}

	public void init(Menu m) {
		calcOrderDifficulty(m);
		this.status = Status.INCOMPLETE;
	}

	public void calcOrderDifficulty(Menu m) {
		for (int i = 0; i < dishes.size(); i++) {
			Dish dish = m.getDishByName(dishes.get(i).getDishName());
			dishes.get(i).setDish(dish);
			this.difficulty += dish.getDifficulty();
		}
		calcReward();
		calculateExpectedTime();
	}

	private void calcReward() {
		reward = 0;
		for (int i = 0; i < dishes.size(); i++) {

			Dish dish = dishes.get(i).getDish();
			reward += dish.getReward();
		}
	}

	private void calculateExpectedTime() {
		expectedTime = 0;
		for (int i = 0; i < dishes.size(); i++) {

			Dish dish = dishes.get(i).getDish();

			if (i == 0)
				expectedTime = dish.getCookTime();
			else if (dish.getCookTime() > expectedTime)
				expectedTime = dish.getCookTime();

		}

	}

	public int calculateTotalDishes() {
		int dishesCount = 0;
		for (OrderOfDish current : dishes) {
			dishesCount += current.getQuantity();
		}

		return dishesCount;
	}

	@Override
	public String toString() {
		FancyStringBuilder builder = new FancyStringBuilder();
		builder.append("Order").append("id", getId())
				.append("difficulty", difficulty)
				.append("actualCookTime", actualCookTime)
				.append("expectedCookTime", expectedTime)
				.append("Address", deliveryAddress.toString());

		builder.append("OrderOfDish");
		for (OrderOfDish ood : dishes)
			builder.append(ood.getDishName(), ood.getQuantity());

		return builder.toString();

	}

	public Vector<OrderOfDish> getDishes() {
		return dishes;
	}

	public void setStatus(Status newStatus) {
		this.status = newStatus;

	}

	public Status getStatus() {
		return this.status;
	}

	public double calculateReward(long actualDeliveryTime) {

		if ((actualDeliveryTime + actualCookTime) > 1.15 * (expectedTime + expectedDeliveryTime)) {
			fullReward = false;
			return 0.5 * reward;
		}
		fullReward = true;

		return reward;
	}

	public void setActualCookTime(long actualCookTime) {
		this.actualCookTime = actualCookTime;
	}

	public int compareTo(Order o) {
		return this.dishes.size() - o.dishes.size();
	}

	public boolean fullReward() {
		return fullReward;
	}

	public long getReward() {
		return reward;
	}
}
