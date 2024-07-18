package com.vic.juc.TestLambda;

public class LambdaThread01 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() ->
                System.out.println("This is thead01")
        );

        Thread t2 = new Thread(()->{
            System.out.println("This is thead02");
        });

        t1.start();
        t2.start();


    }
}
