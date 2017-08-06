package com.lifewithgoal.entity;


import java.util.Date;

/**
 * Created by darshanas on 8/6/2017.
 */
public class Notification {

    private int id;
    private String to;
    private String subject;
    private String message;
    private String template_name;
    private int message_type;
    private int delivery_state;
    private int fail_attempts;
    private Date message_time;
    private Date delivery_completed_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public int getMessage_type() {
        return message_type;
    }

    public void setMessage_type(int message_type) {
        this.message_type = message_type;
    }

    public int getDelivery_state() {
        return delivery_state;
    }

    public void setDelivery_state(int delivery_state) {
        this.delivery_state = delivery_state;
    }

    public int getFail_attempts() {
        return fail_attempts;
    }

    public void setFail_attempts(int fail_attempts) {
        this.fail_attempts = fail_attempts;
    }

    public Date getMessage_time() {
        return message_time;
    }

    public void setMessage_time(Date message_time) {
        this.message_time = message_time;
    }

    public Date getDelivery_completed_time() {
        return delivery_completed_time;
    }

    public void setDelivery_completed_time(Date delivery_completed_time) {
        this.delivery_completed_time = delivery_completed_time;
    }
}
