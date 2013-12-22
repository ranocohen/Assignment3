package com.bgu.assignment3.passives;

import java.util.ArrayList;
import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;


/**
 * 
 * Warehouse class holding our ingredients and kitchen tools
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Warehouse implements WarehouseActions {
	@XmlElementWrapper(name = "Ingredients")
	@XmlElement(name = "Ingredient")
	private ArrayList<Ingredient> ingredients;
	@XmlElementWrapper(name = "Tools")
	@XmlElement(name = "KitchenTool")
	private ArrayList<KitchenTool> kitchenTools;

	public Warehouse() {

	}

	/**
	 * Simulates acquiring @{link KitchenTool} from the Warehouse
	 * 
	 * @param name
	 *            - kitchen tool name
	 * @param quantity
	 *            - quantity to take
	 */
	public void takeKitchenTool(String name, int quantity) {
		for (int i = 0; i < kitchenTools.size(); i++) {
			if(kitchenTools.get(i).getName() == name) {
				kitchenTools.get(i).takeKitchenTool(quantity);
			}
		}
	}

	/**
	 * Simulating returning @{link KitchenTool} to the Warehouse
	 * 
	 * @param name
	 *            - kitchen tool name
	 * @param quantity
	 *            - quantity to take
	 */
	public void returnKitchenTool(String name, int quantity) {
		for (int i = 0; i < kitchenTools.size(); i++) {
			if(kitchenTools.get(i).getName() == name) {
				kitchenTools.get(i).returnKitchenTool(quantity);
			}
		}
	}

	/**
	 * Adds a new @{link KitchenTool} to the Warehouse
	 * 
	 * @param name
	 *            - the kitchen tool name
	 * @param quantity
	 *            - the kitchen tool quantity
	 */
	public void addKitchenTool(String name, int quantity) {
		// not needed eventually , done via JAXB parsing

	}

	/**
	 * Gets the current quantity of specific @{link KitchenTool}
	 * 
	 * @param name
	 *            - the kitchen tool name
	 * 
	 * @return the quantity of kitchen tool named {@code name}
	 */
	public int getKitchenToolQuantity(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Gets the current quantity of specific {@link Ingredient}
	 * 
	 * @param name
	 *            - the ingredient name
	 * 
	 * @return the quantity of ingredient named {@code name}
	 */
	public int getIngredientQuantity(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Simulates acquiring {@link Ingredient} from the Warehouse
	 * 
	 * @param name
	 *            - ingredient name
	 * @param quantity
	 *            - quantity to take
	 */
	public void takeIngredient(String name, int quantity) {
		for (int i = 0; i < ingredients.size(); i++) {
			if(ingredients.get(i).getName() == name) {
				ingredients.get(i).CunsumeIngredient(quantity);
			}
		}
	}

	/**
	 * Adds a new {@link Ingredient} to the Warehouse
	 * 
	 * @param name
	 *            - the ingredient name
	 * @param quantity
	 *            - the ingredient quantity
	 */
	public void addIngredient(String name, int quantity) {
		// not needed eventually , done via JAXB parsing

	}

}
