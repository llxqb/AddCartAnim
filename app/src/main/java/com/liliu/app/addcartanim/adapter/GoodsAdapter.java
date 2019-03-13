package com.liliu.app.addcartanim.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.liliu.app.addcartanim.R;
import com.liliu.app.addcartanim.entity.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by li.liu on 2018/12/27.
 * GoodsAdapter
 */

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.MyHolder> {

    private OnCheckListener mOnCheckListener;
    List<Goods> mGoodsList = new ArrayList<>();

    public GoodsAdapter(List<Goods> goods, OnCheckListener onCheckListener) {
        mGoodsList = goods;
        mOnCheckListener = onCheckListener;
    }

    public interface OnCheckListener {
        void onAddCheckChange(View view);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Goods goods = mGoodsList.get(position);
        MyHolder myHolder = holder;
        myHolder.mGoodsName.setText(goods.name);
        myHolder.mNum.setText(goods.num + "");

        myHolder.mAddIcon.setOnClickListener(v -> {
            if (mOnCheckListener != null) {
                mOnCheckListener.onAddCheckChange(v);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mGoodsList.size();
    }


    class MyHolder extends ViewHolder {
        TextView mGoodsName;
        ImageView mAddIcon;
        ImageView mReduceIcon;
        TextView mNum;
        TextView mProvinceNum;

        MyHolder(View itemView) {
            super(itemView);
            mGoodsName = itemView.findViewById(R.id.goods_name);
            mAddIcon = itemView.findViewById(R.id.add_icon);
            mReduceIcon = itemView.findViewById(R.id.reduce_icon);
            mNum = itemView.findViewById(R.id.num);
        }
    }
}
