package com.bgu.assignment3.passives;

import java.util.Vector;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Dish")
public class Dish {

	private String name;
	private long cookTime;
	private Vector<Ingredient> ingredients;
	private Vector<KitchenTool> kitchenTools;
	private double difficulty;
	private double reward;

	@Override
	public String toString() {
		
		// MUST BE RE IMPLEMENTED , was quick test for debuggin
		String ing = "";
		String kt = "";
		for (Ingredient current : ingredients) {
			ing += current.name + " " + current.quantity;
		}
		for (KitchenTool current : kitchenTools) {
			ing += current.name + " " + current.quantity;
		}
		return "Name = " + name + "\n"
		+ "cookTime  =" + cookTime + "\n"
		+ "diff = " + difficulty + "\n"
		+ "reward =" + reward + "\n"
		+ "kitchen tools=" + kt + "\n" 
		+ "ingredients  =" + ing	+ "\n";

	}
}
