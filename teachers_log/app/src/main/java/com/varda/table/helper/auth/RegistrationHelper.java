package com.varda.table.helper.auth;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RegistrationHelper {
    private static final String SHARED_PREF_NAME = "user_prefs";
    private static final String KEY_USERS = "users";

    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private Gson gson;

    public RegistrationHelper(Context context) {
        this.mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public boolean registerUser(String email, String password) {
        List<User> userList = getUsers();
        userList.add(new User(email, password));
        saveUsers(userList);
        return true;
    }

    private List<User> getUsers() {
        String jsonUsers = mSharedPreferences.getString(KEY_USERS, "");
        if (jsonUsers.isEmpty()) {
            return new ArrayList<>();
        } else {
            Type type = new TypeToken<List<User>>() {
            }.getType();
            return gson.fromJson(jsonUsers, type);
        }
    }


    private void saveUsers(List<User> userList) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        String jsonUsers = gson.toJson(userList);
        editor.putString(KEY_USERS, jsonUsers);
        editor.apply();
    }
}


