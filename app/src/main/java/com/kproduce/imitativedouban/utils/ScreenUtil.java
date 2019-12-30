package com.kproduce.imitativedouban.utils;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by KG on 2019/12/2
 * @author kuanggang
 */
public class ScreenUtil {

    private static WindowManager windowManager;

    private static WindowManager getWindowManager(Context context) {
        if (windowManager == null) {
            windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return windowManager;
    }

    public static int getScreenWidth(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            Display defaultDisplay = getWindowManager(context).getDefaultDisplay();
            defaultDisplay.getMetrics(displayMetrics);
            if (displayMetrics.widthPixels > 0) {
                return displayMetrics.widthPixels;
            }

            Point screenSize = new Point();
            defaultDisplay.getSize(screenSize);
            if (screenSize.x > 0) {
                return screenSize.x;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getScreenHeight(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            Display defaultDisplay = getWindowManager(context).getDefaultDisplay();
            defaultDisplay.getMetrics(displayMetrics);
            if (displayMetrics.heightPixels > 0) {
                return displayMetrics.heightPixels;
            }

            Point screenSize = new Point();
            defaultDisplay.getSize(screenSize);
            if (screenSize.y > 0) {
                return screenSize.y;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getStatusBarHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    public static float getScreenDensity(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager(context).getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.density;
        } catch (Throwable e) {
            e.printStackTrace();
            return 1.0f;
        }
    }

    public static int getDensityDpi(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager(context).getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.densityDpi;
        } catch (Throwable e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static float getScreenScaledDensity(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager(context).getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.scaledDensity;
        } catch (Throwable e) {
            e.printStackTrace();
            return 1.0f;
        }
    }
}
