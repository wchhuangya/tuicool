package com.ch.wchhuangya.dzah.tuicool.util;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by wchya on 2016-11-24 17:22
 */

public class CroutonUtil {

    private CroutonUtil(){
        throw new UnsupportedOperationException("该类不能被实例化～");
    }

    /** 短时间显示，1 秒 */
    public static final Configuration.Builder shortConfiguration = new Configuration.Builder().setDuration(1000);

    /** 长时间显示，2 秒 */
    public static final Configuration.Builder longConfiguration = new Configuration.Builder().setDuration(2000);

    /** 超长时间显示，3 秒 */
    public static final Configuration.Builder soLongConfiguration = new Configuration.Builder().setDuration(3000);

    /** 获取长时间显示的配置，动画使用默认 */
    public static Configuration getLongTimeShowCfg() {
        return longConfiguration.build();
    }

    /** 设置背景颜色并返回 */
    public static Style getStyle(int bgColorResId, Configuration configuration) {
        return new Style.Builder().setConfiguration(configuration).setBackgroundColor(bgColorResId).build();
    }
}
