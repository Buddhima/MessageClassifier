package com_messages;

import models.com.mc.messages.TextMessage;

import java.io.Serializable;

/**
 * Single Text Message to process
 *
 */
public class Work implements Serializable {


    private String workId;
    private TextMessage message;

    public Work(String message, String workId) {
        this.message=new TextMessage(message);
        this.workId = workId;

    }

    public String getJob() {
        return message.getMessage();
    }

    public String getWorkId() {
        return workId;
    }




}

