package com.vic.juc.Callable;

import java.util.concurrent.Callable;

public class TestCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "------fee:" + 80);
        return 80;
    }
}
