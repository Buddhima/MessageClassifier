/**
 * 
 */
package mc.messages.store;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import mc.messages.TextMessage;
import mc.messages.store.MessageStore;


/**
 * This is the class to access objectDB message store 
 * 
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
	 * @see com.mc.msgstore.MessageStore#offer(com.mc.messages.TextMessage)
	 */
	@Override
	public boolean offer(TextMessage message) {
		try {
			EntityManager em = emf.createEntityManager();

			// Store TextMessage objects in the database
			em.getTransaction().begin();
			em.persist(message);
			em.getTransaction().commit();

			em.close();

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/* (non-Javadoc)
	 * @see com.mc.messages.store.MessageStore#remove()
	 */
	@Override
	public TextMessage remove() {
		TextMessage textMessage = null;
		try {
			EntityManager em = emf.createEntityManager();

			TypedQuery<TextMessage> query = em.createQuery("SELECT m FROM TextMessage m", TextMessage.class)
                                              .setFirstResult(0)
                                              .setMaxResults(1);
			List<TextMessage> textMessageList = query.getResultList();
			textMessage = textMessageList.get(0);

			em.getTransaction().begin();
			em.remove(textMessage);
			em.getTransaction().commit();

			em.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return textMessage;
	}

	/* (non-Javadoc)
	 * @see com.mc.messages.store.MessageStore#peek()
	 */
	@Override
	public TextMessage peek() {
		TextMessage textMessage = null;
		try {
			EntityManager em = emf.createEntityManager();

			TypedQuery<TextMessage> query = em.createQuery("SELECT m FROM TextMessage m", TextMessage.class)
					                          .setFirstResult(0)
					                          .setMaxResults(1);
			List<TextMessage> textMessageList = query.getResultList();
			textMessage = textMessageList.get(0);

			em.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return textMessage;
	}

	/* (non-Javadoc)
	 * @see com.mc.messages.store.MessageStore#clear()
	 */
	@Override
	public void clear() {
		try {
			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();
			em.createQuery("DELETE FROM TextMessage").executeUpdate();
			em.getTransaction().commit();

			em.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.mc.messages.store.MessageStore#remove(long)
	 */
	@Override
	public TextMessage remove(long index) {
		TextMessage textMessage = null;
		try {
			EntityManager em = emf.createEntityManager();

			textMessage = em.find(TextMessage.class, index);

			em.getTransaction().begin();
			em.remove(textMessage);
			em.getTransaction().commit();

			em.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return textMessage;
	}

	/* (non-Javadoc)
	 * @see com.mc.messages.store.MessageStore#size()
	 */
	@Override
	public long size() {
		long count = 0L;
		try {
			EntityManager em = emf.createEntityManager();

			Query q = em.createQuery("SELECT COUNT(m) FROM TextMessage m");
			count = (Long) q.getSingleResult();
			em.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see com.mc.messages.store.MessageStore#get(int)
	 */
	@Override
	public TextMessage get(int index) {
		TextMessage textMessage = null;
		try {
			EntityManager em = emf.createEntityManager();

//			textMessage = em.find(TextMessage.class, index);
			
			TypedQuery<TextMessage> query = em.createQuery("SELECT m FROM TextMessage m", TextMessage.class)
                    .setFirstResult(index)
                    .setMaxResults(1);
			List<TextMessage> textMessageList = query.getResultList();
			textMessage = textMessageList.get(0);

			em.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return textMessage;
	}

	/* (non-Javadoc)
	 * @see com.mc.msgstore.MessageStore#getAll()
	 */
	@Override
	public List<TextMessage> getAll() {
		List<TextMessage> allMessages = null;
		try {
			EntityManager em = emf.createEntityManager();

			TypedQuery<TextMessage> query = em.createQuery(
					"SELECT m FROM TextMessage m", TextMessage.class);
			allMessages = query.getResultList();

			em.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return allMessages;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mc.messages.store.MessageStore#getMessageCountOf(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public long getMessageCountOf(String type, String category) {
		long count = 0L;
		try {
			EntityManager em = emf.createEntityManager();

			Query q = em.createQuery(
					"SELECT COUNT(m) FROM TextMessage m WHERE "
							+ type.toLowerCase() + "='" + category.toLowerCase() + "'",
					TextMessage.class);
			count = (Long) q.getSingleResult();
			em.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see mc.messages.store.MessageStore#getMessagesOf(java.lang.String, java.lang.String)
	 */
	@Override
	public List<TextMessage> getMessagesOf(String type, String category) {
		List<TextMessage> messages = null;
		try {
			EntityManager em = emf.createEntityManager();

			TypedQuery<TextMessage> query = em.createQuery(
					"SELECT m FROM TextMessage m WHERE " + type.toLowerCase()
							+ "='" + category.toLowerCase() + "'", TextMessage.class);
			messages = query.getResultList();

			em.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return messages;
	}

	/* (non-Javadoc)
	 * @see mc.messages.store.MessageStore#getCategoriesOf(java.lang.String)
	 */
	@Override
	public List<String> getCategoriesOf(String type) {
		List<String> categories = null;
		try {
			EntityManager em = emf.createEntityManager();

			TypedQuery<String> query = em.createQuery("SELECT DISTINCT m."
					+ type.toLowerCase() + " FROM TextMessage m", String.class);
			categories = query.getResultList();

			em.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return categories;
	}
	
}
