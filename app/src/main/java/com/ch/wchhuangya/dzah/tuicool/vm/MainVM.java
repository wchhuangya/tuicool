package com.ch.wchhuangya.dzah.tuicool.vm;

import android.content.Context;
import android.databinding.ObservableField;

import com.kelin.mvvmlight.base.ViewModel;

/**
 * Created by wchya on 2016-12-04 21:17
 */

public class MainVM implements ViewModel {
    public ObservableField<String> barTitle = new ObservableField<>("首页");

    private Context mContext;

    public MainVM(Context context) {
        mContext = context;
    }

    /** 设置 toolbar 的标题 */
    public void setBarTitle(String title) {
        barTitle.set(title);
    }
}
