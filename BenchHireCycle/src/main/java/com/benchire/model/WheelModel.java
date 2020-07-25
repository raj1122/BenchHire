package com.benchire.model;

public class WheelModel {
	
	

	public WheelModel() {
	}
	
	private int spokes;
	private int rim;
	private int tube;
	private int tyre;
	
	
	public WheelModel(int spokes, int rim, int tube, int tyre) {	
		this.spokes = spokes;
		this.rim = rim;
		this.tube = tube;
		this.tyre = tyre;
	}
	
	
	public int getSpokes() {
		return spokes;
	}
	public void setSpokes(int spokes) {
		this.spokes = spokes;
	}
	public int getRim() {
		return rim;
	}
	public void setRim(int rim) {
		this.rim = rim;
	}
	public int getTube() {
		return tube;
	}
	public void setTube(int tube) {
		this.tube = tube;
	}
	public int getTyre() {
		return tyre;
	}
	public void setTyre(int tyre) {
		this.tyre = tyre;
	}

	
}
