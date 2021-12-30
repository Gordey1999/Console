package com.gordey.console.lib;

import android.graphics.Bitmap;

public class LettersCollection {

    private Bitmap[] letters;

    public LettersCollection(Bitmap bitmap, int letterWidth, int letterHeight, double scale) {

        bitmap = Bitmap.createScaledBitmap(
                bitmap,
                bitmap.getWidth() * (int)scale,
                bitmap.getHeight() * (int)scale,
                false
        );

        this.createLetters(bitmap, letterWidth * (int)scale, letterHeight * (int)scale,
                (int)(letterWidth * scale), (int)(letterHeight * scale));
    }

    private void createLetters(Bitmap bitmap, int letterW, int letterH, int realW, int realH) {
        int bitmapW = bitmap.getWidth();
        int bitmapH = bitmap.getHeight();


        int lettersInRowCount = bitmapW / letterW;
        int lettersInColCount = bitmapH / letterH;
        int lettersCount = lettersInRowCount * lettersInColCount;
        letters = new Bitmap[lettersCount];

        for (int i = 0; i < lettersInRowCount; i++) {
            for (int j = 0; j < lettersInColCount; j++) {
                 Bitmap b = Bitmap.createBitmap(
                        bitmap,
                        i * letterW,
                        j * letterH,
                        letterW,
                        letterH
                );
                letters[j * lettersInRowCount + i] = Bitmap.createScaledBitmap(b, realW, realH, true);
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
