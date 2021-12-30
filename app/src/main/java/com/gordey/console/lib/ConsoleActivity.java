package com.gordey.console.lib;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ConsoleActivity extends Activity {

    private ConsoleView view;

    private List<Object> objects;
    private List<Object> created;
    private List<Object> deleted;
    private ObjectRenderComparator objectRenderComparator;

    private int backgroundColor = Color.BLACK;

    ConsoleCanvas console = null;

    Rect consoleBitmapRect, consoleDrawRect;
    Paint paint = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new ConsoleView(this);
        setContentView(view);

        objects = new ArrayList<>(200);
        created = new ArrayList<>(20);
        deleted = new ArrayList<>(20);

        objectRenderComparator = new ObjectRenderComparator();
    }

    public abstract void onFullyStart();

    protected void initConsole(int width, Alphabet alphabet, Bitmap lettersBitmap, int letterWidth, int letterHeight) {
        int screenWidth = getWidthPx();
        int screenHeight = getHeightPx();

        int scale = screenWidth / (width * letterWidth);

        LettersCollection letters = new LettersCollection(lettersBitmap, letterWidth, letterHeight, scale);

        int height = screenHeight / (scale * letterHeight);

        console = new ConsoleCanvas(letters, width, height, alphabet);

        consoleBitmapRect = new Rect(0, 0, console.getWidthPx(), console.getHeightPx());
        consoleDrawRect = new Rect(0, 0, screenWidth, screenHeight);

        fullRedraw();
    }

//    protected void addConsoleLetters(Bitmap lettersBitmap, String styles) {
//        LettersCollection letters = new LettersCollection(lettersBitmap, letterWidth, letterHeight, scale);
//    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        //savedInstanceState.putFloat
    }

    final public int getWidthPx() {
        return view.getWidth();
    }

    final public int getHeightPx() {
        return view.getHeight();
    }

    final public int getWidth() { return console.getWidth(); }

    final public int getHeight() { return console.getHeight(); }

    final public void addObject(Object obj) {
        created.add(obj);
        obj.activity = this;
    }

    final public void setBackgroundColor(int color) {
        this.backgroundColor = color;
    }

    final public void render(Canvas canvas) {
        console.render(canvas, getWidthPx(), getHeightPx());
    }

    final public void fullRedraw() {

    }

    final public void restart() {
        objects.clear();
    }

    final public void removeObject(Object obj) {
        deleted.add(obj);
    }

    public boolean onTouch(MotionEvent event) {
        for (int i = objects.size() - 1; i >= 0; i--) {
            if (objects.get(i).touch(event))
                return true;
        }

        return super.onTouchEvent(event);
    }

    final boolean update(int millis) {
        while (!created.isEmpty()) {
            Object obj = created.get(0);
            obj.create();
            objects.add(obj);
            created.remove(0);
        }

        for (Object obj : objects) {
            obj.update(millis);
        }

        for (Object obj : deleted) {
            for (Iterator<Object> iterator = objects.iterator(); iterator.hasNext();)
                if (iterator.next() == obj)
                    iterator.remove();
        }

        deleted.clear();

        // drawing
        objects.sort(objectRenderComparator);


        for (Object obj : objects) {
            obj.render(console);
        }

        return console.changed();
    }
}
