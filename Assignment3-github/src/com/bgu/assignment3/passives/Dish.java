package com.bgu.assignment3.passives;

import java.util.ArrayList;
import java.util.Collections;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.bgu.assignment3.FancyStringBuilder;

@XmlAccessorType(XmlAccessType.FIELD)
public class Dish {

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
	
	public int getDifficulty() {
		return difficulty;
	}

	public String getName() {
		return name;
	}
	
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	public ArrayList<KitchenTool> getKitchenTools() {
		return kitchenTools;
	}
	
	

	@Override
	public String toString() {

		FancyStringBuilder builder = new FancyStringBuilder();
		
		builder.append("name",name)
		.append("difficulty",difficulty)
		.append("expectedCookTime",cookTime)
		.append("reward",reward)
		.append("Ingredients");
		
		for(Ingredient ingredient : ingredients)
			builder.append(ingredient.toString());
		
		for(KitchenTool kt : kitchenTools)
			builder.append(kt.toString());
		
		return builder.toString();

	}
	void afterUnmarshal(Unmarshaller u, Object parent) {
		Collections.sort(this.kitchenTools);
	}

	public int getReward() {
		return this.reward;
	}
}
