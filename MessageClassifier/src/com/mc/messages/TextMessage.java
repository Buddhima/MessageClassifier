package com.mc.messages;

/**
 * Single Text Message to process
 * 
 */
public class TextMessage {

	/**
	 * Original Message
	 */
	private String message;

	/**
	 * Classification Results
	 */
	private String context;
	private String gender;
	private String language;
	private String spam;
	
	public TextMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * @return the context
	 */
	public String getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(String context) {
		this.context = context;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the spam
	 */
	public String getSpam() {
		return spam;
	}

	/**
	 * @param spam the spam to set
	 */
	public void setSpam(String spam) {
		this.spam = spam;
	}

	
}
