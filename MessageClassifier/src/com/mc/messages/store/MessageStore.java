/**
 * 
 */
package com.mc.messages.store;

import java.util.List;

import com.mc.messages.TextMessage;

/**
 * @author akila
 *
 */
public interface MessageStore {
	
	/**
	 * Inserts the Message into this store if it is possible to do so
	 * immediately without violating capacity restrictions.
	 * 
	 * @param TextMessage
	 */
	public boolean offer(TextMessage message);
	
	/**
	 * Get the All messages in the Message store
	 * 
	 * @return List of all Messages
	 */
	public List<TextMessage> getAll();
}
