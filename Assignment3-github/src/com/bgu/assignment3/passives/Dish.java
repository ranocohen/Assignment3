package com.bgu.assignment3.passives;

import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class Dish {

	@XmlElement(name = "name")
	private String name;
	@XmlElement(name = "expectedCookTime")
	private long cookTime;
	
	@XmlElementWrapper(name = "Ingredients")
	@XmlElement(name = "Ingredient")
	private Vector<Ingredient> ingredients;
	
	@XmlElementWrapper(name = "KitchenTools")
	@XmlElement(name = "KitchenTool")
	private Vector<KitchenTool> kitchenTools;
	
	@XmlElement(name = "difficultyRating")
	private int difficulty;
	@XmlElement(name = "reward")
	private double reward;

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
}
