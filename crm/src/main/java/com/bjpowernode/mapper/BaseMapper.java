package com.bjpowernode.mapper;

import com.bjpowernode.beans.Type;

import java.io.Serializable;
import java.util.List;

//基本的数据CRUD模板
//T泛指实体类型，I泛指主键类型
public interface BaseMapper<T, I> {
    List<T> getAll();

    T getById(I id);

    int save(T type);

    int edit(T type);

    int delete(I[] ids);
}
