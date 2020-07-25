package com.benchire.services;

import java.awt.MultipleGradientPaint.CycleMethod;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.benchire.model.CycleModel;

public class GenerateCost implements Runnable {

	protected BlockingQueue queue = null;
	int noVehicle;
	String monthName = null;
	HashMap<String, HashMap<String, CycleModel>> yearMap;
	String yearSearch;

	public GenerateCost(BlockingQueue queue, String noVehicle, String monthName, String yearSearch,
			HashMap<String, HashMap<String, CycleModel>> yearMap) {
		this.queue = queue;
		this.noVehicle = Integer.parseInt(noVehicle);
		this.monthName = monthName;
		this.yearMap = yearMap;
		this.yearSearch = yearSearch;
	}

	public void run() {
		// TODO Auto-generated method stub

		startCalculatingCost();

	}

	private void startCalculatingCost() {

		while (noVehicle > 0) {
			try {

				CycleModel currObj = (CycleModel) queue.take();
				calculateCost(currObj);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	private void calculateCost(CycleModel currObj) {

		int sum = 0;
		int chainLength = currObj.getChain().getLength();
		int chainWidth = currObj.getChain().getWidth();
		int frameSeat = currObj.getFrame().getSeatTube();
		int frameTube = currObj.getFrame().getTopTube();
		int handleBar = currObj.getHanldeBar().getHandleBar();
		int handleTape = currObj.getHanldeBar().getHandleBarTape();
		int seat = currObj.getSeating().getSeat();
		int rim = currObj.getWheelDetail().getRim();
		int spokes = currObj.getWheelDetail().getSpokes();
		int tube = currObj.getWheelDetail().getTube();
		int tyre = currObj.getWheelDetail().getTyre();

		ObjectMapper Obj = new ObjectMapper();

		sum += chainLength + chainWidth + frameSeat + frameTube + handleBar + handleTape + rim + spokes + tube + tyre;
		System.out.println("Cycle Cost:" + sum);
		String jsonStr =null;
		try {
			jsonStr = Obj.writerWithDefaultPrettyPrinter().writeValueAsString(currObj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return;
		} catch (JsonMappingException e) {

			e.printStackTrace();
			return;
		} catch (IOException e) {

			e.printStackTrace();
			return;
		}


		System.out.println(jsonStr);

	}

}
