/**
 * 
 */
package com.mc.classifiers;

import org.onesun.textmining.uclassify.ServiceType;

import com.mc.classifiers.uclassify.ContextClassifier;
import com.mc.classifiers.uclassify.GenderClassifier;
import com.mc.classifiers.uclassify.LanguageClassifier;
import com.mc.classifiers.uclassify.SpamClassifier;

/**
 * Factory for generating Classifiers
 * 
 */
public final class ClassifierFactory {

	/**
	 * 
	 */
	public ClassifierFactory() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Factory method to get spam classifier
	 * @return
	 */
	public static Classifier getSpamClassifier() {

		return new SpamClassifier();

	}

	/**
	 * Factory method to get gender classifier
	 * @return
	 */
	public static Classifier getGenderClassifier() {

		return new GenderClassifier();

	}
	
	/**
	 * Factory method to get language classifier
	 * @return
	 */
	public static Classifier getLanguageClassifier() {

		return new LanguageClassifier();

	}
	
	/**
	 * Factory method to get context classifier
	 * @return
	 */
	public static Classifier getContextClassifier() {

		return new ContextClassifier();

	}
}
