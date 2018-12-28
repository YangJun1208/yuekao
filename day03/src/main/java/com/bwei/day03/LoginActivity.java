package com.bwei.day03;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bwei.day03.bean.XiangBean;
import com.bwei.day03.fragment.FragmentOne;
import com.bwei.day03.fragment.FragmentTwo;
import com.bwei.day03.fragment.FregmentThree;
import com.bwei.day03.persenter.IPersenterImpl;
import com.bwei.day03.view.IView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.http.Url;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.group)
    RadioGroup group;
    private List<Fragment> list;
    @BindView(R.id.pager)
    ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        list = new ArrayList<>();
        list.add(new FragmentOne());
        list.add(new FragmentTwo());
        list.add(new FregmentThree());
        //添加适配器
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });


    }



    @OnClick({R.id.radio1,R.id.radio2,R.id.radio3})
    public void click(View v){
        switch (v.getId()){
            case R.id.radio1:
                pager.setCurrentItem(0);
                break;
            case R.id.radio2:
                pager.setCurrentItem(1);
                break;
            case R.id.radio3:
                pager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

}
