package com.wz.beijingnews.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wz on 17-4-18.
 * QQ:1136918218
 * 微信：wz1136918218
 * 作用：
 */

public class SPUtil {

    public static void putBoolean(Context context,String key,boolean value){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key,value);
        edit.apply();
    }

    public static void putString(Context context,String key,String value){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key,value);
        edit.apply();
    }

    public static void putInt(Context context,String key,int value){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(key,value);
        edit.apply();
    }

    public static void putFloat(Context context,String key,float value){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putFloat(key,value);
        edit.apply();
    }

    public static String getString(Context context,String key,String defVal){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        String string = sp.getString(key, defVal);
        return string;
    }

    public static boolean getBoolean(Context context,String key,boolean defVal){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        boolean result = sp.getBoolean(key, defVal);
        return result;
    }

}
