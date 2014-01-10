package models.com.mc.actors;

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

		// Statically bind all types of actors to the broadcasting group
		getContext().actorOf(ClassifyingActor.props("context"), "ca1");
	    getContext().actorOf(ClassifyingActor.props("gender"), "ca2");
	    getContext().actorOf(ClassifyingActor.props("language"), "ca3");
	    getContext().actorOf(ClassifyingActor.props("spam"), "ca4");
	    
	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
