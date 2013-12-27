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
import akka.routing.BroadcastPool;

/**
 * Intermediate Actor for taking responsibility of consuming a single message
 * 
 */
public final class IntermediateActor extends UntypedActor {

	ActorRef router14;

	@Override
	public void preStart() throws Exception {
		// system.actorOf(Props.create(ClassifiersGroup.class), "classifiers");
/*
		context().actorOf(Props.create(ClassifiersGroup.class), "classifiers");

		List<String> paths = Arrays.asList("/user/classifiers/w1",
				"/user/classifiers/w2", "/user/classifiers/w3");

		router14 = getContext().actorOf(new BroadcastGroup(paths).props(),
				"router14");
*/
	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		// TODO Auto-generated method stub

		ActorRef router12 = getContext()
				.actorOf(
						new BroadcastPool(5).props(Props
								.create(ClassifyingActor.class)), "router12");
		
		router12.tell(arg0, getSelf());

	}
	
	/**
	 * Test main
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			final ActorSystem system = ActorSystem.create("helloakka");

	        // Create the 'greeter' actor
	        final ActorRef iActor = system.actorOf(Props.create(IntermediateActor.class), "iActor");
	        
	        iActor.tell("Hello", ActorRef.noSender());
	        

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
