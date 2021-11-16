package com.bjpowernode.mapper;


import com.bjpowernode.beans.Type;
import org.apache.ibatis.annotations.Param;

public interface TypeMapper extends BaseMapper<Type, String> {
    @Override
    Type getById(@Param("code")String id); //id是通用名称,code是此张表中的名称
}
