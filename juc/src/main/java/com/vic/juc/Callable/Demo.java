package com.vic.juc.Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        TestCallable c1 = new TestCallable();
/*        FutureTask<Integer> f1 = new FutureTask<>(c1);
        FutureTask<Integer> f2 = new FutureTask<>(c1);
        FutureTask<Integer> f3 = new FutureTask<>(c1);*/

        FutureTask<Integer> f1 = new FutureTask<>(c1);

        // 只
        Thread t1 = new Thread(f1);
        Thread t2 = new Thread(f1);
        Thread t3 = new Thread(f1);
//        Thread t2 = new Thread(f2);
//        Thread t3 = new Thread(f3);

        t1.start();
        t2.start();
        t3.start();

        Integer fv1 = f1.get();
        Integer fv2 = f1.get();
        Integer fv3 = f1.get();
//        Integer fv2 = f2.get();
//        Integer fv3 = f2.get();
        System.out.println("Sum: " + (fv1 + fv2 + fv3));
    }
}
