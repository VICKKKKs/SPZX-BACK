package com.vic.juc.test;

public class ab {

    private static final Object lock = new Object();
    // 控制先打印小写还是大写
    private static boolean isLower = false;

    public static void main(String[] args) throws InterruptedException {
        Character[] lowerCaseLetters = new Character[]{
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };
        Character[] upperCaseLetters = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
        Thread t1 = new Thread(new PrintTask(lowerCaseLetters, true));
        Thread t2 = new Thread(new PrintTask(upperCaseLetters, false));

        t1.start();
        t2.start();
    }


    static class PrintTask implements Runnable {
        private final Character[] character;
        private final boolean printLower;

        public PrintTask(Character[] character, boolean printLower) {
            this.character = character;
            this.printLower = printLower;
        }

        @Override
        public void run() {
            for (int i = 0; i < 26; i++) {
                synchronized (lock) {
                    while (isLower != printLower) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.interrupted();
                        }
                    }
                    System.out.print(character[i] + "\t");
                    isLower = !isLower;
                    lock.notifyAll();
                }
            }
        }
    }
}
