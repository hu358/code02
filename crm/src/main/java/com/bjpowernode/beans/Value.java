package com.bjpowernode.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Value {
    private String id;
    private String value;
    private String text;
    private Integer orderNo;
    private Type type; //外键
    private String typeCode; //外键，专供 edit.jsp 使用
}
