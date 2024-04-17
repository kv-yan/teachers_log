package com.varda.table.ui.classList.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;

public class WeekSharedPreferencesHelper {

    private static final String PREF_NAME = "MyPrefs";

    private SharedPreferences sharedPreferences;

    public WeekSharedPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveDayData(int dayId, String dayName, List<String> times) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("day_" + dayId + "_name", dayName);
        for (int i = 0; i < times.size(); i++) {
            editor.putString("day_" + dayId + "_time_" + (i+1), times.get(i));
        }
        editor.apply();
    }

    public String getDayName(int dayId) {
        return sharedPreferences.getString("day_" + dayId + "_name", "");
    }

    public List<String> getTimes(int dayId) {
        List<String> times = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            String time = sharedPreferences.getString("day_" + dayId + "_time_" + i, "");
            times.add(time);
        }
        return times;
    }

    public void deleteDayData(int dayId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("day_" + dayId + "_name");
        for (int i = 1; i <= 7; i++) {
            editor.remove("day_" + dayId + "_time_" + i);
        }
        editor.apply();
    }

    public void clearAllData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
    }
}

