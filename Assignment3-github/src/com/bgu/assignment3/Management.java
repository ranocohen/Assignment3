package com.bgu.assignment3;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Resturant")
public class Management {
	
	private Warehouse warehouse;
	private Address address;
	public Management() {
		
	}

}
