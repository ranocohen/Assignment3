package com.bgu.assignment3;

public class FancyStringBuilder {

	private StringBuilder builder;

	public FancyStringBuilder() {
		builder = new StringBuilder();

	}

	public FancyStringBuilder append(String title, String value) {
		builder.append("[")
		.append(title).append("=").append(value).append("]");
		return this;
	}
	public FancyStringBuilder append(String str) {
		builder.append(str);
		return this;
	}
	public FancyStringBuilder append(String title, int value) {
		builder.append("[")
		.append(title).append("=").append(value).append("]");
		return this;
	}
	public FancyStringBuilder append(String title, double value) {
		builder.append("[")
		.append(title).append(" = ").append(value).append("]");
		return this;
	}
	public FancyStringBuilder append(String title, long value) {
		builder.append("[")
		.append(title).append(" = ").append(value).append("]");
		return this;
	}
	public FancyStringBuilder newline(){
		builder.append("\n");
		
		return this;
	}

	@Override
	public String toString() {
		return builder.toString();
	}
}