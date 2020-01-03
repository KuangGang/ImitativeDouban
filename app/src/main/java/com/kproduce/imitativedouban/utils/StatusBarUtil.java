package com.kproduce.imitativedouban.utils;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import com.githang.statusbar.StatusBarCompat;

/**
 * @author kuanggang
 * @date 2018/8/23
 */

public class StatusBarUtil {

    /**
     * 设置状态栏颜色和字体颜色
     */
    public static void setStatusBarBack(Activity activity, int color, boolean isLightMode) {
        StatusBarCompat.setStatusBarColor(activity, color, isLightMode);
    }
}
