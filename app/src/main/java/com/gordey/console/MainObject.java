package com.gordey.console;

import com.gordey.console.lib.Alphabet;
import com.gordey.console.lib.ConsoleCanvas;
import com.gordey.console.lib.ConsoleMotionEvent;
import com.gordey.console.lib.Object;

public class MainObject extends Object {
    @Override
    protected void create() {

    }

    @Override
    protected void destroy() {

    }

    @Override
    protected void update(int millis) {

    }

    int i = 0, j = 0;
    char letter = 'a';
    int a;
    int line = 0;
    Alphabet alphabet = new Alphabet(Letters.ALPHABET);

    @Override
    protected void render(ConsoleCanvas console) {
        letter = alphabet.getLetter((line + i * activity.getWidth() + j) % 165);
        console.drawLetter(j, i, letter, console.STYLE_NORMAL);

        j++;
        if (j == activity.getWidth()) {
            i++; j = 0;
        }
        if (i == activity.getHeight()) {
            line++;
            i = 0;
            j = 0;
        }
    }

    @Override
    protected boolean touch(ConsoleMotionEvent event) {
        System.out.printf("Touch event: %f, %f%n", event.getX(), event.getY());

        return true;
    }
}
