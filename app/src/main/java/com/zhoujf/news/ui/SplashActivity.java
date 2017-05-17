package com.zhoujf.news.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.zhoujf.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZhouJF on 2017-05-15.
 */

public class SplashActivity extends BaseActivity {



    private Context mContext;
    private ImageView mIvSplash;

    @Override
    int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void init() {
        super.init();
        mContext = this;

        mIvSplash = (ImageView) findViewById(R.id.iv_splash);
        startAnim();


    }

    private void startAnim() {


        AnimationSet animSet = new AnimationSet(true);
        RotateAnimation rotateAnimation = new RotateAnimation(0,360,Animation.RELATIVE_TO_PARENT,mIvSplash.getWidth()/2,Animation.RELATIVE_TO_PARENT,mIvSplash.getHeight()/2);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
        animSet.addAnimation(rotateAnimation);
        animSet.addAnimation(alphaAnimation);
        animSet.addAnimation(scaleAnimation);
       // animSet.addAnimation(translateAnimation);
        animSet.setDuration(3000);
        mIvSplash.startAnimation(animSet);
        animSet.setAnimationListener(mAnimationListener);

    }


    private Animation.AnimationListener mAnimationListener = new Animation.AnimationListener() {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            startActivity(NavigationActivity.class);
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
}
