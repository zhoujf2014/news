package com.zhoujf.news.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;



/**
 * Created by ZhouJF on 2017-05-19.
 */

public class SP {
    public static void setBooleanData( String key,boolean value, Context context){
        SharedPreferences sp = context.getSharedPreferences("sp",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }
    public static void setStringData( String key,boolean value, Context context){
        SharedPreferences sp = context.getSharedPreferences("sp",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }
    public static void setIntData( String key,int value, Context context){
        SharedPreferences sp = context.getSharedPreferences("sp",Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();
    }

    public static boolean getBooleanData( String key, Context context){
        SharedPreferences sp = context.getSharedPreferences("sp",Context.MODE_PRIVATE);
       return sp.getBoolean(key,false);
    }
    public static int getIntData( String key, Context context){
        SharedPreferences sp = context.getSharedPreferences("sp",Context.MODE_PRIVATE);
        return  sp.getInt(key,0);
    }
    public static String getStringData( String key, Context context){
        SharedPreferences sp = context.getSharedPreferences("sp",Context.MODE_PRIVATE);
        return   sp.getString(key,null);
    }
}
