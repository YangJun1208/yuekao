package com.bwei.day03.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.day03.Meassage;
import com.bwei.day03.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FregmentThree extends Fragment {
    @BindView(R.id.text2)
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmentthree,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEventMainThread(Meassage messageEvent){
        if(messageEvent.getName().equals("111")){
            String title = messageEvent.getPrice();
            Log.i("TAG",title);
            Toast.makeText(getContext(), title+"", Toast.LENGTH_SHORT).show();
            textView.setText(title);
            //   String title = messageEvent.getTitle();
         /*   Toast.makeText(getContext(), price+"", Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(), title+"", Toast.LENGTH_SHORT).show();*/
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
