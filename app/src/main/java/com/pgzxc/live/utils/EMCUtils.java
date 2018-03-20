package com.pgzxc.live.utils;

import com.hyphenate.chat.EMClient;

/**
 * 环信工具类
 */

public class EMCUtils {
    public static boolean isLoggedInBefore() {
        if (EMClient.getInstance().isLoggedInBefore()) {
            return true;
        } else {
            return false;
        }
    }
}
