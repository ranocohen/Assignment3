package com.bgu.assignment3.pojos;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Ingredients {
	
	public Ingredients() {
		
	}
	
	public Ingredients(List<Ingredient> ingredients) {
		super();
		this.ingredients = ingredients;
	}

	@XmlElement(name="Ingredient")  
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	List<Ingredient> ingredients;


}
