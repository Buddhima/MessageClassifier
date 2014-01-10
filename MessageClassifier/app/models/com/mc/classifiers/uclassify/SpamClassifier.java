/**
 * 
 */
package models.com.mc.classifiers.uclassify;

import models.org.onesun.textmining.uclassify.ServiceType;

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
		this.service = ServiceType.ANALYZE_LEGITIMATE;

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
