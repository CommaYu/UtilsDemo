package com.example.animation;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * 动画的一些基本执行方式：
 * 1.java代码方式，执行动画
 * 2.xml方式，执行xml中的动画
 * 3.往AnimationSet动画组，添加动画，同时执行（如果想顺序执行，为下一个动画添加setStartOffset）
 * 4.Interpolator插入器加速/减速的使用，Java中AnimationSet(true)和xml中设置方式
 * 5.帧动画Frame-by-Frame动画，AnimationDrawable-start/stop
 */
public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    /**
     * 帧动画测试图片
     */
    private ImageView iv_frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);
        iv_frame = (ImageView) findViewById(R.id.iv_frame);

        findViewById(R.id.alphaButtonId).setOnClickListener(new AlphaButtonListener());
        findViewById(R.id.rotateButtonId).setOnClickListener(new RotateButtonListener());
        findViewById(R.id.scaleButtonId).setOnClickListener(new ScaleButtonListener());
        findViewById(R.id.translateButtonId).setOnClickListener(new TranslateButtonListener());
        findViewById(R.id.button).setOnClickListener(new AlphaRotateListener());
        findViewById(R.id.button2).setOnClickListener(new FrameByFrameListener());
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ThirdActivity.class));
                // Activity跳转之间的动画
                overridePendingTransition(R.anim.alpha,R.anim.rotate);
            }
        });
    }

    /**淡入（0-1）淡出（1-0）动画，java执行和xml执行方式*/
    private class AlphaButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            // java执行动画
            // 只有一个效果，就不需要AnimationSet了
            AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
            alphaAnimation.setInterpolator(new AccelerateInterpolator());//不用AnimationSet同样可以设置插入器加速
            alphaAnimation.setDuration(1000);
            iv.startAnimation(alphaAnimation);

            // xml执行动画
//            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
//            iv.startAnimation(animation);
        }
    }

    /** 旋转动画执行 */
    private class RotateButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // java执行动画
            RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                    Animation.ABSOLUTE, 50,
                    Animation.ABSOLUTE, 200);
            rotateAnimation.setDuration(5000);
            iv.startAnimation(rotateAnimation);

            // xml执行动画
//            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
//            iv.startAnimation(animation);
        }
    }

    /** 缩放动画执行 */
    private class ScaleButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            // java执行动画
            ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0.1f, 1, 0.1f,
                    Animation.RELATIVE_TO_SELF, 0.1f,
                    Animation.RELATIVE_TO_SELF, 0.2f);
            scaleAnimation.setStartOffset(3000);//动画执行前等待时间
            scaleAnimation.setFillAfter(false);//设置动画执行完毕，停留在结束位置
            scaleAnimation.setFillBefore(false);//动画执行完毕，回到一开始状态
            scaleAnimation.setDuration(2000);
            iv.startAnimation(scaleAnimation);

            // xml执行动画
//            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
//            iv.startAnimation(animation);

        }
    }

    /** 移动动画执行 */
    private class TranslateButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            // java动画实现
            TranslateAnimation translateAnimation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0f,
                    Animation.RELATIVE_TO_SELF, 1.0f);
            translateAnimation.setDuration(1000);
            iv.startAnimation(translateAnimation);

            // xml执行动画
//            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
//            iv.startAnimation(animation);
        }
    }

    /** 组合动画alpha+rotate同时执行，只要设置了setStartOffset就可以顺序执行了 */
    private class AlphaRotateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {


            // java实现动画
            // 当我们需要同时加载alpha动画和rotate动画的时候，此刻我们就需要AnimationSet类
            AnimationSet animationSet = new AnimationSet(true);//设置成true，alpha和rotate共用一个插入器（加减速）
            animationSet.setInterpolator(new AccelerateInterpolator());//设置一个插入器（加减速用）

            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
            RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

            animationSet.addAnimation(alphaAnimation);
            animationSet.addAnimation(rotateAnimation);

            animationSet.setDuration(2000);
            animationSet.setStartOffset(500);
            iv.startAnimation(animationSet);



            // xml实现动画
//            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha_rotate);
//            iv.startAnimation(animation);
        }
    }

    /** 帧动画的执行方式 */
    private class FrameByFrameListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            iv_frame.setBackgroundResource(R.drawable.animation_list);
            AnimationDrawable animationDrawable = (AnimationDrawable) iv_frame.getBackground();
            animationDrawable.start();
        }
    }
}
