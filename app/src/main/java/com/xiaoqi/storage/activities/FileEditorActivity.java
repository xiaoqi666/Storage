package com.xiaoqi.storage.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xiaoqi.storage.R;
import com.xiaoqi.storage.utils.SpUtils;

/**
 * 文件加载界面
 */
public class FileEditorActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_content;
    private Button btn_save;
    private Button btn_load;
    private Button btn_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_editor);
        initView();
    }

    private void initView() {
        et_content = (EditText) findViewById(R.id.et_content);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_load = (Button) findViewById(R.id.btn_load);
        btn_clear = (Button) findViewById(R.id.btn_clear);

        btn_save.setOnClickListener(this);
        btn_load.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                save();
                break;
            case R.id.btn_load:
                load();
                break;
            case R.id.btn_clear:
                clear();
                break;
        }
    }

    /**
     * 清空内容
     */
    private void clear() {
        et_content.setText("");
    }

    /**
     * 加载内容
     */
    private void load() {
        String content = SpUtils.getString(this, SpUtils.CONTENT, "");
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "Fail to load file", Toast.LENGTH_SHORT).show();
            return;
        }
        et_content.setText(content);
        Toast.makeText(this, "Load successfully", Toast.LENGTH_SHORT).show();
    }

    /**
     * 保存内容
     */
    private void save() {
        String content = et_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        SpUtils.putString(this, SpUtils.CONTENT, content);
        Toast.makeText(this, "Save successfully", Toast.LENGTH_SHORT).show();
        clear();
    }
}
