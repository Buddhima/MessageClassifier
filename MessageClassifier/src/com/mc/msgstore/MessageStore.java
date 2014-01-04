/**
 * 
 */
package com.mc.msgstore;

import java.util.List;

import com.mc.messages.TextMessage;

/**
 * @author akila
 *
 */
public interface MessageStore {
	
	/**
	 * Store a message in database
	 * 
	 * @param message
	 * @return
	 */
	public void storeMessage(TextMessage message);
	
	/**
	 * Retrieve all message in database
	 * 
	 * @return List<TextMessage>
	 */
	public List<TextMessage> getAllMessages();
}
