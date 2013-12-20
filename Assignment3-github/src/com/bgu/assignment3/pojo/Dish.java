package com.bgu.assignment3.pojo;

import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

public class Dish {
	private String name;
	private double difficultyRating;
	private long expectedCookTime;
	private double reward;
	private Vector<KitchenTool> kitchenTools;
	private Vector<Ingredient> ingredients;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDifficultyRating() {
		return difficultyRating;
	}

	public void setDifficultyRating(double difficultyRating) {
		this.difficultyRating = difficultyRating;
	}

	public long getExpectedCookTime() {
		return expectedCookTime;
	}

	public void setExpectedCookTime(long expectedCookTime) {
		this.expectedCookTime = expectedCookTime;
	}

	public double getReward() {
		return reward;
	}

	public void setReward(double reward) {
		this.reward = reward;
	}

	public Vector<KitchenTool> getKitchenTools() {
		return kitchenTools;
	}

	public void setKitchenTools(Vector<KitchenTool> kitchenTools) {
		this.kitchenTools = kitchenTools;
	}

	public Vector<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Vector<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

}
