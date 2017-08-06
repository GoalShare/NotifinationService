package com.lifewithgoal.entity;

/**
 * Created by darshanas on 8/6/2017.
 */
public class Email {

    private final String to;
    private final String subject;
    private final String body;

    public Email(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
