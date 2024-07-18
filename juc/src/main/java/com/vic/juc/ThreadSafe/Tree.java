package com.vic.juc.ThreadSafe;

public class Tree {
    private Integer apple = 1000;

    public synchronized Integer getApple() {
        if (apple > 0) {
            apple--;
            System.out.println("剩余" + apple + "个苹果");
            return 1;
        } else {
            return 0;
        }
    }


    public Integer getter(){
        return apple;
    }


}
