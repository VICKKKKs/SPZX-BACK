package com.vic.juc;

public class TestRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("This is a runnableThread test...");
    }

    public static void main(String[] args) {
        TestRunnable t1 = new TestRunnable();
        Thread t2 = new Thread(t1);
        t2.setName("Runnable_Thread");
        t2.start();
    }
}
