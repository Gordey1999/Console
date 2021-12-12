package com.gordey.console.lib;

import android.graphics.Bitmap;

public class LettersCollection {

    private Bitmap[] letters;

    public LettersCollection(Bitmap bitmap, int letterWidth, int letterHeight, int scale) {

        bitmap = Bitmap.createScaledBitmap(
                bitmap,
                bitmap.getWidth() * scale,
                bitmap.getHeight() * scale,
                false
        );

        this.createLetters(bitmap, letterWidth * scale, letterHeight * scale);
    }

    private void createLetters(Bitmap bitmap, int letterW, int letterH) {
        int bitmapW = bitmap.getWidth();
        int bitmapH = bitmap.getHeight();


        int lettersInRowCount = bitmapW / letterW;
        int lettersInColCount = bitmapH / letterH;
        int lettersCount = lettersInRowCount * lettersInColCount;
        letters = new Bitmap[lettersCount];

        for (int i = 0; i < lettersInRowCount; i++) {
            for (int j = 0; j < lettersInColCount; j++) {
                letters[j * lettersInRowCount + i] = Bitmap.createBitmap(
                        bitmap,
                        i * letterW,
                        j * letterH,
                        letterW,
                        letterH
                );
            }
        }
    }

    public Bitmap getLetter(int index) {
        return letters[index];
    }

    public int getLetterWidth() {
        return letters[0].getWidth();
    }

    public int getLetterHeight() {
        return letters[0].getHeight();
    }
}
