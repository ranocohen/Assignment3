package com.bgu.assignment3.passives;

import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.bgu.assignment3.WarehouseActions;

/**
 * 
 * Warehouse class holding our ingredients and kitchen tools
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Warehouse implements WarehouseActions {
	@XmlElementWrapper(name = "Ingredients")
	@XmlElement(name = "Ingredient")
	private Vector<Ingredient> ingredients;
	@XmlElementWrapper(name = "Tools")
	@XmlElement(name = "KitchenTool")
	private Vector<KitchenTool> kitchenTools;

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
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

}
