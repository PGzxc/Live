package com.pgzxc.live.utils;

import android.app.Activity;
import com.hyphenate.EMError;
import com.hyphenate.exceptions.HyphenateException;
import org.kymjs.kjframe.ui.ViewInject;

/**
 * 处理错误码
 */

public class HandleError {
    private Activity mContext;

    public  HandleError(Activity context) {
        this.mContext = context;
    }

    public void handleError(HyphenateException e) {
        mContext.runOnUiThread(() -> {
            /**
             * 关于错误码可以参考官方api详细说明
             * http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1_e_m_error.html
             */
            int errorCode = e.getErrorCode();
            String message = e.getMessage();
            switch (errorCode) {
                // 网络错误
                case EMError.NETWORK_ERROR:
                    ViewInject.toast(mContext,"网络错误 code: " + errorCode + ", message:" + message);
                    break;
                // 用户已存在
                case EMError.USER_ALREADY_EXIST:
                    ViewInject.toast(mContext,"用户已存在 code: " + errorCode + ", message:" + message);
                    break;
                // 参数不合法，一般情况是username 使用了uuid导致，不能使用uuid注册
                case EMError.USER_ILLEGAL_ARGUMENT:
                    ViewInject.toast(mContext,"参数不合法，一般情况是username 使用了uuid导致，不能使用uuid注册 code: " + errorCode + ", message:" + message);
                    break;
                // 服务器未知错误
                case EMError.SERVER_UNKNOWN_ERROR:
                    ViewInject.toast(mContext,"服务器未知错误 code: " + errorCode + ", message:" + message);
                    break;
                case EMError.USER_REG_FAILED:
                    ViewInject.toast(mContext,"账户注册失败 code: " + errorCode + ", message:" + message);
                    break;
                default:
                    ViewInject.toast(mContext,"ml_sign_up_failed code: " + errorCode + ", message:" + message);
                    break;
            }
        });
    }
}
