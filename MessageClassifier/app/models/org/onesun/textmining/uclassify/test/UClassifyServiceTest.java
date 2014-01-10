/*
 **
	Copyright 2010 Udaya Kumar (Udy)

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
 **	
 */
package models.org.onesun.textmining.uclassify.test;

import java.util.Map;
import java.util.Map.Entry;

import models.org.onesun.textmining.uclassify.ResultHandler;
import models.org.onesun.textmining.uclassify.ServiceType;
import models.org.onesun.textmining.uclassify.UClassifyService;

public class UClassifyServiceTest {
	public void doTest(){
		for(ServiceType service : ServiceType.values()){
			String text = 
				"A new survey has been launched in the United Kingdom to unearth the true nature of cyber stalking in the country." 
				+ "\n"
				+ "The Network for Surviving Stalking has issued an \"Electronic Communication Harassment Observation\" or ECHO questionnaire in collaboration with the scientists at the University of Bedfordshire."
				+ "\n"
				+ "The survey has been commissioned to classify those who have been stalked on web and how according to a number of criteria." 
				+ "\n"
				+ "The questionnaire will ask respondents if they were harassed or threatened on a social networking site such as Facebook, Twitter and LinkedIn, email service or Instant Messaging."
				+ "\n"
				+ "\"At the moment there are very few widely agreed guidelines or rules about how to behave online - we hope Echo will define behaviours that are generally experienced as anti-social or likely to cause distress in online communication.\" said Dr. Emma Short, head of the project ECHO."
				+ "\n"
				+ "The survey has been launched after Crown Prosecution Service (CPS) of the UK revealed a set of new guidelines for law enforcers tough on stalkers on web."
				+ "\n"
				+ "Read more: http://www.itproportal.com/security/news/article/2010/9/25/study-reveal-nature-cyberstalking-uk/#ixzz10YckSmCr";
			
			
			// *******************************************************************
			// DO NOT FORGET TO SET YOUR OWN KEY HERE BEFORE RUNNING APP
			// You can get a key from: http://www.uclassify.com/Register.aspx
			// *******************************************************************
			UClassifyService.setUClassifyReadAccessKey("heUsAtZSyHafkiBexGIG1JlQKL4");
			// *******************************************************************
			
			UClassifyService uClassifyService = new UClassifyService(text, service, new ResultHandler() {
				
				@Override
				public void process(ServiceType serviceType, Map<String, Double> results) {
					System.out.println(
							"---------------------------------------------------------------------\n" 
							+
							serviceType.getUrl() + " <<<>>> " + serviceType.getClassifier() + "\n" +
							"---------------------------------------------------------------------\n"
						);

					String bestKey = keyOfHighestValue(results);
					Double bestValue = results.get(bestKey);

					System.out.println("Best Selection:" + bestKey + ", Score:" + bestValue);


				}
			});
			
			try{
				uClassifyService.process();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * @return the key of the highest value of this map. Note: if this map has
	 *         multiple values that are the highest, it returns one of its
	 *         corresponding keys.
	 */
	public <K, V extends Comparable<V>> K keyOfHighestValue(Map<K, V> map) {
	    K bestKey = null;
	    V bestValue = null;
	    for (Entry<K, V> entry : map.entrySet()) {
	        if (bestValue == null || entry.getValue().compareTo(bestValue) > 0) {
	            bestKey = entry.getKey();
	            bestValue = entry.getValue();
	        }
	    }
	    return bestKey;
	}
	
	public static void main(String[] args) {
		UClassifyServiceTest miningTest = new UClassifyServiceTest();
		
		miningTest.doTest();
	}
}
