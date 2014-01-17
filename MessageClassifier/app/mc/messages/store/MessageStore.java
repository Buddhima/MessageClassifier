/**
 * 
 */
package mc.messages.store;

import java.util.List;

import mc.messages.TextMessage;


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
	 * @return Message in given index position (zero based)
	 */
	public TextMessage get(int index);
	
	/**
	 * Get the All messages in the Message store
	 * 
	 * @return List of all Messages
	 */
	public List<TextMessage> getAll();
	
	/**
	 * Get messages count based of type and category in the Message store
	 * Both parameters are mandatory to get result. If no return 0.
	 * 
	 * @param type of the classifier, category relevant to each classification
	 * @return number of Messages
	 */
	public long getMessageCountOf(String type, String category);
	
	/**
	 * Get messages based of type and category in the Message store
	 * Both parameters are mandatory to get result. If no return 'null'.
	 * 
	 * @param type of the classifier, category relevant to each classification
	 * @return List of Messages
	 */
	public List<TextMessage> getMessagesOf(String type, String category);
}
