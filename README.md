# ZToastDemo
a Toast Utils From Z_COMIC
# 介绍
  一款自定义的TOAST插件，一行代码调用，和原生接口一致
## 使用截图
  ![image](https://github.com/zhhr1122/ZToastDemo/blob/master/image/ezgif.com-video-to-gif.gif?raw=true)
# 用法
## 导入
    直接添加依赖
    compile 'com.zhhr:ztoast:1.0.0'
## 使用
###     显示toast

####     在activity中使用
    ZToast.makeText(MainActivity.this,"文字",ZToast.LENGTH_SHORT).show();
####      在fragment中使用
    ZToast.makeText(getActivity(), "文字",1000).show();
####     在自定义View中使用
    ZToast.makeText((ViewGroup) getParent(),"文字"",1000).show();
###      设置toast的各个参数
    在调用makeText方法之前调用init方法来设置参数
    
    
    ZToast.init(Color.parseColor("#000000"),Color.parseColor("#ff00ff"),true,R.mipmap.item_pasue,90);//参数为 背景色 文字颜色 是否有图标 图标资源 高度
    
    //也可以单独设置参数，如高度
    ZToast.setHeight(200);
    //最后调用toast方法
    ZToast.makeText(MainActivity.this,"点击事件",ZToast.LENGTH_SHORT).show();
###     判断是否正在显示toast

    ZToast.isShow()
    当isShow()为true时，则说明正在显示中，可以用来做双击退出
    
####     点击两下back按键退出可以这样写：
    
    代码如下：
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK)){
            if(ZToast.isShow()){
                return super.onKeyDown(keyCode, event);
            }else{
                ZToast.makeText(MainActivity.this,"再按一次返回键退出",1000).show();
                return false;
            }
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }
    
    

### PS：推荐使用无actionbar的主题和设置statusbar颜色
    强烈建议搭配无actionbar的主题来使用
    
    在style.xml中：
    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>
    
    最好再设置一下statusbar的颜色
    
    在activity中：
    
    Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
