package com.vic.juc.Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class restaurant {

    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition cutting = lock.newCondition();
    private static final Condition cooking = lock.newCondition();
    private static final Condition serving = lock.newCondition();

    private String status = "cutting";


    public void cut(){
        try {
           lock.lock();

           // rest
            if(!status.equals("cutting")){
                try {
                    cutting.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // status == cutting 需要切菜
            System.out.println(Thread.currentThread().getName() + "开始切菜");
            // 切菜时间
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 切菜完成，开始cook
            status = "cooking";
            cooking.signal();
        }finally {
            lock.unlock();
        }
    }

    public void cook(){
        try {
            lock.lock();

            // rest
            if(!status.equals("cooking")){
                try {
                    cooking.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // status == cooking 需要炒菜
            System.out.println(Thread.currentThread().getName() + "开始炒菜");
            // 切菜时间
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 切菜完成，开始上菜
            status = "serving";
            serving.signal();
        }finally {
            lock.unlock();
        }
    }

    public void server(){
        try {
            lock.lock();

            // rest
            if(!status.equals("serving")){
                try {
                    serving.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // status == serving 需要上菜
            System.out.println(Thread.currentThread().getName() + "开始上菜");
            // 上菜时间
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 上菜完成，开始让切菜师傅切下一桌的菜
            status = "cutting";
            cutting.signal();
        }finally {
            lock.unlock();
        }
    }

}
