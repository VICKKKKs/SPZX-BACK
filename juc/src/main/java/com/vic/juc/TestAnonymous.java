package com.vic.juc;

import java.sql.SQLOutput;

public class TestAnonymous {

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("This is anonymous thread");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("This is anonymous thread02");
            }
        });

        t1.start();
        t2.start();

        // lambda 方式
        Thread t3 = new Thread(() -> System.out.println("This is anonymous thread03"));

        t3.start();
    }


}
