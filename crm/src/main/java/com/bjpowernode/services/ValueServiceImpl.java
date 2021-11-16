package com.bjpowernode.services;

import com.bjpowernode.beans.Value;
import com.bjpowernode.mapper.ValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValueServiceImpl implements ValueService{
    @Autowired
    ValueMapper valueMapper;

    @Override
    public List<Value> getAll() {
        return valueMapper.getAll();
    }

    @Override
    public Value getById(String id) {
        return valueMapper.getById(id);
    }

    @Override
    public int save(Value value) {
        return valueMapper.save(value);
    }

    @Override
    public int edit(Value value) {
        return valueMapper.edit(value);
    }

    @Override
    public int delete(String[] ids) {
        return valueMapper.delete(ids);
    }
}
