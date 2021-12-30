package com.gordey.console.lib;

import android.view.MotionEvent;

public class ConsoleMotionEvent {

    public enum Action {
        DOWN,
        MOVE,
        UP,
        NULL
    }

    private int letterWidth, letterHeight;
    private int screenWidth, screenHeight;
    private int indentX;

    MotionEvent event;

    public ConsoleMotionEvent(int letterWidth, int letterHeight, int screenWidth, int screenHeight) {
        this.letterWidth = letterWidth;
        this.letterHeight = letterHeight;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.indentX = (screenWidth - screenWidth / letterWidth * letterWidth) / 2;
    }

    void setEvent(MotionEvent event) {
        this.event = event;
    }

    public Action getAction() {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return Action.DOWN;
            case MotionEvent.ACTION_MOVE:
                return Action.MOVE;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                return Action.UP;
        }
        return Action.NULL;
    }

    public float getX() {
        return (event.getX() - indentX) / letterWidth;
    }

    public float getY() {
        return event.getY() / letterHeight;
    }
}
