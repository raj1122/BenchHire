package com.benchire;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.benchire.model.CycleModel;

import com.benchire.services.CycleServices;
import com.benchire.services.GenerateCost;

public class AppApplication {

	String jsonInputfileName = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if(args.length !=4)
		{
			System.out.println(args.toString());
			return;
		}
		
		AppApplication obj = new AppApplication();
		obj.jsonInputfileName = "pricing.json";
		HashMap<String, HashMap<String, CycleModel>> yearMap = obj.startReadingCycle();
		if(yearMap.size()<1)
		{
			System.out.println("Unable to read json file");
		}
		else
		{
			
			 for (String arg : args) {

                 System.out.print(arg+"\t");

           }
			 System.out.println();

			
			BlockingQueue queue = new ArrayBlockingQueue(1024);
			CycleServices cycleServ = new CycleServices(queue, args[1],args[2].toLowerCase(),args[3], yearMap);
			GenerateCost generateCost = new GenerateCost(queue, args[1],args[2].toLowerCase(),args[3],yearMap);
			
			new Thread(cycleServ).start();
	        new Thread(generateCost).start();
			
		}

	}

	private HashMap<String, HashMap<String, CycleModel>> startReadingCycle() {

		HashMap<String, CycleModel> monthMap = null;
		HashMap<String, HashMap<String, CycleModel>> yearMap = new HashMap<String, HashMap<String, CycleModel>>();
		

		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader(jsonInputfileName));
			JSONObject jsonObject = (JSONObject) obj;

//			Map address = ((Map) jsonObject.get("year"));
//			
//			Iterator<Map.Entry> itr1 = address.entrySet().iterator(); 
//	        while (itr1.hasNext()) { 
//	            Map.Entry pair = itr1.next(); 
//	            System.out.println(pair.getKey() + " : " + pair.getValue()); 
//	        } 

			
			Iterator<Map.Entry> monthItr = null;

			JSONArray yearList = (JSONArray) jsonObject.get("year");
			Iterator yearItr = yearList.iterator();
			
			String yearString=null;
			while (yearItr.hasNext()) {
				Object nextObject =yearItr.next(); 
				monthItr = ((Map) nextObject).entrySet().iterator();
				
//				Map.Entry pairYear = (Entry) itrMnth.next();
				while (monthItr.hasNext()) {
					Map.Entry pair = monthItr.next();

					System.out.println(pair.getKey() + " > " + pair.getValue());
					if(pair.getKey().toString().equals("yearId")) {
						yearString= pair.getValue().toString();
					}
					if (pair.getKey().toString().equals("month")) {

						JSONArray monthList = (JSONArray) pair.getValue();
						Iterator mnthDtlItrList = monthList.iterator();


						monthMap = new HashMap<String, CycleModel>();
						while (mnthDtlItrList.hasNext()) {		                	
							Iterator itrMnth = ((Map) mnthDtlItrList.next()).entrySet().iterator();
							
							CycleModel cycleObj =null;
							String monthName=null;
							while (itrMnth.hasNext()) {
								Map.Entry pairMonth = (Entry) itrMnth.next();
								System.out.println(pairMonth.getKey() + ">>" + pairMonth.getValue());
								
								String cycleDataString = pairMonth.getValue().toString();
								String cycleKeyString = pairMonth.getKey().toString();
								if(cycleKeyString.equals("monthName")) {
									monthName = cycleDataString;
								}
								if (cycleKeyString.equals("monthDetail")) {
									ObjectMapper mapper = new ObjectMapper();
									
									try {
										cycleObj = mapper.readValue(cycleDataString, CycleModel.class);										
									} catch (JsonGenerationException e) {
										e.printStackTrace();
										monthName=null;
										cycleObj=null;
									} catch (JsonMappingException e) {
										e.printStackTrace();
									} 
								}

							}
							if(monthName !=null &&cycleObj!= null)
							{
								monthMap.put(monthName.toLowerCase(), cycleObj);
							}
							

						}
						
						

					}
				}/*end of month map*/
				if(monthMap.size()<0)
				{
					yearMap.put(yearString, monthMap);
				}
				
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return yearMap;

	}

}
