package com.benchire.model;

public class CycleModel {
	

	
	private ChainModel chain;
	private FrameModel frame;
	private HandleBarModel hanldeBar;
	private SeatingModel seating;
	private WheelModel wheelDetail;
	

	public CycleModel() {
	}
	
	
	public CycleModel(ChainModel chain, FrameModel frame, HandleBarModel hanldeBar, SeatingModel seating,
			WheelModel wheelDetail) {		
		this.chain = chain;
		this.frame = frame;
		this.hanldeBar = hanldeBar;
		this.seating = seating;
		this.wheelDetail = wheelDetail;
	}
	
	public ChainModel getChain() {
		return chain;
	}
	public void setChain(ChainModel chain) {
		this.chain = chain;
	}
	public FrameModel getFrame() {
		return frame;
	}
	public void setFrame(FrameModel frame) {
		this.frame = frame;
	}
	public HandleBarModel getHanldeBar() {
		return hanldeBar;
	}
	public void setHanldeBar(HandleBarModel hanldeBar) {
		this.hanldeBar = hanldeBar;
	}
	public SeatingModel getSeating() {
		return seating;
	}
	public void setSeating(SeatingModel seating) {
		this.seating = seating;
	}
	public WheelModel getWheelDetail() {
		return wheelDetail;
	}
	public void setWheelDetail(WheelModel wheelDetail) {
		this.wheelDetail = wheelDetail;
	}
	
}


