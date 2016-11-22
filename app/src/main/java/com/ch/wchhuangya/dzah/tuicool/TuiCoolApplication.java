package com.ch.wchhuangya.dzah.tuicool;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by wchya on 2016-11-16 09:37
 */

public class TuiCoolApplication extends Application {

    public static DisplayImageOptions.Builder mBuilder = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.mine_news_pic_default)
                .showImageOnFail(R.mipmap.mine_news_pic_default)
                .showImageForEmptyUri(R.mipmap.abs_img_no)
                .cacheOnDisk(true);
    public static DisplayImageOptions squareOptions = mBuilder.build();
    public static DisplayImageOptions circleOptions = mBuilder.displayer(new CircleBitmapDisplayer()).build();

    @Override
    public void onCreate() {
        super.onCreate();

        File cacheDirectory = StorageUtils.getCacheDirectory(this);
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .diskCache(new UnlimitedDiskCache(cacheDirectory)).build();
        ImageLoader.getInstance().init(configuration);

    }
}
