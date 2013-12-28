/**
 * 
 */
package com.mc.actors;

import java.util.Arrays;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.BroadcastGroup;

/**
 * Intermediate Actor for taking responsibility of consuming a single message
 * 
 */
public final class IntermediateActor extends UntypedActor {

	ActorRef broadcastRouter;

	@Override
	public void preStart() throws Exception {

		// Set ClassifierGroup to the context of IntermediateActor
		getContext().actorOf(Props.create(ClassifiersGroup.class), "classifiers");

		// Path list of the actors in ClassifierGroup actor
		List<String> paths = Arrays.asList("/user/" + self().path().name() + "/classifiers/ca1", "/user/" + self().path().name() + "/classifiers/ca2", "/user/" + self().path().name() + "/classifiers/ca3", "/user/" + self().path().name() + "/classifiers/ca4");
		
		broadcastRouter = getContext().actorOf(new BroadcastGroup(paths).props(),
				"broadcastRouter");

	}

	@Override
	public void onReceive(Object arg0) throws Exception {

		if(arg0 instanceof String)
		{
			broadcastRouter.tell(arg0, getSelf());
		}

		// TODO: Logic to aggregate results

	}
	
	/**
	 * Test main - SAMPLE LOGIC FOR MASTER ACTOR
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			final ActorSystem system = ActorSystem.create("helloakka");

	        // Create the 'IntermediateActor' actor, *need to specify unique names
	        final ActorRef iActor = system.actorOf(Props.create(IntermediateActor.class), "iActor");
	        
	        iActor.tell("Hello", ActorRef.noSender());
	        
	        final ActorRef iActor2 = system.actorOf(Props.create(IntermediateActor.class), "iActor2");
	        
	        iActor2.tell("World", ActorRef.noSender());
	        

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
