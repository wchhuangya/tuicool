package com.ch.wchhuangya.exampe_01;

import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import com.kelin.mvvmlight.base.ViewModel;

/**
 * Created by wchya on 2016-12-04 13:19
 */

public class MainVM implements ViewModel {

    public ObservableField<String> text = new ObservableField<>();


    private MainActivity mActivity;

    public MainVM(MainActivity activity) {
        mActivity = activity;

        inidData();
    }

    private void inidData() {
        text.set("这又是一场游戏，看你能不能玩转？");
    }

    public void textClick(View view) {
        Intent intent = new Intent(mActivity, ListViewActivity.class);
        intent.putExtra("s", "ss");
        mActivity.startActivity(intent);
        mActivity.finish();
    }
}
