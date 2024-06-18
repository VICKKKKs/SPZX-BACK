package com.atguigu.ebuy.controller;

import com.atguigu.ebuy.entity.TbSchool;
import com.atguigu.ebuy.service.TbSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/school")
public class TbSchoolController {

    @Autowired
    private TbSchoolService tbSchoolService ;

    @GetMapping(value = "/findAllTbSchool")
    public List<TbSchool> findAllTbSchool() {
        return tbSchoolService.findAllTbSchool() ;
    }

}
