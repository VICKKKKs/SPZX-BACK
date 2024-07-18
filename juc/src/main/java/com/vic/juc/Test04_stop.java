package com.vic.juc;

import java.util.concurrent.TimeUnit;

public class Test04_stop {
    public static void main(String[] args) {

        MyThread t = new MyThread();

        t.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.stop();   /*当线程阻塞后，立即停止线程*/
    }
}
