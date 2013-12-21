package com.bgu.assignment3.pojos;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.bgu.assignment3.passives.KitchenTool;

public class Repository {
	
	@XmlElement(name="Tools")  
	public List<KitchenTool> getTools() {
		return tools;
	}

	public void setTools(List<KitchenTool> tools) {
		this.tools = tools;
	}

	@XmlElement(name="Ingredients")  
	public Ingredients getIngredients() {
		return ingredients;
	}

	public void setIngredients(Ingredients ingredients) {
		this.ingredients = ingredients;
	}

	public Repository() {
		
	}
	
	public Repository(List<KitchenTool> tools, Ingredients ingredients) {
		super();
		this.tools = tools;
		this.ingredients = ingredients;
	}
	
	List<KitchenTool> tools;
	Ingredients ingredients;
	


}
