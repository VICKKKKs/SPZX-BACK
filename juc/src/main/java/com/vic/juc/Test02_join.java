package com.vic.juc;

public class Test02_join {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

        MyThread t = new MyThread();
        t.setName("joinThread");
        for (int i = 0; i < 10; i++) {
            if(i == 7){
                t.start();
                t.join();
            }
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + i);
        }
    }
}
