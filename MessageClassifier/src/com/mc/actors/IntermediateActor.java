/**
 * 
 */
package com.mc.actors;

import akka.actor.UntypedActor;
import akka.routing.BroadcastGroup;

/**
 * Intermediate Actor for taking responsibility of consuming a single message
 *
 */
public final class IntermediateActor extends UntypedActor {

	BroadcastGroup broadcastGroup;

	@Override
	public void onReceive(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
