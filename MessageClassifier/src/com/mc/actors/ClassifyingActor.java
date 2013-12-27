/**
 * 
 */
package com.mc.actors;

import com.mc.classifiers.Classifier;

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

	/* (non-Javadoc)
	 * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
	 */
	@Override
	public void onReceive(Object arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
