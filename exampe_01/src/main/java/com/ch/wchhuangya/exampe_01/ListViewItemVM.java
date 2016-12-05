package com.ch.wchhuangya.exampe_01;

import android.databinding.ObservableField;

import com.kelin.mvvmlight.base.ViewModel;

/**
 * Created by wchya on 2016-12-04 14:16
 */

public class ListViewItemVM implements ViewModel {
    public ObservableField<String> text = new ObservableField<>();

    public ListViewItemVM(String text) {
        this.text.set(text);
    }
}
