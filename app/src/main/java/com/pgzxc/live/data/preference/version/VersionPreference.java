package com.pgzxc.live.data.preference.version;

import android.content.Context;

import org.kymjs.kjframe.utils.PreferenceHelper;

/**
 * 版本号
 */

public class VersionPreference {
    private static final String FILE_NAME = VersionPreference.class.getSimpleName();
    private static final String VERSION_CODE = "versionCode";

    public static int getVersionCode(Context context) {
        try {
            return PreferenceHelper.readInt(context, FILE_NAME, VERSION_CODE, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void setVersionCode(Context context, int value) {
        PreferenceHelper.write(context, FILE_NAME, VERSION_CODE, value);
    }
}
