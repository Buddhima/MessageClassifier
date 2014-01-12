package com_messages;

import java.io.Serializable;

/**
 * Single Text Message to process
 *
 */
public class Work implements Serializable {

    /**
     * Original Message
     */
    private String job;

    /**
     * Classification Results
     */
    private String context;
    private String gender;
    private String language;
    private String spam;
    private String workId;

    public Work(String message, String workId) {
        this.job = message;
        this.workId = workId;

    }

    public String getJob() {
        return job;
    }

    public String getWorkId() {
        return workId;
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

