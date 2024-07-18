package com.vic.juc.ThreadSafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReadPackage03 implements Runnable {
    private static int rp = 3;
    // 开启 fair = true 才是公平锁，默认是非公平锁
    private static Lock lock = new ReentrantLock(true);

    @Override
    public void run() {
        try {
            lock.lockInterruptibly();

            if (rp <= 0) {
                System.out.println(Thread.currentThread().getName() + "---->未抢到红包");
                checkRP();
            } else {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "---->抢到了红包");
                checkRP();
                rp--;

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("线程中断");
        } finally {
            lock.unlock();
        }

    }

    public void checkRP() {
        lock.lock();
        System.out.println("检查剩余红包...");
        lock.unlock();
    }


    public static void main(String[] args) {
//        ReadPackage rp = new ReadPackage();
        ReadPackage03 rp = new ReadPackage03();
        Thread t1 = new Thread(rp, "A");
        Thread t2 = new Thread(rp, "B");
        Thread t3 = new Thread(rp, "C");
        Thread t4 = new Thread(rp, "D");

        t1.start();
        t2.start();
        t2.interrupt();
        t3.start();
        t4.start();

    }
}
