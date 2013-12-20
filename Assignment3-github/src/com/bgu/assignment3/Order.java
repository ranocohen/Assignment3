package com.bgu.assignment3;

public class Order {

	
	private enum Status { 
		INCOMPLETE , IN_PROGRESS , COMPLETE , DELIVERED 
	}
	
	private long id;
	private double difficulty;
	private int status;
}
