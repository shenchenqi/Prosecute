package com.micro.worker.model.params;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @Author KiLin
 * @Time 2020/4/15 10:23
 */
public abstract class BaseParam implements Serializable {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}