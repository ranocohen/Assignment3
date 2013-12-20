package com.bgu.assignment3.passives;

public class Ingredient {

	private String name;
	private int quantity;

	@Override
	public String toString() {
		return name + " : " + quantity + "\n";
	}
}
