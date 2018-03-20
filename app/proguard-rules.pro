# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\work\android_sdk\android-sdk-windows/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontoptimize
-dontpreverify
-verbose
-dontwarn
-ignorewarnings
-keepattributes Signature
-keepattributes *Annotation*

#-keep class com.yuanyu.tinber.bean.** { *; }
#-keep class com.yuanyu.tinber.orm.** { *; }
-keep class com.yuanyu.tinber.** { *; }
#Android Support
-keep class android.support.** { *; }
#腾讯相关SDK（微信、QQ）
-keep class com.tencent.** { *; }
#高德SDK
-keep class com.amap.** { *; }
-keep class com.aps.** { *; }
#新浪微博SDK
-keep class com.sina.** { *; }
#kjframe
-keep class org.kymjs.kjframe.** { *; }
#glide
-keep class com.bumptech.glide.** { *; }

#youmeng
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keep public class com.yuanyu.tinber.R$*{
public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

#极光
-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
-dontwarn com.google.**
-keep class com.google.gson.** {*;}
-keep class com.google.protobuf.** {*;}

#EventBus
-keep class org.greenrobot.eventbus.** {*;}

#RxJava
-dontwarn rx.**
-keep class rx.** {*;}

#RxLifecycle
-dontwarn com.trello.**
-keep class com.trello.** {*;}

#player
-dontwarn com.pili.pldroid.player.**
-keep class com.pili.pldroid.player.** {*;}
-dontwarn tv.danmaku.ijk.media.player.**
-keep class tv.danmaku.ijk.media.player.** {*;}

#SlidingTabLayout
-dontwarn com.flyco.tablayout.**
-keep class com.flyco.tablayout.** {*;}

#jp.wasabeef
-dontwarn jp.wasabeef.**
-keep class jp.wasabeef.** {*;}

#jp.co
-dontwarn jp.co.**
-keep class jp.co.** {*;}

#apache
-dontwarn org.apache.http.**
-keep class org.apache.http.** {*;}
# base-adapter-helper

-keep class org.xmlpull.** {*;}
-keep class com.baidu.** {*;}
-keep public class * extends com.umeng.**
-keep class com.umeng.** { *; }
-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
-keepclassmembers  class **$** extends com.chad.library.adapter.base.BaseViewHolder {
     <init>(...);
}
-keep class com.pili.pldroid.player.** { *; }
-keep class com.qiniu.qplayer.mediaEngine.MediaPlayer{*;}