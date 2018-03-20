package com.pgzxc.live.data.preference.guide;

import android.content.Context;

import org.kymjs.kjframe.utils.PreferenceHelper;

/**
 * 引导页Sharepreference
 */

public class GuidePreference {

    private static final String IS_SHOW_GUIDE = "isShowGuide";
    private static final String FILE_NAME = GuidePreference.class.getSimpleName();

    /**
     * 是否显示引导页
     * @param context
     * @return
     */
    public static boolean isShowGuide(Context context) {
        try {
            return PreferenceHelper.readBoolean(context, FILE_NAME, IS_SHOW_GUIDE, true);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置引导页
     * @param context
     * @param isShowGuide
     */
    public static void setShowGuide(Context context, boolean isShowGuide) {
        PreferenceHelper.write(context, FILE_NAME, IS_SHOW_GUIDE, isShowGuide);
    }

}
