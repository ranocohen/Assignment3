package com.bgu.assignment3.passives;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import com.bgu.assignment3.FancyStringBuilder;

public class Statistics {

	// Static nested class
	public static class StatisticsClass {
		private static ArrayList<Order> deliveredOrders;
		private static double moneyGained;

		// Initialize ConcurrentHashMap instance
		static ConcurrentHashMap<Ingredient, Integer> hmConsumedIngredients;
		

		public static void init() {
			
			deliveredOrders = new ArrayList<Order>();
			hmConsumedIngredients = new ConcurrentHashMap<Ingredient, Integer>();
		}

		public static void addIngredientToStatistic(Ingredient ing) {
			synchronized (ing) {
				
			
			if (hmConsumedIngredients.containsKey(ing)) {
				hmConsumedIngredients.put(ing, hmConsumedIngredients.get(ing) + 1);
			} else {
				hmConsumedIngredients.put(ing, 1);
			}
			}
		
		
		}

		public static void addDeliveredOrderToStatistics(Order o) {
			deliveredOrders.add(o);
		}

		public static void addMoneyGained(double moneyGained) {
			StatisticsClass.moneyGained += moneyGained;
		}

		@Override
		public String toString() {
			FancyStringBuilder builder = new FancyStringBuilder();
			builder.append("Money gained",moneyGained);

			

			Iterator<Ingredient> keySetIterator = hmConsumedIngredients.keySet().iterator();

			//print ingredients consumed
			while (keySetIterator.hasNext()) {
				Ingredient key = keySetIterator.next();
				builder.append(key.toString());
			}
			
			return builder.toString();
		}
	}

}
