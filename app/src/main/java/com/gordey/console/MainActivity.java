package com.gordey.console;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.gordey.console.lib.Alphabet;
import com.gordey.console.lib.ConsoleActivity;

public class MainActivity extends ConsoleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onFullyStart() {
        //Bitmap letters = BitmapFactory.decodeResource(getResources(), R.drawable.letters_normal);
        BitmapDrawable letters = (BitmapDrawable) this.getResources().getDrawable(R.drawable.letters_normal);

        initConsole(23, new Alphabet(Letters.ALPHABET), letters.getBitmap(), 8, 8);

        addObject(new MainObject());
    }
}