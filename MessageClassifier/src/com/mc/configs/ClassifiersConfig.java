/**
 * 
 */
package com.mc.configs;

/**
 * Store & Retrieve configurations relevant to creating Classifying Actors
 *
 */
public final class ClassifiersConfig {

	public static boolean DEPLOY_CONTEXT_CLASSIFIER = true;
	public static boolean DEPLOY_GENDER_CLASSIFIER = true;
	public static boolean DEPLOY_LANGUAGE_CLASSIFIER = true;
	public static boolean DEPLOY_SPAM_CLASSIFIER = true;
	
	public static int CLASSIFIER_SERVICE_TIMEOUT = 5;
	
	/**
	 * 
	 */
	public ClassifiersConfig() {
		// TODO Auto-generated constructor stub
	}

}
