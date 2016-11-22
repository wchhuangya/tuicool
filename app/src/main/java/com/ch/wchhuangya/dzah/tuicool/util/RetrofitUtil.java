package com.ch.wchhuangya.dzah.tuicool.util;

import com.ch.wchhuangya.dzah.tuicool.interfaces.ArticleService;
import com.ch.wchhuangya.dzah.tuicool.interfaces.ResponseError;
import com.ch.wchhuangya.dzah.tuicool.interfaces.ResponseSuccess;
import com.ch.wchhuangya.dzah.tuicool.model.Article;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wchya on 2016-11-17 20:32
 */

public class RetrofitUtil implements res {

    public static final String BASE_URL = "http://www.tuicool.com/";
    private static Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    public static <T> T createService(Class<T> service) {
        return builder.client(new OkHttpClient.Builder().build()).build().create(service);
    }

    /**
     * 获取文章列表
     * @param size 数据条数
     * @param lang 语言
     * @param cid 类别
     */
    public static void article (String size, String lang, String cid, ResponseSuccess<Article> success, ResponseError error) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("size", size);
        queryMap.put("lang", lang);
        queryMap.put("cid", cid);
        queryMap.put("is_pad", "1");

        createService(ArticleService.class).article(queryMap)
                .compose(RxAndroidUtil.applySchedulers())
                .subscribe(success::onSuccess, error::onError);
    }
}
