package models.com.mc.workers;

import models.com.mc.messages.TextMessage;

import java.io.Serializable;

/**
 * Single Text Message to process
 *
 */
public class Work implements Serializable {


    private String workId;
    private TextMessage job;

    public Work(String message, String workId) {
        this.job=new TextMessage(message);
        this.workId = workId;

    }

    public TextMessage getJob() {
        return job;
    }

    public String getWorkId() {
        return workId;
    }




}

