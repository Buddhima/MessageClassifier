/**
 * 
 */
package models.com.mc.classifiers.uclassify;

import models.org.onesun.textmining.uclassify.ServiceType;

/**
 * Classifying Gender of messages using uClassify
 * 
 */
public final class GenderClassifier extends UClassifier {

	/**
	 * Constructor
	 */
	public GenderClassifier() {

		super();
		this.service = ServiceType.ANALYZE_GENDER;
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
