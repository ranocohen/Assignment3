package com.bgu.assignment3;

public interface WarehouseActions {
	
	/**
	 * Adds a new  @link KitchenTool to the WareHouse 
	 * @param name  - the KitchenTool name
	 * @param quantity - the KitchenTool quantity 
	 */
	public void addKitchenTool(String name, int quantity);

	public void addIngredient(String name, int quantity);
}

