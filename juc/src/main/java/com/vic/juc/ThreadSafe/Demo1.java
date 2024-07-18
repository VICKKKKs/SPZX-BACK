package com.vic.juc.ThreadSafe;

public class Demo1 {
    public static void main(String[] args) {
        Tree tree = new Tree();
        System.out.println("现有苹果：" + tree.getter());
        long timeMillis = System.currentTimeMillis();

        for (int i = 0; i <= 1000; i++) {
            Thread child = new Thread(()->{
                Integer apple = tree.getApple();
                System.out.println(Thread.currentThread().getName()+"号小朋友摘苹果:"+apple);
            });
            child.start();
        }

        long timeMillis1 = System.currentTimeMillis();
        System.out.println(timeMillis1 - timeMillis); // 186

    }
}
