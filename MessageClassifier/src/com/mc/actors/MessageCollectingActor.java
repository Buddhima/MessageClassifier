/**
 * 
 */
package com.mc.actors;

import com.mc.messages.TextMessage;

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
			System.out.println("Text Message: " + tm.getMessage()
					+ ", context-" + tm.getContext() + ", gender-"
					+ tm.getGender() + ", language-" + tm.getLanguage()
					+ ", spam-" + tm.getSpam());
		}

	}

}
