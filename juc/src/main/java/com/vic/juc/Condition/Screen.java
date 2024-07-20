package com.vic.juc.Condition;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Screen {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    Condition condition = lock.writeLock().newCondition();
    Scanner scanner = new Scanner(System.in);
    String string = null;
    public void write(int flag) {
        try {
            lock.writeLock().lock();
            if (flag == 1) {
                System.out.println("屏幕内容：");
                string = scanner.nextLine();
                System.out.println(Thread.currentThread().getName() + ": " + string);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else {
                string = scanner.nextLine();
                System.out.println(Thread.currentThread().getName() + ": " + string);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void read() {
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + ": " + string);
        } finally {
            lock.readLock().unlock();
        }
    }

}
