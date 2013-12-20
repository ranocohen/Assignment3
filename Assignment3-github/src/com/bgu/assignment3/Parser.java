package com.bgu.assignment3;


import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.bgu.assignment3.passives.Management;
import com.bgu.assignment3.passives.Menu;


public class Parser {

	public static void parseMenu(String fileName, Management management) {
		 try {
			 
				File file = new File("Menu.xml");
				JAXBContext jaxbContext = JAXBContext.newInstance(Menu.class);
		 
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				Menu menu = (Menu) jaxbUnmarshaller.unmarshal(file);
				System.out.println(menu);
		 
			  } catch (JAXBException e) {
				e.printStackTrace();
			  }
	}


}
