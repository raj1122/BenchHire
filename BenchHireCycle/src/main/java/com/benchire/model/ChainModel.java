package com.benchire.model;

public class ChainModel {

	private int length;
	private int width;
	
	
	public ChainModel() {
	}
	
	
	public ChainModel(int length, int width) {	
		this.length = length;
		this.width = width;
	}
	
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	
}
