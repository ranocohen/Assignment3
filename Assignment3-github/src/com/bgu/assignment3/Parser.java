package com.bgu.assignment3;


import com.bgu.assignment3.passives.KitchenTool;
import com.bgu.assignment3.passives.Management;


import java.io.File;
import java.util.Vector;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Parser {

	public static void parseMenu(String fileName, Management management) {
		 try {
			 
				File file = new File(fileName);
				JAXBContext jaxbContext = JAXBContext.newInstance(Management.class);
		 
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				Management customer = (Management) jaxbUnmarshaller.unmarshal(file);
				System.out.println(customer);
		 
			  } catch (JAXBException e) {
				e.printStackTrace();
			  }
	}

	private static Vector<KitchenTool> parseKitchenTools(NodeList list) {
		Vector<KitchenTool> kt = new Vector<KitchenTool>();
		for (int temp = 0; temp < list.getLength(); temp++) {
			Node nNode = list.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				String name = eElement.getElementsByTagName("name").item(0)
						.getTextContent();
				String quantityStr = eElement.getElementsByTagName("quantity")
						.item(0).getTextContent();
				int quantity = Integer.valueOf(quantityStr);
				 
				kt.add(new KitchenTool(name, quantity));
			}

		}
		return kt;
	}
}
