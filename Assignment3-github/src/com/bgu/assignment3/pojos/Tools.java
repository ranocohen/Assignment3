package com.bgu.assignment3.pojos;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Tools {
	
	@XmlElement(name="KitchenTool")  
	public List<KitchenTool> getTools() {
		return tools;
	}

	public void setTools(List<KitchenTool> tools) {
		this.tools = tools;
	}

	public Tools() {
		
	}
	
	public Tools(List<KitchenTool> tools) {
		super();
		this.tools = tools;
	}

	List<KitchenTool> tools;
}
