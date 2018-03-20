package com.pgzxc.live.utils;

import android.Manifest;
import android.app.Activity;
import com.tbruyelle.rxpermissions2.RxPermissions;


/**
 * 权限
 */

public class RxPermisionUtils {

    public static void reqPermision(Activity mContext) {
        RxPermissions rxPermissions = new RxPermissions(mContext);
        rxPermissions.requestEach(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.VIBRATE,
                Manifest.permission.INTERNET,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.GET_TASKS,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.WAKE_LOCK,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.RECEIVE_BOOT_COMPLETED,
                Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS
        )
                .subscribe(permission -> {
                    if (permission.granted) {
                        //Toast.makeText(mContext, "同意权限", Toast.LENGTH_SHORT).show();
                    } else {
                        //Toast.makeText(mContext, "拒绝权限", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
