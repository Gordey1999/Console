package com.gordey.console.lib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ConsoleView extends SurfaceView implements SurfaceHolder.Callback {

    ConsoleActivity activity;
    private ConsoleThread thread;

    public ConsoleView(Context context) {
        super(context);
        getHolder().addCallback(this);

        setFocusable(true);

        activity = (ConsoleActivity)getContext();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        activity.fullRedraw();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new ConsoleThread(getHolder(), this);
        thread.setRunning(true);

        activity.onFullyStart();

        thread.start();

        activity.fullRedraw();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // try again shutting down the thread
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
    }

    // the fps to be displayed
    private String avgFps;
    public void setAvgFps(String avgFps) {
        this.avgFps = avgFps;
    }

    public void displayFps(Canvas canvas) {
        if (canvas != null && avgFps != null) {
            Paint paint = new Paint();
            paint.setTextSize(50);
            paint.setARGB(255, 255, 255, 255);
            canvas.drawText(avgFps, 50, 50, paint);
        }
    }

    public boolean update(int millis) {
        return activity.update(millis);
    }

    public void render(Canvas canvas) {
        activity.render(canvas);

        displayFps(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return activity.onTouch(event);
    }
}
