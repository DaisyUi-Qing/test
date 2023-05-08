package com.daisy.demo.entity;

import lombok.Data;

/**
 * @author dxm
 */
@Data
public class User {
    /**
    * 主键
    */
    private String id;

    /**
    * 姓名
    */
    private String name;

    /**
    * 年龄
    */
    private Integer age;

    /**
    * 身份证
    */
    private String idCard;
}
