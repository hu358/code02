package com.bjpowernode.services;

import com.bjpowernode.beans.Type;

import java.util.List;

public interface TypeService {
    List<Type> getAll();

    Type getById(String id);

    int save(Type type);

    int edit(Type type);

    int delete(String[] ids);
}
