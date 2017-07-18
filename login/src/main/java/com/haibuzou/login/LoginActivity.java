package com.haibuzou.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.haibuzou.baselib.utils.UIUtils;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UIUtils.toastMsg("test");
    }
}
