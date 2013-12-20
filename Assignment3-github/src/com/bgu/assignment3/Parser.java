package com.bgu.assignment3;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Parser {

	public static void menuParser(String fileName) {
		try {

			File file = new File(fileName);
			JAXBContext jaxbContext = JAXBContext.newInstance(Dish.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Dish dish = (Dish) jaxbUnmarshaller.unmarshal(file);

			System.out.println(dish.toString());

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

}
