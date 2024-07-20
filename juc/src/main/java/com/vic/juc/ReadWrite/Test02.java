package com.vic.juc.ReadWrite;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test02 {

    private static boolean isLower = true;

    private static class printer implements Runnable {

    private static final ReentrantLock lock = new ReentrantLock();

    private static final Condition condition = lock.newCondition();

        private Character[] characters;
        private boolean printLower;

        public printer(Character[] characters, boolean printLower) {
            this.characters = characters;
            this.printLower = printLower;
        }

        @Override
        public void run() {
            for (int i = 0; i < characters.length; i++) {
                lock.lock();
                try {
                    while (isLower != printLower) {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.print(characters[i] + "\t");
                    isLower = !isLower;
                    condition.signal();
                } finally {
                    lock.unlock();
                }
            }

        }
    }

    public static void main(String[] args) {

        Character[] lowerCaseLetters = new Character[]{
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };
        Character[] upperCaseLetters = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
        Thread t1 = new Thread(new printer(lowerCaseLetters, true), "打印小写");
        Thread t2 = new Thread(new printer(upperCaseLetters, false), "打印大写");

        t1.start();
        t2.start();
        System.out.println("success");
    }
}
