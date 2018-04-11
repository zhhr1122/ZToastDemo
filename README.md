# ZToastDemo
a Toast Utils From Z_COMIC
# 介绍
  一款自定义的TOAST插件，一行代码调用，和原生接口一致
# 用法
## 导入
    直接添加依赖
    compile 'com.zhhr:ztoast:1.0.0'
## 使用
###     显示toast
     ZToast.makeText(MainActivity.this,"点击事件",ZToast.LENGTH_SHORT).show();
###      设置toast的各个参数
    在调用makeText方法之前调用init方法来设置参数
    
    
    ZToast.init(Color.parseColor("#000000"),Color.parseColor("#ff00ff"),true,R.mipmap.item_pasue,90);//参数为 背景色 文字颜色 是否有图标 图标资源 高度
    
    //也可以单独设置参数，如高度
    ZToast.setHeight(200);
    //最后调用toast方法
    ZToast.makeText(MainActivity.this,"点击事件",ZToast.LENGTH_SHORT).show();
