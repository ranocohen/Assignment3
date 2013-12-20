package com.bgu.assignment3.pojos;

public class Chef {
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEfficiencyRating() {
		return efficiencyRating;
	}

	public void setEfficiencyRating(int efficiencyRating) {
		this.efficiencyRating = efficiencyRating;
	}

	public int getEnduranceRating() {
		return enduranceRating;
	}

	public void setEnduranceRating(int enduranceRating) {
		this.enduranceRating = enduranceRating;
	}

	public Chef(){
		
	}
	
	public Chef(String name, int efficiencyRating, int enduranceRating) {
		super();
		this.name = name;
		this.efficiencyRating = efficiencyRating;
		this.enduranceRating = enduranceRating;
	}
	String name;
	int efficiencyRating;
	int enduranceRating;

}
