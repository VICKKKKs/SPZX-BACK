package com.vic.juc.DeathLock;


import java.util.concurrent.TimeUnit;

/*

死锁，一直阻塞
 */

public class T1 {

    public static void main(String[] args) {

        Mirror mirror = new Mirror();

        Lipstick lipstick = new Lipstick();


        new Thread(() -> {
            synchronized (mirror) {
                System.out.println(Thread.currentThread().getName() + "得到镜子，请求口红");
                synchronized (lipstick) {
                    System.out.println(Thread.currentThread().getName() + "得到口红，开始化妆");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                System.out.println(Thread.currentThread().getName() + "化妆完毕，释放资源");
                }
            }
        }, "小红").start();

        new Thread(() -> {
            synchronized (lipstick) {
                System.out.println(Thread.currentThread().getName() + "得到口红，请求镜子");
                synchronized (mirror) {
                    System.out.println(Thread.currentThread().getName() + "得到镜子，开始化妆");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "化妆完毕，释放资源");
                }
            }
        }, "小花").start();
    }
}
