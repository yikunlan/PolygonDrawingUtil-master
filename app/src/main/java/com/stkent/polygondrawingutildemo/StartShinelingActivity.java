package com.stkent.polygondrawingutildemo;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StartShinelingActivity extends Activity {
    private DemoView demo_view1;
    private DemoView demo_view2;
    private DemoView demo_view3;
    private DemoView demo_view4;
    private DemoView demo_view5;

    private final int SEND_CHANGE_SIZE = 0x011;
    private final int SEND_CHANGE_COLOR = 0x012;

    private final float MAX_SIZE = 1.0f;
    private final float MIN_SIZE = 0.0f;
    private float mSize = 0.5f;
    //是要开始增加大小还是减小大小
    private boolean isAdd = true;

    private float maxCornerRadius;

    //颜色
    private List<int[]> mList ;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SEND_CHANGE_SIZE:
                    updateScale(demo_view1);
                    updateScale(demo_view2);
                    updateScale(demo_view3);
                    updateScale(demo_view4);
                    updateScale(demo_view5);
                    mHandler.sendEmptyMessageDelayed(SEND_CHANGE_SIZE,200);
                    break;
                case SEND_CHANGE_COLOR:
                    getRandomColor(demo_view1);
                    getRandomColor(demo_view2);
                    getRandomColor(demo_view3);
                    getRandomColor(demo_view4);
                    getRandomColor(demo_view5);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_shineling);

        maxCornerRadius = getResources().getDimension(R.dimen.max_corner_radius);

        demo_view1 = (DemoView) findViewById(R.id.demo_view1);
        demo_view2 = (DemoView) findViewById(R.id.demo_view2);
        demo_view3 = (DemoView) findViewById(R.id.demo_view3);
        demo_view4 = (DemoView) findViewById(R.id.demo_view4);
        demo_view5 = (DemoView) findViewById(R.id.demo_view5);
        //五角星
        demo_view1.setNumberOfSides(5);
        demo_view2.setNumberOfSides(5);
        demo_view3.setNumberOfSides(5);
        demo_view4.setNumberOfSides(5);
        demo_view5.setNumberOfSides(5);
        //圆角
        demo_view1.setCornerRadius(maxCornerRadius*0.01f);
        demo_view2.setCornerRadius(maxCornerRadius*0.01f);
        demo_view3.setCornerRadius(maxCornerRadius*0.01f);
        demo_view4.setCornerRadius(maxCornerRadius*0.01f);
        demo_view5.setCornerRadius(maxCornerRadius*0.01f);
        //大小
        demo_view1.setScale(0.5f);
        demo_view2.setScale(0.5f);
        demo_view3.setScale(0.5f);
        demo_view4.setScale(0.5f);
        demo_view5.setScale(0.5f);

        //颜色初始化
        mList = new ArrayList<>();
        int array1[] = {R.color.colorAccentTranslucent, R.color.colorAccentTranslucent};
        int array2[] = {R.color.color_2cd276, R.color.color_2edb6f};
        int array3[] = {R.color.color_3dc4c9, R.color.color_blue_0888ff};
        int array4[] = {R.color.color_green_disable, R.color.color_green_00d3a9};
        int array5[] = {R.color.color_btn_orange, R.color.color_eb6233};
        mList.add(array1);
        mList.add(array2);
        mList.add(array3);
        mList.add(array4);
        mList.add(array5);


        //改变大小
        mHandler.sendEmptyMessage(SEND_CHANGE_SIZE);
        //改变颜色
        mHandler.sendEmptyMessage(SEND_CHANGE_COLOR);

    }
    private void updateScale(DemoView demoView) {
        if(mSize>=MAX_SIZE){
            isAdd = false;
//            mHandler.sendEmptyMessage(SEND_CHANGE_COLOR);
        }else if(mSize<=MIN_SIZE){
            isAdd = true;
            mHandler.sendEmptyMessage(SEND_CHANGE_COLOR);
        }
        if(isAdd){
            mSize = mSize + 0.02f;
        }else{
            mSize = mSize - 0.02f;
        }
        demoView.setScale(mSize);
    }

    private void getRandomColor(DemoView demoView){
        Random rand = new Random();
        int num = rand.nextInt(5);
        int[] array = mList.get(num);

        demoView.setColor(array[0],array[1]);
    }
}
