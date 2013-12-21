package com.bgu.assignment3.passives;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Restaurant")
public class Management {
	
	@XmlElement(name = "Repository")
	private Warehouse warehouse;
	
	@XmlElement(name = "Address")
	private Address address;
	
	@XmlElement(name = "Staff")
	private Staff staff;
	
	
	
	public Management() {
		
	}
	
	

}
