package com.varda.table.helper.auth;

import static com.varda.table.utils.Constants.AUTH_STATE;
import static com.varda.table.utils.Constants.PREF_NAME;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthHelper {
    private Context context;

    public AuthHelper(Context context) {
        this.context = context;
    }

    public boolean getAuthStatus() {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        boolean state = pref.getBoolean(AUTH_STATE, false);
        return state;
    }

    public void setAuthStatus(boolean state) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        pref.edit().putBoolean(AUTH_STATE, state).apply();
    }
}
