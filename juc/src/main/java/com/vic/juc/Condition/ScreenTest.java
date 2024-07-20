package com.vic.juc.Condition;

public class ScreenTest {
    public static void main(String[] args) {


        Screen screen = new Screen();

        new Thread(()->{
            screen.write(1);
        },"徐老师").start();

        for (int i = 0; i < 40; i++) {
            new Thread(()->{
                screen.read();
            },(i+1)+"号学生").start();
        }
    }
}
