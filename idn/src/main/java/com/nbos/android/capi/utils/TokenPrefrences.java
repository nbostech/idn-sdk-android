package com.nbos.android.capi.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by vineelanalla on 04/03/16.
 */
public class TokenPrefrences {
    private static final String PREF_NAME = "tokenPrefrences";
    private static SharedPreferences sharedPreferences;
    public SharedPreferences getSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public static void setClientToken(Context context, String clientToken) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("clientToken", clientToken).apply();
    }

    public static String getClientToken(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("clientToken", "");
    }

    public static void setAccessToken(Context context, String accessToken) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("accessToken", accessToken).apply();
    }

    public static String getAccessToken(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("accessToken", "");
    }

    public static void setRefreshToken(Context context, String refreshToken) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("refreshToken", refreshToken).apply();
    }

    public static String getRefreshToken(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("refreshToken", "");
    }
}
