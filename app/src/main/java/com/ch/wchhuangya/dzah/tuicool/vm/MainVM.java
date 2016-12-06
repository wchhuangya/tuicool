package com.ch.wchhuangya.dzah.tuicool.vm;

import android.content.Context;
import android.databinding.ObservableField;

/**
 * Created by wchya on 2016-12-04 21:17
 */

public class MainVM extends BaseVM {
    public ObservableField<String> barTitle = new ObservableField<>("首页");

    private Context mContext;

    public MainVM(Context context) {
        mContext = context;
    }

    /** 设置 toolbar 的标题 */
    public void setBarTitle(String title) {
        barTitle.set(title);
    }

    @Override
    public void unsubscribe() {
        super.unsubscribe();
    }
}
