package com.bwei.day02;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.day02.bean.XiangQing;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.login_banner)
    Banner banner;
    @BindView(R.id.login_title)
    TextView title;
    @BindView(R.id.login_price)
    TextView price;
    @BindView(R.id.login_gouwu)
    Button button;
    @BindView(R.id.login_simple)
    SimpleDraweeView simpleDraweeView;
    private List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        Intent intent;
        intent = getIntent();
        String pid = intent.getStringExtra("pid");
        Map<String,String> map=new HashMap<>();
        map.put("pid",pid);
        OkHttps.getInstance().postRequeue(Apis.TYPE_XIANG,map,XiangQing.class);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(new ImageLoaderInterface<SimpleDraweeView>() {
            @Override
            public void displayImage(Context context, Object path, SimpleDraweeView imageView) {
                Uri parse = Uri.parse((String) path);
                imageView.setImageURI(parse);
            }

            @Override
            public SimpleDraweeView createImageView(Context context) {
                SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
                return simpleDraweeView;
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(MessageEvent messageEvent){
        if (messageEvent.getId().equals("xiang")){
            XiangQing bean= (XiangQing) messageEvent.getObject();
            list = new ArrayList<>();
            imageurl(bean.getData().getImages());
            title.setText(bean.getData().getTitle());
            price.setText(bean.getData().getPrice()+"");
            banner.setImages(list);
            banner.start();
        }
    }
    private void imageurl(String images) {
        int i = images.indexOf("|");
        if (i>0){
            String substring = images.substring(0, i);
            list.add(substring);
            imageurl(images.substring(i+1,images.length()));
        }else{
            list.add(images);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.login_gouwu)
    public void jiaru(){
        UMShareAPI um=UMShareAPI.get(LoginActivity.this);
        um.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                String iconurl = map.get("iconurl");
                Uri parse=Uri.parse(iconurl);
                simpleDraweeView.setImageURI(parse);
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(LoginActivity.this).onActivityResult(requestCode,resultCode,data);
    }
}
