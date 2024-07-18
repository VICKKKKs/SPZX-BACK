package com.vic.juc.TestLambda;

public class Lambda {
    public static void main(String[] args) {


        // 方法引用
//        Foo foo = Integer::sum;

        Foo foo = (a, b) -> a + b;

        System.out.println(foo.add(10, 20));
    }
}
