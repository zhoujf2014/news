package com.zhoujf.news.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zhoujf.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZhouJF on 2017-05-15.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        init();


    }
    public void init() {

    }
    protected abstract int getLayoutId();

    public  void startActivity(Class c){
        Intent intent = new Intent(this,c);
        startActivity(intent);
    }

}
