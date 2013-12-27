package com.bgu.assignment3.passives;

import java.util.ArrayList;

public class Statistics {

	// Static nested class
	public static class StatisticsClass {
		
		public StatisticsClass() {
			consumedIngredients = new ArrayList<Ingredient>();
			deliveredOrders = new ArrayList<Order>();
		}
		
		private static ArrayList<Order> deliveredOrders;
		private static ArrayList<Ingredient> consumedIngredients;
		
		public static void addIngredientToStatistic(Ingredient ing) {
			consumedIngredients.add(ing);
		}
		
		public static void addDeliveredOrderToStatistics(Order o) {
			deliveredOrders.add(o);
		}
		
		
	}

}
