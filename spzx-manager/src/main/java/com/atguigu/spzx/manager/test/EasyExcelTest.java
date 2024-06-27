package com.atguigu.spzx.manager.test;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class EasyExcelTest {

    public static void main(String[] args) {

        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            Student student = new Student();
            student.setId(i);
            student.setName("name" + i);
            studentList.add(student);
        }
        EasyExcel.write("V:\\Project\\a.xlsx", Student.class).sheet("testEasyExcel").doWrite(studentList);
    }
}
