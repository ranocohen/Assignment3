package com.bgu.assignment3.pojos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;  
import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlRootElement; 

@XmlRootElement(name="Restaurant")
public class Restaurant {
	
	@XmlElement(name="Address")  
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@XmlElement(name="Repository")
	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public Restaurant() {
		
	}
	
	public Restaurant(Address address, Repository repository) {
		super();
		this.address = address;
		this.repository = repository;
	}
	Address address;
	Repository repository;

}
