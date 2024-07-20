package com.vic.juc.Condition;

public class Test01 {
    public static void main(String[] args) {

        restaurant restaurant = new restaurant();

        // 3个师傅
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                restaurant.cut();
            }
        }, "切菜大师").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                restaurant.cook();
            }
        }, "炒菜大师").start();

        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                restaurant.server();
                System.out.println("第" + (i+1) + "桌菜上完了");
            }
        }, "上菜大师").start();
    }
}
