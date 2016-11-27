package com.example.animation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/11/26.
 * 主要方法AnimationListener（动画执行过程监听方法）
 */

public class ThirdActivity extends Activity {
    String TAG = getClass().getSimpleName();
    private ImageView iv_second;
    private Button btn_add, btn_remove;
    private ViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);

        iv_second = (ImageView) findViewById(R.id.iv_second);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_remove = (Button) findViewById(R.id.btn_remove);
        viewGroup = (ViewGroup) findViewById(R.id.layoutId);//LinearLayout extends ViewGroup
        findViewById(R.id.btn_add).setOnClickListener(new AddListener());
        findViewById(R.id.btn_remove).setOnClickListener(new RemoveListener());
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /** 添加ImageView到ViewGroup，(此处ViewGroup就是layout中的LinearLayout),增加淡入alpha动画0-1 */
    private class AddListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
            animation.setDuration(1000);
            ImageView imageViewAdd = new ImageView(ThirdActivity.this);
            imageViewAdd.setImageResource(R.mipmap.ic_launcher);
            viewGroup.addView(imageViewAdd, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageViewAdd.startAnimation(animation);
        }
    }

    /**设置ImageView的alpha淡出动画1-0，并添加动画监听：动画执行完毕移除ImageView*/
    private class RemoveListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: RemoveListener");
            Animation animation = AnimationUtils.loadAnimation(ThirdActivity.this, R.anim.alpha);
            animation.setAnimationListener(new RemoveAnimListener());
            iv_second.startAnimation(animation);
        }
    }

    private class RemoveAnimListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            Log.d(TAG, "onAnimationStart: ");
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Log.d(TAG, "onAnimationEnd: ");
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    viewGroup.removeView(iv_second);
                    Log.d(TAG, "run: ");
                }
            });
            //如果不用Handler方法包裹，貌似会报错。

            //当动画执行完毕，就从ViewGroup（也就是LinearLayout里面移除ImageView控件）
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            Log.d(TAG, "onAnimationRepeat: ");
        }
    }
}
