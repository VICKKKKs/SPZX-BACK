package com.vic.juc.ThreadSafe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tree2 {
    private Integer apple = 1000;
    private static Lock lock = new ReentrantLock(true);

    public Integer getApple() {
        try {
            lock.lock();
            if (apple > 0) {
                apple--;
                System.out.println("剩余" + apple + "个苹果");
            } else {
                return 0;
            }
        }catch (Exception e){
            System.out.println("出现异常");
            e.printStackTrace();
        }finally {
            System.out.println("方法return之前解锁");
            lock.unlock();
        }
        return 1;
    }


    public Integer getter(){
        return apple;
    }


}
