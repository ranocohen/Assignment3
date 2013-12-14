package com.bgu.assignment3;

public interface WarehouseActions {
	
	/**
	 * <p>
	 * Retrieve specified kitchen tool named {@link name} from the wareHouse
	 * </p>
	 * @param name  - the kitchen tool name
	 * @param quantity - the kitchen tool quantity 
	 * 
	 * 1. quantity>0 2. not trying to take more than exists 3. befor and after take state is equal
	 * @Pre quantity > 0
	 * @Pre (this).getKitchenToolQuantity(name) - quantity >= 0
	 * @Post(this).getKitchenToolQuantity(name) + quantity = @Pre(this).getKitchenToolQuantity(name)
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
	
	/**
	 * <b> Command </b> </br>
	 * Adds a new  {@link Ingredient} to the WareHouse 
	 * @param name  - the Ingredient name
	 * @param quantity - the Ingredient quantity
	 * 
	 * @require quantity > 0
	 * @pre none
	 * @post getIngredientQuantity(name) == quantity
	 * 
	 */
	public void addIngredient(String name, int quantity);
	
	
	/**
	 * Adds a new  {@link KitchenTool} to the WareHouse 
	 * @param name  - the KitchenTool name
	 * @param quantity - the KitchenTool quantity 
	 */
	public void addKitchenTool(String name, int quantity);
	
	
	public int getKitchenToolQuantity(String toolName);
	
	/**
	 * <b> Query </b> </br>
	 * 
	 * Returns current quantity of given {@link Ingredient} 
	 * @param ingredientName the name of the Ingredient
	 * @return quantity of given Ingredient 
	 * 
	 * @require Ingredient named ingredientName is available in the WareHouse
	 * @pre none
	 * @post none
	 * @inv none
	 */
	public int getIngredientQuantity(String ingredientName);
	
	
	
	
}

