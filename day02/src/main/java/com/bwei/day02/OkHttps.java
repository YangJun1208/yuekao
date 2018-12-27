package com.bwei.day02;

import com.google.gson.Gson;


import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import static org.greenrobot.eventbus.EventBus.getDefault;

public class OkHttps {

    public static OkHttps instance;
    private final OkHttpClient client;

    public static OkHttps getInstance() {
        if(instance==null){
            synchronized (OkHttps.class){
                if(null==instance){
                    instance=new OkHttps();
                }
            }
        }
        return instance;
    }

    public OkHttps(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .connectTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }

    public void postRequeue(String dataUrl, Map<String,String> params, final Class clazz){

        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String,String> entry:params.entrySet()){
            builder.add(entry.getKey(),entry.getValue());
        }

        RequestBody build = builder.build();
        Request builder1 = new Request.Builder()
                .url(dataUrl)
                .post(build)
                .build();
        client.newCall(builder1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Object o = new Gson().fromJson(string, clazz);
                EventBus.getDefault().post(new MessageEvent(o,"zhan"));
                EventBus.getDefault().post(new MessageEvent(o,"xiang"));
            }
        });

    }

}
