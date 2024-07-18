package com.vic.juc.ThreadSafe;

import javax.xml.crypto.Data;

public class Demo2 {
    public static void main(String[] args) {
        Tree2 tree = new Tree2();
        System.out.println("现有苹果：" + tree.getter());

        long timeMillis = System.currentTimeMillis();

        for (int i = 0; i <= 1000; i++) {
            Thread child = new Thread(()->{
                Integer apple = tree.getApple();
                System.out.println(Thread.currentThread().getName()+"号小朋友摘了一个苹果:"+apple);
            });
            child.start();
        }

        long timeMillis2 = System.currentTimeMillis();

        System.out.println(timeMillis2 - timeMillis); // 192
    }
}
