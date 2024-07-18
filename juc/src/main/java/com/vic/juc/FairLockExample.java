package com.vic.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairLockExample implements Runnable {

    private static final int NUM_THREADS = 100;
    private static Lock lock = new ReentrantLock(true);

    @Override
    public void run() {
//        for (int i = 0; i < NUM_THREADS; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 获取了锁");
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " 释放了锁");
                lock.unlock();
            }
//        }
    }

    public static void main(String[] args) {

        FairLockExample fairLockExample = new FairLockExample();
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(fairLockExample, "example--" + i);
            threads[i].start();
//            try {
//                threads[i].join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

        System.out.println("所有线程已完成");
    }
}
