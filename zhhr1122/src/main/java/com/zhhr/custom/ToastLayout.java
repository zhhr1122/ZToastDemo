package com.zhhr.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhhr.R;


/**
 * Created by 皓然 on 2017/8/3.
 */

public class ToastLayout extends RelativeLayout {
    private static final int  ANIMATION_TIME = 200;
    private TextView mContent;
    private View view;
    private boolean isShow;
    private RelativeLayout mWrapper;
    private ImageView mIcon;
    private int height;

    public boolean isShow() {
        return isShow;
    }

    public ToastLayout(Context context) {
        this(context, null);
    }

    public ToastLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToastLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = LayoutInflater.from(getContext()).inflate(R.layout.layout_toast, null);
        addView(view);
        mContent =  view.findViewById(R.id.tv_content);
        mWrapper = view.findViewById(R.id.rl_toast);
        mIcon = view.findViewById(R.id.iv_icon);
        height = 60;
    }

    public void setTextColor(int color){
        mContent.setTextColor(color);
    }

    public void setBgColor(int color){
        mWrapper.setBackgroundColor(color);
    }

    public void setIconVisible(boolean isShow){
       if(isShow){
           mIcon.setVisibility(View.VISIBLE);
       }else {
           mIcon.setVisibility(View.GONE);
       }
    }

    public void setIcon(int resId){
        mIcon.setImageResource(resId);
    }

    public void setHeight(int height) {
        this.height = height;
    }



    public void setContent(String content){
        if(mContent!=null){
            mContent.setText(content);
        }
    }

    public void showToast(long time){
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation trans1 = new TranslateAnimation(0, 0 ,-dip2px(getContext(),height) ,0);
        TranslateAnimation trans2 = new TranslateAnimation(0,0 , 0 , -dip2px(getContext(),height));
        trans1.setDuration(ANIMATION_TIME);
        trans2.setStartOffset(ANIMATION_TIME+time);
        trans2.setDuration(ANIMATION_TIME);
        animationSet.addAnimation(trans1);
        animationSet.addAnimation(trans2);
        this.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isShow = true;
                ToastLayout.this.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isShow = false;
                ToastLayout.this.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * density + 0.5f);
    }

}
