package com.liliu.app.addcartanim.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.liliu.app.addcartanim.R;
import com.liliu.app.addcartanim.views.MoveImageView;


/**
 * Created by li.liu on 2019/3/13.
 * 添加购物车动画
 */

public class AddCartAnimUtil implements Animator.AnimatorListener{

    private GoodsUtilListener mGoodsUtilListener;
    private RelativeLayout mContainer;
    private ImageView mCartIcon;
    private Context mContext;


    public interface GoodsUtilListener{
        void addGoodsToCartListener(Animator animation);
    }

    public AddCartAnimUtil(Context context) {
        mContext = context;
    }

    /**
     * 增加购物车动画
     * @param addV view
     * @param mContainer  recyclerView 容器
     * @param mBottomCartImage 购物车控件
     * @param scaleAnim 购物车图标放大的动画
     * @param context 上下文对象
     */
    public void addCartAnim(View addV, RelativeLayout mContainer, ImageView mBottomCartImage, int scaleAnim,Context context) {
        this.mContainer = mContainer;
        this.mCartIcon = mBottomCartImage;
        int[] childCoordinate = new int[2];
        int[] parentCoordinate = new int[2];
        int[] shopCoordinate = new int[2];
        //1.分别获取被点击View、父布局、购物车在屏幕上的坐标xy。
        addV.getLocationInWindow(childCoordinate);
        mContainer.getLocationInWindow(parentCoordinate);
        //cartImg.getLocationInWindow(shopCoordinate);
        mBottomCartImage.getLocationInWindow(shopCoordinate);
        //2.自定义ImageView 继承ImageView
        MoveImageView img = new MoveImageView(context);
        img.setImageResource(R.drawable.ic_shop_cart_goods_reserved);
        //3.设置img在父布局中的坐标位置
        img.setX(childCoordinate[0] - parentCoordinate[0]);
        img.setY(childCoordinate[1] - parentCoordinate[1]);
        //4.父布局添加该Img
        mContainer.addView(img);

        //5.利用 二次贝塞尔曲线 需首先计算出 MoveImageView的2个数据点和一个控制点
        PointF startP = new PointF();
        PointF endP = new PointF();
        PointF controlP = new PointF();
        //开始的数据点坐标就是 addV的坐标
        startP.x = childCoordinate[0] - parentCoordinate[0];
        startP.y = childCoordinate[1] - parentCoordinate[1];
        //结束的数据点坐标就是 shopImg的坐标
        endP.x = shopCoordinate[0] - parentCoordinate[0];
        endP.y = shopCoordinate[1] - parentCoordinate[1];
        //控制点坐标 x等于 购物车x；y等于 addV的y
        controlP.x = endP.x;
        controlP.y = startP.y;

        //启动属性动画
        ObjectAnimator animator = ObjectAnimator.ofObject(img, "mPointF",
                new PointFTypeEvaluator(controlP), startP, endP);
        animator.setDuration(500);
        animator.addListener(this);
        animator.start();
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
//        if(mGoodsUtilListener!=null){
//            mGoodsUtilListener.addGoodsToCartListener(animation);
//        }
        //动画结束后 父布局移除 img
        Object target = ((ObjectAnimator) animation).getTarget();
        mContainer.removeView((View) target);
        //shopImg 开始一个放大动画
        Animation scaleAnim = AnimationUtils.loadAnimation(mContext, R.anim.shop_scale);
        mCartIcon.startAnimation(scaleAnim);
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }


}
