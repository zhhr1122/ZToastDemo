package com.zhhr.custom;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.zhhr.R;


/**
 * Created by zhhr on 2018/3/27.
 */

public class ZToast {

    private Activity mActivity;
    private RelativeLayout mToastLayout;
    private ToastLayout mToast;
    private ViewGroup mView;
    private String text;
    private long times;
    private static ZToast mToastInstance;
    /**
     * 固定参数
     */
    public static final long LENGTH_LONG = 3000;
    public static final long LENGTH_SHORT = 1000;
    /**
     * 静态可设置参数
     */
    public static int TextColor;
    public static int BgColor;
    public static boolean isShowIcon = true;
    public static int height = 60;
    public static int resId;

    /**
     * 设置小图标
     * @param resId
     */
    public static void setResId(int resId) {
        ZToast.resId = resId;
    }

    /**
     * 设置高度
     * @param height
     */
    public static void setHeight(int height) {
        ZToast.height = height;
    }

    /**
     * 图标是否显示
     * @param isShowIcon
     */
    public static void setIsShowIcon(boolean isShowIcon) {
        ZToast.isShowIcon = isShowIcon;
    }

    /**
     * 背景色
     * @param bgColor
     */
    public static void setBgColor(int bgColor) {
        BgColor = bgColor;
    }

    /**
     * 文字颜色
     * @param textColor
     */
    public static void setTextColor(int textColor) {
        TextColor = textColor;
    }

    /**
     * 初始化
     * @param BgColor 背景颜色
     * @param TextColor 文字颜色
     * @param isIcon 图标是否显示
     * @param resId 图标
     * @param height 高度
     */
    public static void init(int BgColor, int TextColor, boolean isIcon,int resId,int height){
        ZToast.BgColor = BgColor;
        ZToast.TextColor = TextColor;
        ZToast.isShowIcon = isIcon;
        ZToast.height = height;
        ZToast.resId = resId;
    }


    /**
     * 构造函数，上下文为activity
     * @param mActivity
     * @param text
     * @param times
     */
    public ZToast(Activity mActivity, String text, long times){
        this.mActivity = mActivity;
        this.text = text;
        this.times = times;
    }

    /**
     * 构造函数，上下文为View
     * @param mView
     * @param text
     * @param times
     */
    public ZToast(ViewGroup mView, String text, long times){
        this.mView = mView;
        this.text = text;
        this.times = times;
    }

    /**
     * 调用方法，上下文为activity
     * @param mActivity
     * @param text
     * @param times
     * @return
     */
    public static ZToast makeText(Activity mActivity, String text, long times){
        mToastInstance = new ZToast(mActivity,text,times);
        return mToastInstance;
    }

    /**
     * 调用方法，上下文为view
     * @param mView
     * @param text
     * @param times
     * @return
     */
    public static ZToast makeText(ViewGroup mView, String text, long times){
        mToastInstance = new ZToast(mView,text,times);
        return mToastInstance;
    }


    /**
     * 展示
     */
    public void show(){
        if(mActivity!=null){
            mToastLayout = (RelativeLayout) mActivity.findViewById(R.id.rl_toast);
            if(mToastLayout==null){//判断是否已经添加进母VIEW里，没有则添加进去
                mToast = new ToastLayout(mActivity);
                initToast(mToast);
                mActivity.addContentView(mToast,new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ToastLayout.dip2px(mActivity,height)));
            }else{//如果有，则直接取出
                mToast = (ToastLayout) mToastLayout.getParent();
            }
            mToast.setContent(text);
            mToast.showToast(times);
            return;
        }else if(mView!=null){
            mToastLayout = (RelativeLayout) mView.findViewById(R.id.rl_toast);
            if(mToastLayout==null){
                mToast = new ToastLayout(mView.getContext());
                initToast(mToast);
                mView.addView(mToast,new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ToastLayout.dip2px(mView.getContext(),height)));
            }else{
                mToast = (ToastLayout) mToastLayout.getParent();
            }
            mToast.setContent(text);
            mToast.showToast(times);
        }
    }

    /**
     * 设置各个参数
     * @param mToast
     */
    private void initToast(ToastLayout mToast) {
        if(TextColor!=0){
            mToast.setTextColor(TextColor);
        }
        if(BgColor!=0){
            mToast.setBgColor(BgColor);
        }
        if(resId!=0){
            mToast.setIcon(resId);
        }

        mToast.setIconVisible(isShowIcon);
        mToast.setHeight(height);
    }

    private boolean isShowToast(){
        if(mToast == null){
            return false;
        }
        return  mToast.isShow();
    }

    /**
     * 是否在展示
     * @return
     */
    public static boolean isShow(){
        if(mToastInstance == null){
            return false;
        }else{
            boolean isShow = mToastInstance.isShowToast();
            mToastInstance = null;
            return isShow;
        }
    }
}
