package com.vic.juc.DeathLock;

public class a {

    public synchronized void aa(){
        System.out.println("aa");
        bb();
    }

    public synchronized void bb(){
        System.out.println("bb");
    }

    public static void main(String[] args) {
        a A = new a();
        A.aa();
        A.bb();
    }

}

