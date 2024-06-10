package com.varda.table.helper.auth;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class LoginHelper {
    private static final String SHARED_PREF_NAME = "user_prefs";
    private static final String KEY_USERS = "users";

    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private Gson gson;

    public LoginHelper(Context context) {
        this.mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public boolean loginUser(String email, String password) {
        List<User> userList = getUsers();
        if (userList != null) {
            for (User user : userList) {
                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<User> getUsers() {
        String jsonUsers = mSharedPreferences.getString(KEY_USERS, "");
        Type type = new TypeToken<List<User>>() {
        }.getType();
        return gson.fromJson(jsonUsers, type);
    }
}