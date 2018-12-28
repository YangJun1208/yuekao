package com.bwei.day03.model;

import android.widget.RelativeLayout;

import com.bwei.day03.callback.MyCallBack;
import com.bwei.day03.network.RetrofitManager;
import com.google.gson.Gson;

import java.util.Map;

import okhttp3.RequestBody;

public class IMdelImpl implements IModel {
    @Override
    public void getRequest(String dataUrl, Map<String, String> params, final Class clazz, final MyCallBack callBack) {
        /*Map<String, RequestBody> map = RetrofitManager.getInstance().generateRequestBody(params);
        RetrofitManager.getInstance().postFormBoby(dataUrl,map).result(new RetrofitManager.HttpListener() {
            @Override
            public void onSuccess(String data) {
                Object o = new Gson().fromJson(data, clazz);
                if(callBack!=null){
                    callBack.CallBack(o);
                }
            }

            @Override
            public void onFail(String error) {

            }
        });*/

       RetrofitManager.getInstance().post(dataUrl,params).result(new RetrofitManager.HttpListener() {
           @Override
           public void onSuccess(String data) {
               Object o = new Gson().fromJson(data, clazz);
               callBack.CallBack(o);
           }

           @Override
           public void onFail(String error) {
           }
       });

    }
}
