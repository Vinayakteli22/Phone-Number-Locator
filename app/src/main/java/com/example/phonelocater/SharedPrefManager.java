package com.example.phonelocater;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "my_shared_pref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static void init(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
    }

    public static void setLoggedIn(Context context, boolean isLoggedIn, String username) {
        init(context);
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    public static boolean isLoggedIn(Context context) {
        init(context);
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public static String getUsername(Context context) {
        init(context);
        return sharedPreferences.getString(KEY_USERNAME, null);
    }
}

