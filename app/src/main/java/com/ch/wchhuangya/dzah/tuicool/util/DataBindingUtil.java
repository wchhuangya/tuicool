package com.ch.wchhuangya.dzah.tuicool.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.ch.wchhuangya.dzah.tuicool.TuiCoolApplication;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by wchya on 2016-11-18 19:47
 */

public class DataBindingUtil {
    @BindingAdapter("bind:img")
    public static void setSquareImg(ImageView imageView, String url) {
        ImageLoader.getInstance().displayImage(url, imageView, TuiCoolApplication.squareOptions);
    }
}
