package com.ch.wchhuangya.exampe_01;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ch.wchhuangya.exampe_01.databinding.ListViewBinding;

/**
 * Created by wchya on 2016-12-04 14:14
 */

public class ListViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListViewBinding binding = DataBindingUtil.setContentView(this, R.layout.list_view);
        binding.setListview(new ListViewVM());
    }
}
