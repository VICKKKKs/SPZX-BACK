package com.vic.juc;

import java.util.concurrent.TimeUnit;

public class TestDaemon {


    public static void main(String[] args) {

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        t1.setName("Lucy");
        t2.setName("David");
        // 设置守护线程
        t1.setDaemon(true);
        t2.setDaemon(true);

        t1.start();
        t2.start();


//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Thread.currentThread().setName("Jack");
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "------" + i);
        }


    }
}
