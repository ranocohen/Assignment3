package com.bgu.assignment3;


import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.bgu.assignment3.passives.Management;
import com.bgu.assignment3.passives.Menu;
import com.bgu.assignment3.passives.Orders;


public class Parser {



	public static void parseInitialData(String fileName, Management management) {
		 try {
			 
				File file = new File(fileName);
				JAXBContext jaxbContext = JAXBContext.newInstance(Management.class);
		 
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				management = (Management) jaxbUnmarshaller.unmarshal(file);
				System.out.println(management);
		 
			  } catch (JAXBException e) {
				e.printStackTrace();
			  }
		
	}
	public static void parseMenu(String fileName, Management management) {
		 try {
			 
				File file = new File("Menu.xml");
				JAXBContext jaxbContext = JAXBContext.newInstance(Menu.class);
		 
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				Menu menu = (Menu) jaxbUnmarshaller.unmarshal(file);
				management.addMenu(menu);
		 
			  } catch (JAXBException e) {
				e.printStackTrace();
			  }
	}
	public static void parseOrdersList(String fileName, Management management) {
		 try {
			 
				File file = new File(fileName);
				JAXBContext jaxbContext = JAXBContext.newInstance(Orders.class);
		 
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				Orders orders = (Orders) jaxbUnmarshaller.unmarshal(file);
				management.addOrders(orders);
		 
			  } catch (JAXBException e) {
				e.printStackTrace();
			  }
		
	}

}
