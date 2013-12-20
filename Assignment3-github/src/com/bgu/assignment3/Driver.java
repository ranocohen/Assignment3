package com.bgu.assignment3;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.bgu.assignment3.passives.Management;
import com.bgu.assignment3.pojos.Restaurant;

public class Driver {

	public static void main ( String [] args){
/*		System.out.println("Assignment 3");
		Management management = new Management();
<<<<<<< HEAD
		Parser.parseMenu("Menu.xml", management);
=======
		Parser.parseMenu("InitialData.xml", management);*/
	     try {  
	    	   
	         File file = new File("InitialData.xml");  
	         JAXBContext jaxbContext = JAXBContext.newInstance(Restaurant.class);  
	    
	         Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
	         Restaurant que = (Restaurant) jaxbUnmarshaller.unmarshal(file);  
	         
	         System.out.println(que.getAddress());
	    
	       } catch (JAXBException e) {  
	         e.printStackTrace();  
	       }  

		
	}
	//public Driver(String initData, String menu, String ordersList) {
	public Driver(String[] confXMLFiles) {

	}

	public void configureResturant(String[] confXMLFiles) {

	}

	public void analyzeInitData(String fileName) {
		try {
			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("staff");

			System.out.println("----------------------------");
		} catch(Exception e) {

		}
	}
	

	public void analyzeMenu(String fileName) {
		try {
			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("staff");

			System.out.println("----------------------------");
		} catch(Exception e) {

		}
	}

	public void analyzeOrdersList(String fileName) {
		try {
			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("staff");

			System.out.println("----------------------------");
		} catch(Exception e) {

		}
	}



}
