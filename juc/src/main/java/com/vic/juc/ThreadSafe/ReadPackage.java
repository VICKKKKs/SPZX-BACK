package com.vic.juc.ThreadSafe;

import java.util.concurrent.TimeUnit;

public class ReadPackage implements Runnable {
    private static int rp = 3;

    @Override
    public void run() {
        synchronized (this) {
            if (rp <= 0) {
                System.out.println(Thread.currentThread().getName() + "---->未抢到红包");
            } else {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "---->抢到了红包");
                rp--;
            }
        }
    }


    public static void main(String[] args) {
        ReadPackage rp = new ReadPackage();
        Thread t1 = new Thread(rp,"A");
        Thread t2 = new Thread(rp,"B");
        Thread t3 = new Thread(rp,"C");
        Thread t4 = new Thread(rp,"D");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
