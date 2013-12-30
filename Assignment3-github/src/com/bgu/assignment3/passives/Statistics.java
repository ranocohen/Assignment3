package com.bgu.assignment3.passives;

import java.util.ArrayList;

public class Statistics {

	// Static nested class
	public static class StatisticsClass {
		private static ArrayList<Order> deliveredOrders;
		private static ArrayList<Ingredient> consumedIngredients;
		private static double moneyGained;
		
		public static void init() {
			consumedIngredients = new ArrayList<Ingredient>();
			deliveredOrders = new ArrayList<Order>();
		}

		
		public static void addIngredientToStatistic(Ingredient ing) {
			for(Ingredient ingredient : consumedIngredients) {
				if(ingredient.compareTo(ing) == 0 )
					return;		
			}
			consumedIngredients.add(ing);
		}

		public static void addDeliveredOrderToStatistics(Order o) {
			deliveredOrders.add(o);
		}

		public static void addMoneyGained(double moneyGained) {
			StatisticsClass.moneyGained+= moneyGained;
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Money gained: "+moneyGained);
			
			for(Ingredient ingredient : consumedIngredients) {
				builder.append(ingredient.toString() +"\n");
			}
			return builder.toString();
		}
	}

	
}
