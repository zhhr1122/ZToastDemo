package demo.zhr.toastdemo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zhhr.custom.ZToast;


public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);

        //ZToast.init(Color.parseColor("#000000"),Color.parseColor("#ff00ff"),true,R.mipmap.item_pasue,90);
        //ZToast.setHeight(200);

        findViewById(R.id.btn_showToast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZToast.makeText(MainActivity.this,"点击事件",ZToast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
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
}
