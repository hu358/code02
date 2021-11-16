package com.bjpowernode.services;

import com.bjpowernode.beans.Type;
import com.bjpowernode.beans.Value;

import java.util.List;

public interface ValueService {
    List<Value> getAll();

    Value getById(String id);

    int save(Value type);

    int edit(Value type);

    int delete(String[] ids);
}
