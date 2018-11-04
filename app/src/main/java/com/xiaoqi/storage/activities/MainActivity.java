package com.xiaoqi.storage.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xiaoqi.storage.R;
import com.xiaoqi.storage.utils.SpUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_password;
    private EditText et_confirm;
    private Button btn_ok;
    private Button btn_clear;
    private String password;
    private boolean ifHasPassword;//是否设置密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        password = SpUtils.getString(this, SpUtils.PASSWORD, "");
        if (!TextUtils.isEmpty(password)) {
            ifHasPassword = true;
        }

        if (ifHasPassword) {//如果已经设置了密码，则将重复输入密码的EditText隐藏
            et_confirm.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化布局
     */
    private void initView() {
        et_password = (EditText) findViewById(R.id.et_password);
        et_confirm = (EditText) findViewById(R.id.et_confirm);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_clear = (Button) findViewById(R.id.btn_clear);

        btn_ok.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok://ok按钮
                commitOk();//进行密码验证，提交
                break;
            case R.id.btn_clear://clear按钮
                clearEditText();                //将两个编辑框清空
                break;
        }
    }

    /**
     * 将两个文本框置空
     */
    private void clearEditText() {
        et_confirm.setText("");
        if (!ifHasPassword)
            et_password.setText("");
    }


    /**
     * 点击OK按钮进行提交的操作
     */
    private void commitOk() {
        if (!ifHasPassword) {//如果没有设置密码，则进行密码设置
            settingPassword();//进行密码设置
        } else {//进入验证密码，正确则进行页面跳转
            String pwd = et_password.getText().toString();
            if (pwd.equals(password)) {
                go2FileEditActivity();//进行页面跳转
            } else {
                //密码不正确弹出吐司
                Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * 进行密码设置
     */
    private void settingPassword() {
        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, getString(R.string.pwd_connot_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        String confirm = et_confirm.getText().toString().trim();
        if (TextUtils.isEmpty(confirm)) {
            Toast.makeText(this, getString(R.string.pwd_connot_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirm)) {
            Toast.makeText(this, getString(R.string.Password_Mismatch), Toast.LENGTH_SHORT).show();
            return;
        }

        SpUtils.putString(this, SpUtils.PASSWORD, password);
        go2FileEditActivity();
    }

    /**
     * 跳转到FileEditActivity的界面
     */
    private void go2FileEditActivity() {
        startActivity(new Intent(this, FileEditorActivity.class));
        finish();
    }

}
