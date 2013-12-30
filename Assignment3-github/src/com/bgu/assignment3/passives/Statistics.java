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
		static ConcurrentHashMap<Ingredient, Integer> m;

		public static void init() {
			consumedIngredients = new ArrayList<Ingredient>();
			deliveredOrders = new ArrayList<Order>();
			m = new ConcurrentHashMap<Ingredient, Integer>();
		}

		public static void addIngredientToStatistic(Ingredient ing) {

			if (m.containsKey(ing)) {
				m.put(ing, m.get(ing) + 1);
			} else {
				m.put(ing, 1);
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

			Iterator<Ingredient> keySetIterator = m.keySet().iterator();

			while (keySetIterator.hasNext()) {
				Ingredient key = keySetIterator.next();
				System.out.println("key: " + key.getName() + " value: " + m.get(key));
			}
			return builder.toString();
		}
	}

}
