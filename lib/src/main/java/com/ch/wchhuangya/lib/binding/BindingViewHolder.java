package com.ch.wchhuangya.lib.binding;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by wchya on 2016-11-23 15:10
 */

public class BindingViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;

    public BindingViewHolder(ViewDataBinding binding, Object object) {
        super(binding.getRoot());

        this.binding = binding;
        binding.getRoot().setTag(object);
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
