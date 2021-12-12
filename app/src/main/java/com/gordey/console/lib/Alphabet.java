package com.gordey.console.lib;

import java.util.HashMap;
import java.util.Map;

public class Alphabet {

    private final String[] alphabet;

    private final Map<Character, Integer> mapLetterToIndex = new HashMap<>();

    private char[] mapIndexToLetter;

    public Alphabet(String[] alphabet) {
        this.alphabet = alphabet;

        createMapLetterToIndex();
        createMapIndexToLetter();
    }

    private void createMapLetterToIndex() {
        int lineWidth = alphabet[0].length();

        for (int i = 0; i < alphabet.length; i++) {
            char[] str = alphabet[i].toCharArray();

            for (int j = 0; j < str.length; j++) {
                mapLetterToIndex.put(str[j], i * lineWidth + j);
            }
        }

        String lastLine = alphabet[alphabet.length - 1];
    }

    private void createMapIndexToLetter() {
        int lineWidth = alphabet[0].length();
        mapIndexToLetter = new char[alphabet[0].length() * alphabet.length];

        for (int i = 0; i < alphabet.length; i++) {
            char[] str = alphabet[i].toCharArray();

            for (int j = 0; j < str.length; j++) {
                mapIndexToLetter[i * lineWidth + j] = str[j];
            }
        }
    }

    public int getIndex(char ch) {
        return mapLetterToIndex.get(ch);
    }

    public char getLetter(int index) {
        return mapIndexToLetter[index];
    }
}
