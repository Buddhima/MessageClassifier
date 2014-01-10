/**
 * 
 */
package models.com.mc.messages.store;

import java.util.List;

import models.com.mc.messages.TextMessage;


/**
 * This is the interface for the MessageClassifier Message Store 
 * Message Store is used to store Messages
 * 
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
	 * Retrieves and removes the first Message in this store. 
	 * Message ordering will depend on the underlying implementation
	 * 
	 * @return first message in the store
	 */
	public TextMessage remove();
	
	/**
	 * Retrieves but not removes the first Message in this store. 
	 * Message ordering will depend on the underlying implementation
	 * 
	 * @return first message context in the store
	 */
	public TextMessage peek();
	
	/**
	 * Delete all the Messages in the Message Store
	 * 
	 */
	public void clear();
	
	/**
	 * Delete and return the Message with given Message id
	 * 
	 * @param index position of the message
	 * @return Message in given index position
	 */
	public TextMessage remove(long index);
	
	/**
	 * Returns the number of Messages in this store.
	 * 
	 * @return the number of Messages in this Store
	 */
	public long size();

	/**
	 * Return the Message in given index position
	 * 
	 * @param index position of the message
	 * @return Message in given index position
	 */
	public TextMessage get(int index);
	
	/**
	 * Get the All messages in the Message store
	 * 
	 * @return List of all Messages
	 */
	public List<TextMessage> getAll();
	
}
