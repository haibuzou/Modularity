package com.haibuzou.router;

/**
 * Created by hywin on 2017/7/17.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by haibuzou on 2017/4/1.
 * 路由跳转管理类
 */
public class RounterManagerJava {

    private static Map<String,Integer> rounterMap =new HashMap<>();
    private static final int LOGIN = 1;

    private static String HTTP_SCHEME = "http";

    static {
        rounterMap.put("login", LOGIN);
    }


    public static void rounter(String uriStr,Activity activity){
        if (TextUtils.isEmpty(uriStr)) {
            return;
        }
        try {
            Uri uri = Uri.parse(uriStr);
            String scheme = uri.getScheme();
            String host = uri.getHost();
            //判断当前是scheme是http web链接执行web跳转
            if (scheme.contains(HTTP_SCHEME)) {
                Class webActivity = Class.forName("com.rnhighfinance.ui.HywinWebActivity");
                Method goToPage = webActivity.getDeclaredMethod("goToPage", Context.class, String.class);
                goToPage.invoke(webActivity.newInstance(), activity, uriStr);
                return;
            }
            //scheme不是http 判断host 决定跳转到哪个activity
            int rounterType = rounterMap.get(host);
            switch (rounterType) {
                case LOGIN:
                    activity.startActivity(new Intent(activity, Class.forName("com.hywin.loginlib.login.LoginActivity")));
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
