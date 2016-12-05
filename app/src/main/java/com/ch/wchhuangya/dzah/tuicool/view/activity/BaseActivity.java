package com.ch.wchhuangya.dzah.tuicool.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Activity 基类
 * Created by wchya on 2016-11-16 10:01
 */

public class BaseActivity extends AppCompatActivity {
    protected Activity mActivity = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
