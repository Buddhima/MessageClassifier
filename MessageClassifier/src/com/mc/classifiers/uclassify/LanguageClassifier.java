/**
 * 
 */
package com.mc.classifiers.uclassify;

import org.onesun.textmining.uclassify.ServiceType;

/**
 * Classifying Language of messages using uClassify
 * 
 */
public final class LanguageClassifier extends UClassifier {

	/**
	 * 
	 */
	public LanguageClassifier() {

		super();
		this.service = ServiceType.ANALYZE_LANGUAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mc.classifiers.Classifier#classify(java.lang.String)
	 */
	@Override
	public String classify(String message) {
		// TODO Auto-generated method stub
		return super.classify(message);
	}

}
