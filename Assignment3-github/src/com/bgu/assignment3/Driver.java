package com.bgu.assignment3;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.bgu.assignment3.passives.Management;

public class Driver {
	private static Logger log = Logger.getLogger(Driver.class);
	public static void main(String[] args) {
		System.out.println("Assignment 3");
		Management management = Parser.parseInitialData(args[0]);

		management.addMenu(Parser.parseMenu(args[1]));
		management.addOrders(Parser.parseOrdersList(args[2]));
		management.simulate();

		//log4j , notice the log4j.properties file inside bin dir in the project 

		log.setLevel(Level.FATAL);
		log.trace("Trace");
		log.debug("Debug");
		log.info("Info");
		log.warn("Warn");
		log.error("Error");
		log.fatal("Fatal");





	}

	// public Driver(String initData, String menu, String ordersList) {
	public Driver(String[] confXMLFiles) {

	}

	public void configureResturant(String[] confXMLFiles) {

	}

	public void analyzeInitData(String fileName) {
		try {
			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :"
					+ doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("staff");

			System.out.println("----------------------------");
		} catch (Exception e) {

		}
	}

	public void analyzeMenu(String fileName) {
		try {
			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :"
					+ doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("staff");

			System.out.println("----------------------------");
		} catch (Exception e) {

		}
	}

	public void analyzeOrdersList(String fileName) {
		try {
			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :"
					+ doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("staff");

			System.out.println("----------------------------");
		} catch (Exception e) {

		}
	}

}
