package com.varda.table.helper.auth;

import static com.varda.table.utils.Constants.AUTH_EMAIL;
import static com.varda.table.utils.Constants.AUTH_PASSWORD;
import static com.varda.table.utils.Constants.AUTH_STATE;
import static com.varda.table.utils.Constants.PREF_NAME;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthHelper {
    private Context context;

    public AuthHelper(Context context) {
        this.context = context;
    }

    public void setAuthStatus(boolean state) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        pref.edit().putBoolean(AUTH_STATE, state).apply();
    }

    public boolean getAuthStatus() {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return pref.getBoolean(AUTH_STATE, false);
    }

    public void setEmail(String state) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        pref.edit().putString(AUTH_EMAIL, state).apply();
    }

    public String getEmail() {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString(AUTH_EMAIL, null);
    }

    public void setPassword(String state) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        pref.edit().putString(AUTH_PASSWORD, state).apply();
    }

    public String getPassword() {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString(AUTH_PASSWORD, null);
    }
}
