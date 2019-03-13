package com.liliu.app.addcartanim.views;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.AppCompatImageView;

/**
 * Created by li.liu on 2017/6/19.
 */

public class MoveImageView extends AppCompatImageView {

    public MoveImageView(Context context) {
        super(context);
    }

    public void setMPointF(PointF pointF) {
        setX(pointF.x);
        setY(pointF.y);
    }
}
