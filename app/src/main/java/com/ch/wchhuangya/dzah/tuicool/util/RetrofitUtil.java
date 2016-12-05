package com.ch.wchhuangya.dzah.tuicool.util;

import android.text.TextUtils;

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

    public static final String BASE_URL = "http://api.tuicool.com/";
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
     * @param lastId 最后一条记录 id，加载更多时用
     * @param pageNo 要请求的页数，加载更多时用
     * @param firstTime 上次请求时间，下拉刷新时用
     * @param firstId 上次请求返回结果中第一条数据的 id，下拉刷新时用
     * @param success 请求成功调用的接口
     * @param error 请求失败调用的接口
     */
    public static void article (String size, String lang, String cid, String lastId, String pageNo,
                                long firstTime, String firstId, ResponseSuccess<Article> success, ResponseError error) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("size", size);
        queryMap.put("lang", lang);
        queryMap.put("cid", cid);
        queryMap.put("is_pad", "1");
        if (!TextUtils.isEmpty(lastId))
            queryMap.put("last_id", lastId);
        if (!TextUtils.isEmpty(pageNo))
            queryMap.put("pn", pageNo);
        if (firstTime != -1)
            queryMap.put("first_time", firstTime + "");
        if (!TextUtils.isEmpty(firstId))
            queryMap.put("first_id", firstId);

        createService(ArticleService.class).article(queryMap)
                .compose(RxAndroidUtil.applySchedulers())
                .subscribe(success::onSuccess, error::onError);
    }
}
