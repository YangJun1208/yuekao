package com.bwei.day02;

public class MessageEvent {

    Object object;
    String id;

    public MessageEvent(Object object, String id) {
        this.object = object;
        this.id = id;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
