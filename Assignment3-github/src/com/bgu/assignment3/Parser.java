package com.bgu.assignment3;


import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.bgu.assignment3.passives.Management;
import com.bgu.assignment3.passives.Menu;
import com.bgu.assignment3.passives.Orders;


public class Parser {



	public static Management parseInitialData(String fileName) {
		 try {
			 	
				File file = new File(fileName);
				JAXBContext jaxbContext = JAXBContext.newInstance(Management.class);
		 
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				Management management = (Management) jaxbUnmarshaller.unmarshal(file);
				return management;
		 
			  } catch (JAXBException e) {
				e.printStackTrace();
				return null;
			  }
		 	
	}
	public static Menu parseMenu(String fileName) {
		 try {
				File file = new File("Menu.xml");
				JAXBContext jaxbContext = JAXBContext.newInstance(Menu.class);
		 
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				Menu menu = (Menu) jaxbUnmarshaller.unmarshal(file);
				return menu;
		 
			  } catch (JAXBException e) {
				e.printStackTrace();
				return null;
			  }
	}
	public static Orders parseOrdersList(String fileName) {
		 try {
			 
				File file = new File(fileName);
				JAXBContext jaxbContext = JAXBContext.newInstance(Orders.class);
		 
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				Orders orders = (Orders) jaxbUnmarshaller.unmarshal(file);
				return orders;
		 
			  } catch (JAXBException e) {
				e.printStackTrace();
				return null;
			  }
		
	}

}
