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

	public static Classifier getClassifier(ServiceType service) {
		if (service.equals(ServiceType.ANALYZE_SENTIMENT))
			return new SpamClassifier();
		else if (service.equals(ServiceType.ANALYZE_GENDER))
			return new GenderClassifier();
		else if (service.equals(ServiceType.ANALYZE_LANGUAGE))
			return new LanguageClassifier();
		else if (service.equals(ServiceType.ANALYZE_CONTEXT))
			return new ContextClassifier();
		else
			return null;
	}

}
