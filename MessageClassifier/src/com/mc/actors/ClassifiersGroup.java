package com.mc.actors;

import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Group of classifiers to broadcast message
 * 
 */
public class ClassifiersGroup extends UntypedActor{

	public ClassifiersGroup() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void preStart() throws Exception {
		// TODO Auto-generated method stub
//		super.preStart();
		
		getContext().actorOf(Props.create(ClassifyingActor.class), "w1");
	    getContext().actorOf(Props.create(ClassifyingActor.class), "w2");
	    getContext().actorOf(Props.create(ClassifyingActor.class), "w3");
	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
