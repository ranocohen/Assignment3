package com.bgu.assignment3;

public interface WarehouseActions {
	
	//TODO add quantity reference 
	/**
	 * <p>
	 * Retrieve specified kitchen tool named {@link name} from the wareHouse
	 * </p>
	 * @param name  - the kitchen tool name
	 * @param quantity - the kitchen tool quantity 
	 * @Pre quantity > 0
	 */
	public void takeKitchenTool(String name, int quantity);
	
	/**
	 * Return back previously taken kitchen tool
	 * @param name
	 * @param quantity
	 * 
	 * @Pre quantity > 0
	 */
	public void returnKitchenTool(String name, int quantity);

	public void takeIngredient(String name, int quantity);
}

