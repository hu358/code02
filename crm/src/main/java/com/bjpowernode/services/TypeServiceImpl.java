package com.bjpowernode.services;

import com.bjpowernode.beans.Type;
import com.bjpowernode.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeMapper typeMapper;

    @Override
    public List<Type> getAll() {
        return typeMapper.getAll();
    }

    @Override
    public Type getById(String id) {
        return typeMapper.getById(id);
    }

    @Override
    public int save(Type type) {
        return typeMapper.save(type);
    }

    @Override
    public int edit(Type type) {
        return typeMapper.edit(type);
    }

    @Override
    public int delete(String[] ids) {
        return typeMapper.delete(ids);
    }
}
