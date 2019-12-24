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


    /**
     * 是否设置黑色字体
     */
    public static void setBlackFontMode(Activity activity, boolean black) {
        if (Build.VERSION.SDK_INT >= 23) {
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (black) {
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                activity.getWindow().getDecorView().setSystemUiVisibility(
                        activity.getWindow().getDecorView().getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }
}
