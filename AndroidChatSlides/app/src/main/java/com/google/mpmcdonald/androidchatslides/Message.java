package com.google.mpmcdonald.androidchatslides;

import com.firebase.client.DataSnapshot;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mpmcdonald on 1/17/15.
 */
public class Message {

    private String user;
    private String text;

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public Message(String user, String text){
        this.user = user;
        this.text = text;
    }
}
