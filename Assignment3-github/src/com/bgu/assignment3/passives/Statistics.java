package com.bgu.assignment3.passives;

import java.util.Vector;

public class Statistics {

	// Static nested class
	public static class StatisticsClass {

		public static void init() {
			consumedIngredients = new Vector<Ingredient>();
			deliveredOrders = new Vector<Order>();
		}

		private static Vector<Order> deliveredOrders;
		private static Vector<Ingredient> consumedIngredients;
		private static double moneyGained;
		public static void addIngredientToStatistic(Ingredient ing) {
			consumedIngredients.add(ing);
		}

		public static void addDeliveredOrderToStatistics(Order o) {
			deliveredOrders.add(o);
		}

		public static void addMoneyGained(double moneyGained) {
			moneyGained+= moneyGained;
		}
	}

	
}
