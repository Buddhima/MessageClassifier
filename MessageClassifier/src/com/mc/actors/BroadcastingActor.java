/**
 * 
 */
package com.mc.actors;

import java.util.Arrays;
import java.util.List;

import com.mc.messages.ResultMessage;
import com.mc.messages.TextMessage;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.BroadcastGroup;

/**
 * Actor for broadcasting and aggregating results
 *
 */
public class BroadcastingActor extends UntypedActor {

	ActorRef broadcastRouter;
	
	TextMessage tm;

	@Override
	public void preStart() throws Exception {

		// Set ClassifierGroup to the context of IntermediateActor
		getContext().actorOf(Props.create(ClassifiersGroup.class), "classifiers");

		// Path list of the actors in ClassifierGroup actor
		List<String> paths = Arrays.asList(
				  "/user/" +self().path().parent().name() + "/" + self().path().name() + "/classifiers/ca1"
				, "/user/" +self().path().parent().name() + "/" + self().path().name() + "/classifiers/ca2"
				, "/user/" +self().path().parent().name() + "/" + self().path().name() + "/classifiers/ca3"
				, "/user/" +self().path().parent().name() + "/" + self().path().name() + "/classifiers/ca4");
		
		broadcastRouter = getContext().actorOf(new BroadcastGroup(paths).props(),
				"broadcastRouter");

	}


	/* (non-Javadoc)
	 * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
	 */
	@Override
	public void onReceive(Object arg0) throws Exception {

		try{			
		
		if(arg0 instanceof TextMessage)
		{
			tm = (TextMessage)arg0;
			
			broadcastRouter.tell(tm.getMessage(), getSelf());
			
		}
		else if(arg0 instanceof ResultMessage)
		{
			// TODO: Logic to aggregate results
			
			
			// Need to tell IntermediateActor since it keeps waiting on this
			getSender().tell(tm, getSelf());
			
		}
		
		}catch(Exception e){
			getSender().tell(new akka.actor.Status.Failure(e), getSelf());
			
			throw e;
		}

	}

}
