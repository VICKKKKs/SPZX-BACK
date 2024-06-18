package com.atguigu.ebuy.service.impl;

import com.atguigu.ebuy.entity.TbSchool;
import com.atguigu.ebuy.mapper.TbSchoolMapper;
import com.atguigu.ebuy.service.TbSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbSchoolServiceImpl implements TbSchoolService {

    @Autowired
    private TbSchoolMapper tbSchoolMapper ;

    @Override
    public List<TbSchool> findAllTbSchool() {
        return tbSchoolMapper.findAllTbSchool();
    }

}
