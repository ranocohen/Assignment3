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
		static ConcurrentHashMap<String, Integer> hmConsumedIngredients;
		

		public static void init() {
			
			deliveredOrders = new ArrayList<Order>();
			hmConsumedIngredients = new ConcurrentHashMap<String, Integer>();
		}



		public static void addIngredientToStatistic(Ingredient ing, int quantity) {

			if (hmConsumedIngredients.containsKey(ing.getName())) {
				int temp = hmConsumedIngredients.get(ing.getName());
				temp += quantity;
				hmConsumedIngredients.remove(ing.getName());
				
				//done in order to unify under same object
				hmConsumedIngredients.put(ing.getName(), temp);

			} else {
				hmConsumedIngredients.put(ing.getName(), quantity);
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

			

			Iterator<String> keySetIterator = hmConsumedIngredients.keySet().iterator();

			//print ingredients consumed
			while (keySetIterator.hasNext()) {

				String key = keySetIterator.next();
				builder.append("key: " + key + " value: " + hmConsumedIngredients.get(key)).newline();

			
	
				

			}
			
			return builder.toString();
		}
	}

}
