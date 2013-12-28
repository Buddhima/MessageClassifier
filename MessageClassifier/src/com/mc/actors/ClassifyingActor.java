/**
 * 
 */
package com.mc.actors;

import com.mc.classifiers.Classifier;
import com.mc.classifiers.ClassifierFactory;
import com.mc.configs.ClassifiersConfig;

import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Classifying Actor who classify messages according to configured classifier
 * 
 */
public class ClassifyingActor extends UntypedActor {

	/**
	 * Message Classifier
	 */
	Classifier classifier;

	/**
	 * Constructor
	 */
	public ClassifyingActor() {
		// TODO Auto-generated constructor stub
	}

	public ClassifyingActor(String serivce){

		if (serivce.equalsIgnoreCase("context") && ClassifiersConfig.DEPLOY_CONTEXT_CLASSIFIER)
			classifier = ClassifierFactory.getContextClassifier();
		else if (serivce.equalsIgnoreCase("gender") && ClassifiersConfig.DEPLOY_GENDER_CLASSIFIER)
			classifier = ClassifierFactory.getGenderClassifier();
		else if (serivce.equalsIgnoreCase("language") && ClassifiersConfig.DEPLOY_LANGUAGE_CLASSIFIER)
			classifier = ClassifierFactory.getLanguageClassifier();
		else if (serivce.equalsIgnoreCase("spam") && ClassifiersConfig.DEPLOY_SPAM_CLASSIFIER)
			classifier = ClassifierFactory.getSpamClassifier();

	}
	
	
	
	public static Props props(String serivce) {
	    return Props.create(ClassifyingActor.class, serivce);
	  }
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
	 */
	@Override
	public void onReceive(Object arg0) throws Exception {
		// TODO Auto-generated method stub

		if (arg0 instanceof String)
		{
			System.out.println(arg0 + " with " + this.hashCode());
			
			try{
				System.out.println(classifier.classify((String) arg0));
			}
			catch(Exception e)
			{
				getSender().tell(null, getSelf());
			}
		}
		else
			unhandled(arg0);
	}

}
