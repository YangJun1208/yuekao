package com.bwei.day03.model;

import com.bwei.day03.callback.MyCallBack;

import java.util.Map;

public interface IModel {
    void getRequest(String dataUrl, Map<String,String> params, Class clazz, MyCallBack callBack);
}
