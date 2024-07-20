package com.vic.juc.ReadWrite;

import java.util.Scanner;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("哪个老师上课？1 or 2");
        int flag = sc.nextInt();
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);


        String nr = null;
//        boolean flag = true;
//        for (int i = 0; i < 2; i++) {
        lock.writeLock().lock();
        try {
            if (flag == 1) {
                nr = "上课";
                String s = nr;
                new Thread(() -> {
                    Thread.currentThread().setName("1号老师");
                    System.out.println("老师________：" + Thread.currentThread().getName() + s);
                }).start();
            } else {
                nr = "下课";
                String s = nr;
                new Thread(() -> {
                    Thread.currentThread().setName("2号老师");
                    System.out.println("老师________：" + Thread.currentThread().getName() + s);
                }).start();
            }
        } finally {
            lock.writeLock().unlock();
        }
//        }

        for (
                int i = 0;
                i < 40; i++) {
            lock.readLock().lock();
            try {
                String finalNr = nr;
                new Thread(() -> System.out.println("学生" + Thread.currentThread().getName() + finalNr)).start();

            } finally {
                lock.readLock().unlock();
            }
        }
    }
}
