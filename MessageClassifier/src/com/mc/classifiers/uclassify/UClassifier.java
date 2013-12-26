package com.mc.classifiers.uclassify;

import java.util.Map;
import java.util.Map.Entry;

import org.onesun.textmining.uclassify.ResultHandler;
import org.onesun.textmining.uclassify.ServiceType;
import org.onesun.textmining.uclassify.UClassifyService;

import com.mc.classifiers.Classifier;

/**
 * Abstract level of uClassifier with classifying functionality
 * 
 */
abstract class UClassifier implements Classifier {

	/**
	 * Type of the service providing
	 */
	protected ServiceType service;

	/**
	 * API Read key of the account
	 */
	protected String key;

	/**
	 * Best suited selection recorded here
	 */
	private String bestResult;

	public UClassifier() {
		this.key = "heUsAtZSyHafkiBexGIG1JlQKL4";

		UClassifyService.setUClassifyReadAccessKey(this.key);
	}

	/**
	 * Classifying the message
	 */
	@Override
	public String classify(String message) {

		UClassifyService uClassifyService = new UClassifyService(message,
				service, new ResultHandler() {

					@Override
					public void process(ServiceType serviceType,
							Map<String, Double> results) {
						/*
						 * System.out.println(
						 * "---------------------------------------------------------------------\n"
						 * + serviceType.getUrl() + " <<<>>> " +
						 * serviceType.getClassifier() + "\n" +
						 * "---------------------------------------------------------------------\n"
						 * );
						 */

						String bestKey = keyOfHighestValue(results);
						Double bestValue = results.get(bestKey);

						bestResult = bestKey;
						// System.out.println("Best Selection:" + bestKey +
						// ", Score:" + bestValue);

					}

					/**
					 * @return the key of the highest value of this map. Note:
					 *         if this map has multiple values that are the
					 *         highest, it returns one of its corresponding
					 *         keys.
					 */
					public <K, V extends Comparable<V>> K keyOfHighestValue(
							Map<K, V> map) {
						K bestKey = null;
						V bestValue = null;
						for (Entry<K, V> entry : map.entrySet()) {
							if (bestValue == null
									|| entry.getValue().compareTo(bestValue) > 0) {
								bestKey = entry.getKey();
								bestValue = entry.getValue();
							}
						}
						return bestKey;
					}
				});

		try {
			uClassifyService.process();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bestResult;
	}
}
