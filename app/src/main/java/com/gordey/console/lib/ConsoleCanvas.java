package com.gordey.console.lib;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsoleCanvas {

    public final String STYLE_NORMAL = "normal";

    private Map<String, LettersCollection> letters = new HashMap<>();
    private Alphabet alphabet;

    private List<String> stylesMap;

    private char[][] buffer;
    private String[][] bufferStyles;

    private boolean changed = true;

    private Rect rectLetter = new Rect();
    private Rect rectDrawedLetter = new Rect();
    private Paint paint = new Paint();

    public ConsoleCanvas(LettersCollection letters, int width, int height, Alphabet alphabet) {
        stylesMap = new ArrayList<>(10);
        stylesMap.add(STYLE_NORMAL);

        this.addLetters(STYLE_NORMAL, letters);

        this.alphabet = alphabet;

        buffer = new char[height][width];
        bufferStyles = new String[height][width];

        for (int x = 0; x < getWidth(); x++)
            for (int y = 0; y < getHeight(); y++) {
                buffer[y][x] = ' ';
                bufferStyles[y][x] = STYLE_NORMAL;
            }

        rectLetter.set(
                0, 0,
                this.letters.get(STYLE_NORMAL).getLetterWidth(),
                this.letters.get(STYLE_NORMAL).getLetterHeight()
        );

        changed = true;
    }

    public void addLetters(String style, LettersCollection letters) {
        if (!this.letters.isEmpty()) {
            if (getLetterWidth() != letters.getLetterWidth() || getLetterHeight() != letters.getLetterHeight())
                throw new IllegalArgumentException("Wrong size of letters");
        }
        this.letters.put(style, letters);

        if (!stylesMap.contains(style)) {
            stylesMap.add(style);
        }
    }

    private int getStyleIndex(String style) {
        return stylesMap.indexOf(style);
    }

    public char getLetter(int x, int y) {
        return alphabet.getLetter(buffer[y][x]);
    }

    public String getLetterStyle(int x, int y) {
        return bufferStyles[y][x];
    }

    public void drawLetter(int x, int y, char letter, String style) {
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
            return;
        }
        if (buffer[y][x] == letter && bufferStyles[y][x].equals(style)) {
            return;
        }

        buffer[y][x] = letter;
        bufferStyles[y][x] = style;

        changed = true;
    }

    public void clear() {
        for (int x = 0; x < getWidth(); x++)
            for (int y = 0; y < getHeight(); y++)
                drawLetter(x, y, ' ', STYLE_NORMAL);
    }

    void render(Canvas canvas, int screenWidth, int screenHeight) {
        int letterScreenWidth = screenWidth / getWidth();
        int letterScreenHeight = screenHeight / getHeight();

        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                int posX = x * letterScreenWidth;
                int posY = y * letterScreenHeight;
                rectDrawedLetter.set(posX, posY,
                        posX + letterScreenWidth, posY + letterScreenHeight);
                String style = getLetterStyle(x, y);
                Bitmap b = letters.get(style)
                        .getLetter(alphabet.getIndex(buffer[y][x]));
                canvas.drawBitmap(b, rectLetter, rectDrawedLetter, paint);
            }
        }

        changed = false;
    }

    public boolean changed() {
        return changed;
    }

    public int getLetterWidth() {
        return letters.get(STYLE_NORMAL).getLetterWidth();
    }
    public int getLetterHeight() {
        return letters.get(STYLE_NORMAL).getLetterHeight();
    }

    public int getWidth() {
        return buffer[0].length;
    }

    public int getHeight() {
        return buffer.length;
    }

    public int getWidthPx() {
        return buffer[0].length * getLetterWidth();
    }

    public int getHeightPx() {
        return buffer.length * getLetterHeight();
    }
}
