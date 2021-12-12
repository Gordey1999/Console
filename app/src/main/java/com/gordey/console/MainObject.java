package com.gordey.console;

import com.gordey.console.lib.ConsoleCanvas;
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

    @Override
    protected void render(ConsoleCanvas console) {
        console.drawLetter(i + j, j+5, 'a', console.STYLE_NORMAL);
        j++;
        if (j == 23) {
            i++; j = 0;
        }
    }
}
