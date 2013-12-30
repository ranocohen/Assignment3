package com.bgu.assignment3.passives;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.bgu.assignment3.FancyStringBuilder;


@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	double x;
	double y;
	
	/**
	 * 
	 * @return the distance(Euclidean) from other address
	 */
	public int distanceFrom(Address other) { 
		double deltaX = this.x - other.x;
		double deltaY = this.y - other.y;
		
		return (int) Math.sqrt(deltaX*deltaX + deltaY*deltaY);
	}
	@Override
	public String toString() {
		FancyStringBuilder builder = new FancyStringBuilder();
		
		builder.append("x",x).append("y",y);
		
		return builder.toString();
		
	}
}
