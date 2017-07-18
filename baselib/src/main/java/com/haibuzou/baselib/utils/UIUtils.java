package com.haibuzou.baselib.utils;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.haibuzou.baselib.BaseApplication;
import com.haibuzou.baselib.R;

/**
 * Created by hywin on 2017/7/18.
 */

public class UIUtils {

    private static Toast lastToast;
    private static TextView mLastToastText;

    /**
     * offsetY为距离屏幕底部Y轴偏移值
     */
    public static void toastMsg(String msg, int showTime, int yOffset) {
        // 只更改Y轴偏移值，默认X轴居中处理
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (lastToast != null) {
            mLastToastText.setText(msg);
        } else {
            lastToast = new Toast(BaseApplication.baseContext);
            LayoutInflater inflater = LayoutInflater.from(BaseApplication.baseContext);
            View view = inflater.inflate(R.layout.toast, null);
            mLastToastText = (TextView) view.findViewById(R.id.toast_text);
            mLastToastText.setText(msg);
            lastToast.setView(view);
        }
        lastToast.setDuration(showTime);
        lastToast.show();
        // 将toast恢复至默认
        lastToast = null;
    }

    public static void toastMsgByStringResource(int strResId) {
        toastMsgByStringResource(strResId, Toast.LENGTH_SHORT);
    }

    public static void toastMsgByStringResource(int strResId, int showTime) {
        String msg = getString(strResId);
        toastMsg(msg, showTime);
    }

    public static String getString(int id) {
        return BaseApplication.baseContext.getResources().getString(id);
    }

    public static void toastMsg(String msg) {
        toastMsg(msg, Toast.LENGTH_SHORT);
    }

    public static void toastMsg(String msg, int showTime) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        BaseApplication.getAppHandler().sendMessage(BaseApplication.getAppHandler().obtainMessage(BaseApplication.CUSTOM_TOAST_MESSAGE, showTime, 0, msg));
    }

}
