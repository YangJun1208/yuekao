package com.bwei.day03.network;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitManager<T> {

    public static final String BASE_URL="http://www.zhaoapi.cn/product/";

    public static RetrofitManager retrofitManager;



    public static synchronized RetrofitManager getInstance(){
        if(retrofitManager==null){
            retrofitManager=new RetrofitManager();
        }
        return retrofitManager;
    }

    private BaseApis mBaseApis;

    public RetrofitManager(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient builder = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

                Retrofit builder1 = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(builder)
                .build();

        mBaseApis = builder1.create(BaseApis.class);
    }

    public Map<String, RequestBody> generateRequestBody(Map<String,String> requestDataMap){

        HashMap<String, RequestBody> requestBodyHashMap = new HashMap<>();

        for (String key:requestDataMap.keySet()){
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),
                    requestDataMap.get(key) == null ? "" : requestDataMap.get(key));
            requestBodyHashMap.put(key,requestBody);
        }
        return requestBodyHashMap;
    }

    public RetrofitManager get(String url){
        mBaseApis.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return retrofitManager;
    }

    public RetrofitManager post(String dataUrl,Map<String,String> map){
        if(map==null){
            map=new HashMap<>();
        }
        mBaseApis.post(dataUrl,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return retrofitManager;
    }

    public RetrofitManager postFormBoby(String dataUrl,Map<String,RequestBody> map){
        if(map==null){
            map=new HashMap<>();
        }
        mBaseApis.postFormBody(dataUrl,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return retrofitManager;
    }


    private Observer observer=new Observer<ResponseBody>(){

        //观察者
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(ResponseBody responseBody) {
            try {
                String data = responseBody.string();
                if(listener!=null){
                    listener.onSuccess(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
                if(listener!=null){
                    listener.onFail(e.getMessage());
                }
            }
        }
    };

    private HttpListener listener;

    public void result(HttpListener listener){
        this.listener=listener;
    }

    public interface HttpListener {
        void onSuccess(String data);
        void onFail(String error);
    }
}
