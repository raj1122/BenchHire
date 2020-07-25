package com.benchire.services;

import java.awt.MultipleGradientPaint.CycleMethod;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.benchire.model.CycleModel;

public class CycleServices implements Runnable {

	protected BlockingQueue queue = null;
	int noVehicle ;
	String monthName = null;
	HashMap<String, HashMap<String, CycleModel>> yearMap;
	String yearSearch;

	public CycleServices(BlockingQueue queue, String noVehicle, String monthName, String yearSearch,
			HashMap<String, HashMap<String, CycleModel>> yearMap) {
		this.queue = queue;
		this.noVehicle = Integer.parseInt(noVehicle);
		this.monthName = monthName;
		this.yearMap = yearMap;
		this.yearSearch = yearSearch;
	}

	public void run() {
		// TODO Auto-generated method stub

		startReadingCycle();

	}

	private void startReadingCycle() {

		while (noVehicle>0) {
			
			if (yearMap.containsKey(yearSearch)) {
				HashMap<String, CycleModel> monthMap = yearMap.get(yearSearch);
				if (monthMap.containsKey(monthName)) {
					CycleModel cycleModel = monthMap.get(monthName);

					 try {
						queue.put(cycleModel);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("No month found in MonthMap" + monthName);
				}

			} else {
				System.out.println("No data found in Year Map"+ this.yearSearch);
			}

			noVehicle--;
		}
	}

}
