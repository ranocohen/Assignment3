package com.bgu.assignment3.passives;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Semaphore;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class Dish {

	public int getDifficulty() {
		return difficulty;
	}

	public String getName() {
		return name;
	}

	@XmlElement(name = "name")
	private String name;
	@XmlElement(name = "expectedCookTime")
	private long cookTime;
	
	@XmlElement(name = "difficultyRating")
	private int difficulty;
	@XmlElement(name = "reward")
	private int reward;
	
	public long getCookTime() {
		return cookTime;
	}

	@XmlElementWrapper(name = "Ingredients")
	@XmlElement(name = "Ingredient")
	private ArrayList<Ingredient> ingredients;
	
	@XmlElementWrapper(name = "KitchenTools")
	@XmlElement(name = "KitchenTool")
	private ArrayList<KitchenTool> kitchenTools;
	
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	public ArrayList<KitchenTool> getKitchenTools() {
		return kitchenTools;
	}
	
	

	@Override
	public String toString() {

		// MUST BE RE IMPLEMENTED , was quick test for debuggin
		String ing = "";
		String kt = "";
		 for (Ingredient current : ingredients) 
		 ing += current.toString();
		 
		for (KitchenTool current : kitchenTools) 
			ing += current.toString();
		
		return "Name = " + name + "\n" + "cookTime  =" + cookTime + "\n"
				+ "diff = " + difficulty + "\n" + "reward =" + reward + "\n"
				+ "kitchen tools=" + kt + "\n" + "ingredients  =" + ing + "\n";

	}
	void afterUnmarshal(Unmarshaller u, Object parent) {
		Collections.sort(this.kitchenTools);
	}

	public int getReward() {
		return this.reward;
	}
}
