package com.atguigu.spzx.manager.test;

import com.alibaba.excel.EasyExcel;

public class EasyExcelReadTest {
    public static void main(String[] args) {
        EasyExcel.read("V:\\Project\\a.xlsx", Student.class, new MyListener())
                .sheet("testEasyExcel")
                .doRead();
    }
}
