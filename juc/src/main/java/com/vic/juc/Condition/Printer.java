package com.vic.juc.Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Printer {

    private static final ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    int n = 0;


    public void print0() {
        try {
            lock.lock();

            while (n != 0){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // 开始工作，打印0
            System.out.println(Thread.currentThread().getName() + "开始打印：" + n++);

            // 打印完成，让同事打印1
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print1(){
        try {
            lock.lock();

            while (n != 1){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // 开始工作，打印1
            System.out.println(Thread.currentThread().getName() + "开始打印：" + n--);
            // 打印完成，让同事打印0
            condition.signal();
        } finally {
            lock.unlock();
        }
    }


}
