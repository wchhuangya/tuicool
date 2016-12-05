package io.github.izzyleung.zhihudailypurify.util;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wchya on 2016-12-04 16:54
 */

public class RetrofitUtil {

    public static final String BASE_URL = "http://news.at.zhihu.com/";
    public static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    public static <T> T generateService(Class<T> service) {
        Retrofit retrofit = builder.build();
        return retrofit.create(service);
    }
}
