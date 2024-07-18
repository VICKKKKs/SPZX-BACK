package com.vic.juc;

import java.util.concurrent.TimeUnit;

public class Test03_interrupt {

    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.setName("interruptThread");
        t.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.interrupt();
    }
}
