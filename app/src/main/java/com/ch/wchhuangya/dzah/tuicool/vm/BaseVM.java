package com.ch.wchhuangya.dzah.tuicool.vm;

import android.databinding.ObservableBoolean;

import com.ch.wchhuangya.lib.vm.ViewModel;

/**
 * Created by wchya on 2016-12-05 20:14
 */

public class BaseVM extends ViewModel {
    public ObservableBoolean pbShow = new ObservableBoolean(true);
    public ObservableBoolean listShow = new ObservableBoolean(true);
}
