package com.vic.juc.Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class Alphabet {

    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    private boolean isLower = true;


    Character[] lowerCaseLetters = new Character[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };
    Character[] upperCaseLetters = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    public void printLowerCaseLetters(int i) {
        try {
            lock.lock();
            while (!isLower) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // 开始打印
            System.out.print(lowerCaseLetters[i] + "  ");

            isLower = !isLower;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void printUpperCaseLetters(int i) {
        try {
            lock.lock();
            while (isLower) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // 开始打印
            System.out.print(upperCaseLetters[i] + "  ");

            isLower = !isLower;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
