package com.vic.juc.Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Demo02 implements Callable<Integer> {


    @Override
    public Integer call() {
        int result = 0;
        for (int i = 0; i < 10; i++) {
            result += i;
        }
        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = new Demo02();
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        Integer result = futureTask.get();
        System.out.println(result);
    }
}
