/**
 * 
 */
package com.mc.msgstore;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.mc.messages.TextMessage;
import com.mc.msgstore.MessageStore;

/**
 * Methods to access objectDB message store 
 * @author akila
 *
 */
public class ObjectDBMsgStore implements MessageStore {

	private static EntityManagerFactory emf;

	static {
		try {
			emf = Persistence
					.createEntityManagerFactory("$objectdb/db/classified_messages.odb");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
    
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mc.msgstore.MessageStore#storeMsg(com.mc.messages.TextMessage)
	 */
	@Override
	public void storeMessage(TextMessage message) {
		EntityManager em = emf.createEntityManager();

		// Store TextMessage objects in the database
		em.getTransaction().begin();
		em.persist(message);
		em.getTransaction().commit();

		em.close();
	}

	/* (non-Javadoc)
	 * @see com.mc.msgstore.MessageStore#getAllMessages()
	 */
	@Override
	public List<TextMessage> getAllMessages() {
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<TextMessage> query = em.createQuery("SELECT msg FROM TextMessage msg",
				TextMessage.class);
		List<TextMessage> allMessages = query.getResultList();
		
		em.close();
		
		return allMessages;
	}
	
}
