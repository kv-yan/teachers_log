package com.varda.table.helper.time;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;

public class TimePrefHelper {

    private static final String PREF_NAME = "time_prefs";

    public static void setTimeList(Context context, List<String> timeList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("timeListSize", timeList.size());
        for (int i = 0; i < timeList.size(); i++) {
            editor.putString("time_" + i, timeList.get(i));
        }
        editor.apply();
    }

    public static List<String> getTimeList(Context context) {
        List<String> timeList = new ArrayList<>(7);
        timeList.add("");
        timeList.add("");
        timeList.add("");
        timeList.add("");
        timeList.add("");
        timeList.add("");
        timeList.add("");
        timeList.add("");
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int size = sharedPreferences.getInt("timeListSize", 0);
        for (int i = 0; i < size; i++) {
            String time = sharedPreferences.getString("time_" + i, "");
            timeList.add(i,time);
        }
        return timeList;
    }
}
