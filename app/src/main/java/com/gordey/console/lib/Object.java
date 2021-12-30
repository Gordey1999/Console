package com.gordey.console.lib;

import android.graphics.Rect;
import android.view.MotionEvent;

public abstract class Object {
    public Rect position;
    public int depth;
    public ConsoleActivity activity;

    public Object() {
        this(0, 0);
    }

    public Object(int x, int y) {
        this(x, y, 0, 0);
    }
    public Object(int x, int y, int width, int height) {
        this(x, y, width, height, 0);
    }

    public Object(int x, int y, int width, int height, int depth) {
        this.position = new Rect(x, y, x + width, y + height);
        this.depth = depth;
    }

    public Object(Rect position) {
        this(position, 0);
    }

    public Object(Rect position, int depth) {
        this.position = new Rect(position);
        this.depth = depth;
    }


    final public void setPosition(int x, int y) {
        position.offsetTo(x, y);
    }

    final public void setPosition(int x, int y, int width, int height) {
        position.left = x;
        position.top = y;
        position.right = x + width;
        position.bottom = y + height;
    }

    final public void setPosition(Rect position) {
        this.position.set(position);
    }

    final public void setSides(int width, int height) {
        position.right = position.left + width;
        position.bottom = position.top + height;
    }

    final public void setWidth(int width) {
        position.right = position.left + width;
    }

    final public void setHeight(int height) {
        position.bottom = position.top + height;
    }

    final public void setX(int x) {
        position.offsetTo(x, getY());
    }

    final public void setY(int y) {
        position.offsetTo(getX(), y);
    }


    final public int getX() {
        return position.left;
    }

    final public int getY() {
        return position.top;
    }

    final public int getWidth() { return position.right - position.left; }

    final public int getHeight() { return position.bottom - position.top; }

    final public int getDepth() {
        return depth;
    }

    final public ConsoleActivity getActivity() {
        return activity;
    }


    protected abstract void create();

    protected abstract void destroy();

    protected abstract void update(int millis);

    protected abstract void render(ConsoleCanvas console);

    protected boolean touch(ConsoleMotionEvent event) { return false; };
}
