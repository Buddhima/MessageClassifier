/**
 * 
 */
package models.com.mc.actors;

import mc.messages.TextMessage;
import mc.messages.store.ObjectDBMsgStore;


import akka.actor.UntypedActor;

/**
 * Actor for collecting classified messages
 * 
 * 
 */
public class MessageCollectingActor extends UntypedActor {

	TextMessage tm;
	ObjectDBMsgStore odbMsgStore;

	/* (non-Javadoc)
	 * @see akka.actor.UntypedActor#preStart()
	 */
	@Override
	public void preStart() throws Exception {
		super.preStart();

		// Set ObjectDBMsgStore instance
		odbMsgStore = new ObjectDBMsgStore();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
	 */
	@Override
	public void onReceive(Object arg0) throws Exception {
		if (arg0 instanceof TextMessage) {
			tm = (TextMessage) arg0;
			System.out.println("Message - " + tm.toString());
			
			//save messages
			odbMsgStore.offer(tm);
		}

	}

}
