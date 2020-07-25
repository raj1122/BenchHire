package com.benchire.model;

public class FrameModel {
	private int topTube;
	private int seatTube;
	
	

	public FrameModel() {
	}
	public FrameModel(int topTube, int seatTube) {
		this.topTube = topTube;
		this.seatTube = seatTube;
	}
	public int getTopTube() {
		return topTube;
	}
	public void setTopTube(int topTube) {
		this.topTube = topTube;
	}
	public int getSeatTube() {
		return seatTube;
	}
	public void setSeatTube(int seatTube) {
		this.seatTube = seatTube;
	}
	

}
