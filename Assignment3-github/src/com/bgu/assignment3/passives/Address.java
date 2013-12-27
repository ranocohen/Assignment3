package com.bgu.assignment3.passives;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


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
}
