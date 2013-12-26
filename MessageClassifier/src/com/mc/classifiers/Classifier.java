/**
 * 
 */
package com.mc.classifiers;

/**
 * @author BUDDHIMA
 *
 */
public interface Classifier {

	/**
	 * Classifying logic should be here
	 *
	 * @param message
	 * @return
	 */
	public String classify(String message);
}
