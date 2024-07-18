package com.vic.juc.TestLambda;

public class Demo01 {
    public static void main(String[] args) {
        Foo foo = new Foo(){
            @Override
            public int add(int a, int b) {
                return a+b;
            }
        };
        System.out.println(foo.add(1,2));
    }
}
