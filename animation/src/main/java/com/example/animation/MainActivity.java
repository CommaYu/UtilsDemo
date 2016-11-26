package com.example.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    /** 帧动画测试图片 */
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
    }

    private class AlphaButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            // java执行动画
//            //创建一个AnimationSet对象
//            AnimationSet animationSet = new AnimationSet(true);
//            //创建一个AlphaAnimation对象
//            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
//            //设置动画执行的时间（单位：毫秒）
//            alphaAnimation.setDuration(1000);
//            //将AlphaAnimation对象添加到AnimationSet当中
//            animationSet.addAnimation(alphaAnimation);
//            //使用ImageView的startAnimation方法开始执行动画
//            iv.startAnimation(animationSet);

            // xml执行动画
            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
            iv.startAnimation(animation);
        }
    }

    private class RotateButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // java执行动画
//            AnimationSet animationSet = new AnimationSet(true);
//            RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
//                    Animation.ABSOLUTE, 50,
//                    Animation.ABSOLUTE, 200);
//            rotateAnimation.setDuration(5000);
//            animationSet.addAnimation(rotateAnimation);
//            iv.startAnimation(animationSet);

            // xml执行动画
            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
            iv.startAnimation(animation);
        }
    }

    private class ScaleButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            // java执行动画
//            AnimationSet animationSet = new AnimationSet(true);
//            ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0.1f, 1, 0.1f,
//                    Animation.RELATIVE_TO_SELF, 0.1f,
//                    Animation.RELATIVE_TO_SELF, 0.2f);
//            animationSet.addAnimation(scaleAnimation);
//            animationSet.setStartOffset(3000);//动画执行前等待时间
//            animationSet.setFillAfter(false);//设置动画执行完毕，停留在结束位置
//            animationSet.setFillBefore(false);//动画执行完毕，回到一开始状态
//            animationSet.setDuration(2000);
//            iv.startAnimation(animationSet);

            // xml执行动画
            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
            iv.startAnimation(animation);

        }
    }

    private class TranslateButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            // java动画实现
//            AnimationSet animationSet = new AnimationSet(true);
//            TranslateAnimation translateAnimation = new TranslateAnimation(
//                    Animation.RELATIVE_TO_SELF, 0f,
//                    Animation.RELATIVE_TO_SELF, 0.5f,
//                    Animation.RELATIVE_TO_SELF, 0f,
//                    Animation.RELATIVE_TO_SELF, 1.0f);
//            translateAnimation.setDuration(1000);
//            animationSet.addAnimation(translateAnimation);
//            iv.startAnimation(animationSet);

            // xml执行动画
            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
            iv.startAnimation(animation);
        }
    }

    private class AlphaRotateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // xml实现动画
            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha_rotate);
            iv.startAnimation(animation);

            // java实现动画
//            AnimationSet animationSet = new AnimationSet(true);//设置成true，alpha和rotate共用一个插入器（加减速）
//            animationSet.setInterpolator(new AccelerateInterpolator());//设置一个插入器（加减速用）
//            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
//            RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//            animationSet.addAnimation(alphaAnimation);
//            animationSet.addAnimation(rotateAnimation);
//            animationSet.setDuration(2000);
//            animationSet.setStartOffset(500);
//            iv.startAnimation(animationSet);
        }
    }

    private class FrameByFrameListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            iv_frame.setBackgroundResource(R.drawable.animation_list);
            AnimationDrawable animationDrawable = (AnimationDrawable) iv_frame.getBackground();
            animationDrawable.start();
        }
    }
}
