package com.varda.table.ui.classList;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.varda.table.ui.classList.helper.WeekSharedPreferencesHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;

public class ClassListViewModel extends AndroidViewModel {

    private MutableLiveData<List<List<String>>> classList;
     WeekSharedPreferencesHelper sharedPreferencesHelper;

    public ClassListViewModel(Application application) {
        super(application);
        classList = new MutableLiveData<>();
        sharedPreferencesHelper = new WeekSharedPreferencesHelper(application);

        fetchData();
    }

    private void fetchData() {
        List<List<String>> formattedData = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            List<String> dayData = getDataForDay(i);
            formattedData.add(dayData);
        }
        classList.setValue(formattedData);
    }

    private List<String> getDataForDay(int dayId) {
        List<String> dayData = new ArrayList<>();
        String dayName = sharedPreferencesHelper.getDayName(dayId);
        if (dayName.isEmpty()) {
            dayName = getFallbackDayName(dayId);
        }
//        dayData.add(dayName);

        List<String> times = sharedPreferencesHelper.getTimes(dayId);
        dayData.addAll(times);

        return dayData;
    }

    private String getFallbackDayName(int dayId) {

        switch (dayId) {
            case 0:
                return "Երկ";
            case 1:
                return "Երք";
            case 2:
                return "Չրք";
            case 3:
                return "Հնգ";
            case 4:
                return "Ուրբ";
            case 5:
                return "Շբթ";
            default:
                return "";
        }
    }

    public LiveData<List<List<String>>> getClassList() {
        return classList;
    }

    public void saveDayData(int dayId, String dayName, List<String> times) {
        sharedPreferencesHelper.saveDayData(dayId, dayName, times);
        fetchData();
    }

    public void deleteDayData(int dayId) {
        sharedPreferencesHelper.deleteDayData(dayId);
        fetchData();
    }

    public void clearAllData() {
        sharedPreferencesHelper.clearAllData();
        fetchData();
    }
}


