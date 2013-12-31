package com.bgu.assignment3.passives;

/**
 * 
 * @inv getKitchenToolQuantity(name) >= 0
 * @inv getIngredientQuantity(name) >= 0
 *
 */
public interface WarehouseActions {
	
	/**
	 * <p>
	 * <b> Command </b> </br>
	 * Retrieve specified kitchen tool named {@link name} from the wareHouse
	 * </p>
	 * @param name  - the kitchen tool name
	 * @param quantity - the kitchen tool quantity 
	 * 
     * </br></br>
	 * 1. quantity > 0 </br> 
	 * 2. not trying to take more than exists </br> 
	 * 3. before and after take state is equal </br>
	 * 
	 * @require quantity > 0
	 * @pre (this).getKitchenToolQuantity(name) - quantity >= 0
	 * @post(this).getKitchenToolQuantity(name) + quantity = @pre(this).getKitchenToolQuantity(name)
	 */
	public void takeKitchenTool(String name, int quantity);
	
	/**
	 * <b> Command </b> </br>
	 * Return back previously taken kitchen tool
	 * @param name
	 * @param quantity
	 * 
	 * @require quantity > 0
	 * @post (this).getKitchenToolQuantity(name) > 0
	 * @post (this).getKitchenToolQuantity(name) - quantity == @pre(this).getKitchenToolQuantity(name)
	 */
	public void returnKitchenTool(String name, int quantity);

	

	/**
	 * <b> Command </b> </br>
	 * Adds a new  {@link KitchenTool} to the WareHouse 
	 * @param name  - the KitchenTool name
	 * @param quantity - the KitchenTool quantity 
	 * 
	 * @require quantity > 0
	 * @pre none
	 * @post (this).getKitchenToolQuantity(name) == quantity
	 * 
	 */
	public void addKitchenTool(String name, int quantity);
	
	/**
	 * <b> Query </b> </br>
	 * 
	 * Returns current quantity of given {@link KitchenTool} 
	 * @param name the name of the KitchenTool
	 * @return quantity of given KitchenTool 
	 * 
	 * @require KitchenTool named <code>name</code> is available in the WareHouse
	 * @pre none
	 * @post (this).getKitchenToolQuantity(name) == @pre (this).getKitchenToolQuantity(name)
	 */
	public int getKitchenToolQuantity(String name);
	
	/**
	 * <b> Query </b> </br>
	 * 
	 * Returns current quantity of given {@link Ingredient} 
	 * @param ingredientName the name of the Ingredient
	 * @return quantity of given Ingredient 
	 * 
	 * @require Ingredient named {@code name} is available in the WareHouse
	 * @pre none
	 * @post (this).getIngredientQuantity(name) == @pre (this).getIngredientQuantity(name)
	 */
	public int getIngredientQuantity(String îame);
	
	
	/**
	 * <b> Command </b> </br>
	 * Retrieve specified ingredient named {@code name} from the wareHouse

	 * @param name  - the ingredient name
	 * @param quantity - the ingredient quantity 
	 * 
	 * </br></br>
	 * 1. quantity > 0 </br> 
	 * 2. not trying to take more than exists </br> 
	 * 3. before and after take state is equal </br>
	 * 
	 * @require  quantity > 0
	 * @pre (this).getIngredientQuantity(name) - quantity >= 0
	 * @post(this).getIngredientQuantity(name) + quantity = @pre(this).getIngredientQuantity(name)
	 */
	public void takeIngredient(String name, int quantity);
	
	/**
	 * <b> Command </b> </br>
	 * Adds a new  {@link Ingredient} to the WareHouse 
	 * @param name  - the Ingredient name
	 * @param quantity - the Ingredient quantity
	 * 
	 * @require quantity > 0
	 * @pre none
	 * @post (this).getIngredientQuantity(name) == quantity
	 * 
	 */
	public void addIngredient(String name, int quantity);
	
	
}

