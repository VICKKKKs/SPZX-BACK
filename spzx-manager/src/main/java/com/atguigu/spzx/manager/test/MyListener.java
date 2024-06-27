package com.atguigu.spzx.manager.test;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyListener extends AnalysisEventListener<Student> {

    // 批处理：bus
    List<Student> bus = new ArrayList<Student>();

    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        bus.add(student);
        if (bus.size() >= 5) {
            System.out.println("讲student读取到数据库中");
            System.out.println(bus);
            bus.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("收尾方法");
        System.out.println(bus);
    }
}
