/**
 * 
 */
package com.mc.classifiers.uclassify;

import org.onesun.textmining.uclassify.ServiceType;

/**
 * Classifying Spam messages using uClassify
 * 
 */
public final class SpamClassifier extends UClassifier {

	/**
	 * 
	 */
	public SpamClassifier() {
		super();
		this.service = ServiceType.ANALYZE_SENTIMENT; // TODO: has to change ASAP to a Spam Classifier

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
