package com.ch.wchhuangya.dzah.tuicool.util;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wchya on 2016-11-24 10:34
 */

public class TimeUtil {

    public static final String LONG_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private TimeUtil() {
        throw new UnsupportedOperationException("该类不能被实例化～");
    }

    /**
     * 根据格式获取当前时间的字符串
     * @param format 时间格式，如果为 null 或为空，则使用默认长格式：yyyy-MM-dd HH:mm:ss
     */
    public static String getCurTimeByFormat(String format) {
        SimpleDateFormat sdf = null;
        if (TextUtils.isEmpty(format))
            sdf = new SimpleDateFormat(LONG_FORMAT);
        else
            sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    /**
     * 返回当前的时间戳
     * @param returnType 返回时间戳的类型。1-毫秒；2-秒；
     */
    public static long getTimestamp(int returnType) {
        return returnType == 1 ? System.currentTimeMillis() : System.currentTimeMillis() / 1000;
    }
}
