package com.zonetwyn.projects.bonplant.utils;

public class Event {

    public static final int SUBJECT_MAIN_OPEN_BASKET = 120;
    private int subject;
    private Object data;

    public Event() {
    }

    public Event(int subject, Object data) {
        this.subject = subject;
        this.data = data;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
