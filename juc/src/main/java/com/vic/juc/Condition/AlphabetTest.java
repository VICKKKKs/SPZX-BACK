package com.vic.juc.Condition;

public class AlphabetTest {
    public static void main(String[] args) {
        Alphabet alphabet = new Alphabet();

        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                alphabet.printLowerCaseLetters(i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                alphabet.printUpperCaseLetters(i);
            }
        }).start();
    }
}
