package android.bignerdranch.playandroid.bottomnlottie.search;

import android.app.Dialog;
import android.bignerdranch.playandroid.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;


public class MyDialog extends Dialog implements View.OnClickListener {

    private TextView mConfirm, mCancel;
    private String sConfirm, sCancel;

    //声明两个监听器
    private View.OnClickListener cancelListener, confirmListener;


    //把返回值设为类名，返回this，调用方法就可以链式调用
    public MyDialog setsConfirm(String sConfirm, View.OnClickListener listener) {
        this.sConfirm = sConfirm;
        this.confirmListener = listener;
        return this;
    }

    public MyDialog setsCancel(String sCancel, View.OnClickListener listener) {
        this.sCancel = sCancel;
        this.cancelListener = listener;
        return this;
    }


    public MyDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_dialog_layout);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setCancelable(false);

        //设置dialog的window对象attribute属性
        //自定义Dialog宽度
        WindowManager windowManager = getWindow().getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        Point size = new Point();
        defaultDisplay.getSize(size);
        layoutParams.width = (int) ((size.x)*0.8);        //设置为屏幕的0.8倍宽度
        getWindow().setAttributes(layoutParams);

//        获取屏幕宽高的常用方法
//        Display defaultDisplay = getWindowManager().getDefaultDisplay();
//        Point point = new Point();
//        defaultDisplay.getSize(point);
//        int x = point.x;
//        int y = point.y;
//        Log.i(TAG, "x = " + x + ",y = " + y);




        mCancel = findViewById(R.id.cancel);
        mConfirm = findViewById(R.id.confirm);

        //如果设置的字符串不是空的话，就给布局中的text赋值
        if (!TextUtils.isEmpty(sCancel)) {
            mCancel.setText(sCancel);
        }
        if (!TextUtils.isEmpty(sConfirm)) {
            mConfirm.setText(sConfirm);
        }

        mConfirm.setOnClickListener(this);
        mCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.confirm:
                if(confirmListener != null){
                    confirmListener.onClick(view);
                }
                break;
            case R.id.cancel:
                if(cancelListener != null){
                    cancelListener.onClick(view);
                }
                break;
        }
    }

}

