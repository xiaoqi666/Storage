package com.xiaoqi.storage.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences 本地持久化数据
 */
public class SpUtils {

    public static final String PASSWORD = "password";
    public static final String CONTENT = "content";


    private static SharedPreferences sp;

    public static void putString(Context context, String key, String value) {
        if (sp == null)
            sp = context.getSharedPreferences("storage_data", 0);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key, String value) {
        if (sp == null)
            sp = context.getSharedPreferences("storage_data", 0);
        return sp.getString(key, value);
    }


}
