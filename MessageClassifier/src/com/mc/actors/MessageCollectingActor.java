/**
 * 
 */
package com.mc.actors;

import com.mc.messages.TextMessage;
import com.mc.msgstore.ObjectDBMsgStore;

import akka.actor.UntypedActor;

/**
 * Actor for collecting classified messages
 * 
 * @author akila
 */
public class MessageCollectingActor extends UntypedActor {

	TextMessage tm;

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
			ObjectDBMsgStore odbMsgStore = new ObjectDBMsgStore();
			odbMsgStore.storeMessage(tm);
		}

	}

}
