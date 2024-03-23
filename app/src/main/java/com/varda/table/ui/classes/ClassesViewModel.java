package com.varda.table.ui.classes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;

public class ClassesViewModel extends ViewModel {

    private final MutableLiveData<List<String>> mText;

    public ClassesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(
                Arrays.asList(
                        "7 Դասարան",
                        "9 Դասարան",
                        "10 Դասարան",
                        "12 Դասարան"
                )
        );
    }

    public LiveData<List<String>> getClasses() {
        return mText;
    }
}