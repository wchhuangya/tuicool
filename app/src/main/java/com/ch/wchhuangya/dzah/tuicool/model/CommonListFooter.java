package com.ch.wchhuangya.dzah.tuicool.model;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

/**
 * Created by wchya on 2016-11-23 16:13
 */

public class CommonListFooter {
    public ObservableField<String> tips = new ObservableField<>("上拉加载更多");
    public ObservableBoolean metaballShow = new ObservableBoolean(false);
    public ObservableBoolean tipsShow = new ObservableBoolean(true);
}
