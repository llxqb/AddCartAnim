package com.liliu.app.addcartanim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.liliu.app.addcartanim.adapter.GoodsAdapter;
import com.liliu.app.addcartanim.entity.Goods;
import com.liliu.app.addcartanim.utils.AddCartAnimUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GoodsAdapter.OnCheckListener {
    private RecyclerView mRecyclerView;
    private ImageView mCartIcon;
    private GoodsAdapter mGoodsAdapter;
    private RelativeLayout mContainer;
    private List<Goods> mGoodsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mCartIcon = findViewById(R.id.cart_icon);
        mContainer = findViewById(R.id.category_container);

        mGoodsAdapter = new GoodsAdapter(mGoodsList, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mGoodsAdapter);
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            Goods goods = new Goods();
            goods.name = "苹果" + i;
            goods.num = 10;
            mGoodsList.add(goods);
        }
    }

    @Override
    public void onAddCheckChange(View view) {
        AddCartAnimUtil goodsUtil = new AddCartAnimUtil(this);
        goodsUtil.addCartAnim(view,mContainer,mCartIcon,R.anim.shop_scale,this);
    }
}
