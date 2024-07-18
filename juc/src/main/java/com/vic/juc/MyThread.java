package com.vic.juc;

import java.util.concurrent.TimeUnit;

public class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "------" + i);
//            if(i==7){
//                try {
//                    TimeUnit.SECONDS.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }
}
