package com.haibuzou.baselib;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.haibuzou.baselib.utils.UIUtils;

/**
 * Created by hywin on 2017/7/17.
 */

public class BaseApplication extends Application {

    public static BaseApplication baseContext;
    public static Handler mAppHandler;              // 应用全局handler，处理全局事务
    public static final int CUSTOM_TOAST_MESSAGE = 1;           // 自定义Toast信息

    @Override
    public void onCreate() {
        super.onCreate();
        baseContext = this;
    }

    public static Handler getAppHandler() {
        if (mAppHandler == null) {
            mAppHandler = new AppHandler(Looper.getMainLooper());
        }
        return mAppHandler;
    }

    private static class AppHandler extends Handler {
        AppHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case CUSTOM_TOAST_MESSAGE:
                    removeMessages(CUSTOM_TOAST_MESSAGE);
                    if (msg.obj != null) {
                        String toastMsg = String.valueOf(msg.obj);
                        // 将全局toast移动至顶部
                        int marginTop = baseContext.getResources().getDimensionPixelOffset(R.dimen.toast_margin_top);
                        UIUtils.toastMsg(toastMsg, msg.arg1, marginTop);
                    }
                    break;
            }
        }
    }
}
