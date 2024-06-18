package com.atguigu.ebuy.mapper;

import com.atguigu.ebuy.entity.TbSchool;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbSchoolMapper {

    public abstract List<TbSchool> findAllTbSchool() ;

}
