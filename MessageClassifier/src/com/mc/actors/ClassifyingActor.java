/**
 * 
 */
package com.mc.actors;

import com.mc.classifiers.Classifier;
import com.mc.classifiers.ClassifierFactory;
import com.mc.configs.ClassifiersConfig;
import com.mc.messages.ResultMessage;

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
	
	String service;

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

		this.service = serivce;
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

		if (arg0 instanceof String)
		{
			
			try{
				String result = classifier.classify((String) arg0);
				System.out.println(result);
				
				getSender().tell(new ResultMessage(service, result), getSelf());
			}
			catch(Exception e)
			{
				getSender().tell(new ResultMessage(service), getSelf());
			}
		}
		else
			unhandled(arg0);
	}

}
