package com.bwei.day02;

import android.animation.Animator;
import android.animation.ObjectAnimator;
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

import com.bwei.day02.bean.UserBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineAdapter extends RecyclerView.Adapter<MineAdapter.ViewHolder> {

    private Context context;
    private List<UserBean.DataBean> mData;


    public MineAdapter(Context context) {
        this.context = context;
        mData=new ArrayList<>();
    }

    public void setDatas(List<UserBean.DataBean> data) {
        //this.mData = mData;
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addDatas(List<UserBean.DataBean> data) {
        //this.mData = mData;
        //mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.activity_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        ButterKnife.bind(viewHolder,view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MineAdapter.ViewHolder viewHolder, final int i) {
        String images = mData.get(i).getImages();
        String replace = images.split("\\|")[0].replace("https", "http");
        viewHolder.imageView.setImageURI(Uri.parse(replace));
        viewHolder.text_price.setText(mData.get(i).getTitle());
        viewHolder.text_title.setText(mData.get(i).getPrice()+"");
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent(i,"short"));
            }
        });
        viewHolder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                EventBus.getDefault().post(new MessageEvent(mData.get(i).getPid(),"long"));
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_image)
        SimpleDraweeView imageView;
        @BindView(R.id.text_title)
        TextView text_title;
        @BindView(R.id.text_price)
        TextView text_price;
        @BindView(R.id.layout)
        ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }


    }
}
