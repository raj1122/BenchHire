package com.benchire.model;

public class HandleBarModel {
	

	public HandleBarModel() {
	}
	private int handleBar;
	private int handleBarTape;
	
	
	public HandleBarModel(int handleBar, int handleBarTape) {
		this.handleBar = handleBar;
		this.handleBarTape = handleBarTape;
	}
	public int getHandleBar() {
		return handleBar;
	}
	public void setHandleBar(int handleBar) {
		this.handleBar = handleBar;
	}
	public int getHandleBarTape() {
		return handleBarTape;
	}
	public void setHandleBarTape(int handleBarTape) {
		this.handleBarTape = handleBarTape;
	}
	
	

}
