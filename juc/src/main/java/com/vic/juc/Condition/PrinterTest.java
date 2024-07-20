package com.vic.juc.Condition;

public class PrinterTest {
    public static void main(String[] args) {
        Printer printer = new Printer();
        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                printer.print0();
                if (i == 25) System.out.println("最后一个0");
            }
        },"打印机0号").start();

        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                printer.print1();
                if(i == 25) System.out.println("最后一个1");
            }
        },"打印机1号").start();
    }
}
