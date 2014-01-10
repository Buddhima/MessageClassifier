/**
 * 
 */
package models.com.mc.classifiers.uclassify;

import models.org.onesun.textmining.uclassify.ServiceType;

/**
 * Classifying Context of messages using uClassify
 * 
 */
public final class ContextClassifier extends UClassifier {

	/**
	 * 
	 */
	public ContextClassifier() {
		super();
		this.service = ServiceType.ANALYZE_CONTEXT;
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
