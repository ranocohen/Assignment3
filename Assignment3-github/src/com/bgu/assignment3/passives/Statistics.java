package com.bgu.assignment3.passives;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class Statistics {

	// Static nested class
	public static class StatisticsClass {
		private static ArrayList<Order> deliveredOrders;
		private static ArrayList<Ingredient> consumedIngredients;
		private static double moneyGained;

		// Initialize ConcurrentHashMap instance
		static ConcurrentHashMap<Ingredient, Integer> hmConsumedIngredients;
		

		public static void init() {
			consumedIngredients = new ArrayList<Ingredient>();
			deliveredOrders = new ArrayList<Order>();
			hmConsumedIngredients = new ConcurrentHashMap<Ingredient, Integer>();
		}

		public static void addIngredientToStatistic(Ingredient ing) {

			if (hmConsumedIngredients.containsKey(ing)) {
				hmConsumedIngredients.put(ing, hmConsumedIngredients.get(ing) + 1);
			} else {
				hmConsumedIngredients.put(ing, 1);
			}
			
			for (Ingredient ingredient : consumedIngredients) {
				if (ingredient.compareTo(ing) == 0)
					return;
			}
			consumedIngredients.add(ing);
		}

		public static void addDeliveredOrderToStatistics(Order o) {
			deliveredOrders.add(o);
		}

		public static void addMoneyGained(double moneyGained) {
			StatisticsClass.moneyGained += moneyGained;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Money gained: " + moneyGained);

			for (Ingredient ingredient : consumedIngredients) {
				builder.append(ingredient.toString() + "\n");
			}

			Iterator<Ingredient> keySetIterator = hmConsumedIngredients.keySet().iterator();

			//print ingredients consumed
			while (keySetIterator.hasNext()) {
				Ingredient key = keySetIterator.next();
				System.out.println("key: " + key.getName() + " value: " + hmConsumedIngredients.get(key));
			}
			
			return builder.toString();
		}
	}

}
