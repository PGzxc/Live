package com.pgzxc.live.utils;


import android.content.Context;
import android.content.Intent;

/**
 * 分享工具类
 */

public class ShareUtils {

    /**
     * 分享文字
     *
     * @param context
     */
    public static void shareText(Context context) {
        Intent textIntent = new Intent(Intent.ACTION_SEND);
        textIntent.setType("text/plain");
        textIntent.putExtra(Intent.EXTRA_TEXT, "这是一段分享的文字");
        context.startActivity(Intent.createChooser(textIntent, "分享"));
    }


}
