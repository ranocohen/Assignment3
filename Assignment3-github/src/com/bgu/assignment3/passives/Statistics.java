package com.bgu.assignment3.passives;

import java.util.ArrayList;
import java.util.Vector;

public class Statistics {

	// Static nested class
	public static class StatisticsClass {
		
		public StatisticsClass() {
			consumedIngredients = new Vector<Ingredient>();
			deliveredOrders = new Vector<Order>();
		}
		
		private static Vector<Order> deliveredOrders;
		private static Vector<Ingredient> consumedIngredients;
		
		public static void addIngredientToStatistic(Ingredient ing) {
			consumedIngredients.add(ing);
		}
		
		public static void addDeliveredOrderToStatistics(Order o) {
			deliveredOrders.add(o);
		}
		
		
	}

}
