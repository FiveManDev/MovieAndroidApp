package com.example.movieandroidapp.model;

public class MessageEvent<T> {

    public T mMessage;

    public MessageEvent(T message) {
        mMessage = message;
    }

    public T getMessage() {
        return mMessage;
    }
}