package com.micro.tremolo.model.from;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @Author KiLin
 * @Time 2020/4/15 10:34
 */
public abstract class BaseFrom implements Serializable, Cloneable {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
