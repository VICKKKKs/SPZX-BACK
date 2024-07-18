package com.vic.juc;

public class Test01 {

    public static void main(String[] args) {

        MyThread t = new MyThread();
        t.start();

//        t.run();

        MyThread t2 = new MyThread();
        t2.start();


    }

}
