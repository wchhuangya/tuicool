package com.ch.wchhuangya.lib;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by wchya on 2016-11-28 09:11
 */

public class LibApplication extends Application {
    public static DisplayImageOptions.Builder mBuilder = new DisplayImageOptions.Builder()
            .cacheInMemory(false)
            .cacheOnDisk(true);
    public static DisplayImageOptions squareOptions = mBuilder
            .showImageOnLoading(R.mipmap.pic_default)
            .showImageOnFail(R.mipmap.pic_fail)
            .showImageForEmptyUri(R.mipmap.no_img)
            .build();
    public static DisplayImageOptions circleOptions = mBuilder.displayer(new CircleBitmapDisplayer()).build();

    @Override
    public void onCreate() {
        super.onCreate();

        initImageLoader();
    }

    private void initImageLoader() {
        File cacheDirectory = StorageUtils.getCacheDirectory(this);
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .diskCache(new UnlimitedDiskCache(cacheDirectory))
                .defaultDisplayImageOptions(squareOptions)
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}
