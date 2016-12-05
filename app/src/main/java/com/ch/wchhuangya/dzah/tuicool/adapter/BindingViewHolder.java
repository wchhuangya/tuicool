package com.ch.wchhuangya.dzah.tuicool.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by wchya on 2016-11-23 15:10
 */

public class BindingViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;

    public BindingViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
