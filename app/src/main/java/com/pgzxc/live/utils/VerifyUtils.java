package com.pgzxc.live.utils;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;

import com.pgzxc.live.R;

/**
 * 验证登录用户名和密码
 */

public class VerifyUtils {

    public static boolean verify(Context context, TextInputEditText etUserName, TextInputEditText etPassWord) {
        String userName = etUserName.getText().toString().trim();
        String passWord = etPassWord.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            etUserName.setError(context.getString(R.string.user_empty));
            return false;
        }
        if (TextUtils.isEmpty(passWord)) {
            etPassWord.setError(context.getString(R.string.password_empty));
            return false;
        }
        if (!isUserNameValid(userName)) {
            etUserName.setError(context.getString(R.string.user_long));
            return false;
        }
        return true;
    }

    private static boolean isUserNameValid(String userName) {
        return 0 < userName.length() && userName.length() <= 11;
    }

}
