package com.bwei.day03.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bwei.day03.R;
import com.bwei.day03.bean.ChaBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<ChaBean.DataBean> mData;
    private boolean flag=true;


    public MyAdapter(Context context, boolean flag) {
        this.context = context;
        this.flag = flag;
        mData=new ArrayList<>();
    }

    public void setmData(List<ChaBean.DataBean> data) {
        //this.mData = mData;
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }
    public void addmData(List<ChaBean.DataBean> data) {
        //this.mData = mData;
        //data.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder viewHolder=null;
        if(flag){
            View view = LayoutInflater.from(context).inflate(R.layout.item_lv, viewGroup, false);

            viewHolder=new ViewHolder(view);
            ButterKnife.bind(viewHolder,view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.item_gr, viewGroup, false);

            viewHolder=new ViewHolder(view);
            ButterKnife.bind(viewHolder,view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        final ChaBean.DataBean dataBean = mData.get(i);
        String images = mData.get(i).getImages();
        String s = images.split("\\|")[0];
        viewHolder.imageView.setImageURI(Uri.parse(s));
      //  Glide.with(context).load(split[0]).into(viewHolder.imageView);

        viewHolder.lv_text.setText(mData.get(i).getTitle());
        viewHolder.lv_price.setText(mData.get(i).getPrice()+"");
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mClick!=null){
                    mClick.onSuccess(dataBean.getPid());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lv_image)
        SimpleDraweeView imageView;
         @BindView(R.id.lv_text)
         TextView lv_text;
         @BindView(R.id.lv_price)
         TextView lv_price;
         @BindView(R.id.LV_liear)
         ConstraintLayout layout;

        public ViewHolder(View view) {
            super(view);
            layout=view.findViewById(R.id.LV_liear);

        }







    }



    Click mClick;

    public void setonClickListener(Click click){
        this.mClick=click;
    }
    public interface Click {
        void onSuccess(int position);
    }
}
