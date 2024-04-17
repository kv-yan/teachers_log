package com.varda.table.ui.classList.view;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.varda.table.R;
import com.varda.table.ui.classList.ClassListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ClassListView extends LinearLayout {

    private ClassListViewModel viewModel;

    private TextView dayNameEditText;
    private EditText timeEditTexts0;
    private EditText timeEditTexts1;
    private EditText timeEditTexts2;
    private EditText timeEditTexts3;
    private EditText timeEditTexts4;
    private EditText timeEditTexts5;
    private EditText timeEditTexts6;

    private int dayId;

    public ClassListView(Context context) {
        super(context);
        init(context);
    }

    public ClassListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ClassListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.class_list_field_item, this);

        dayNameEditText = findViewById(R.id.day_name);
        timeEditTexts0 = findViewById(R.id.time_1);
        timeEditTexts1 = findViewById(R.id.time_2);
        timeEditTexts2 = findViewById(R.id.time_3);
        timeEditTexts3 = findViewById(R.id.time_4);
        timeEditTexts4 = findViewById(R.id.time_5);
        timeEditTexts5 = findViewById(R.id.time_6);
        timeEditTexts6 = findViewById(R.id.time_7);

    }

    private Handler handler = new Handler();
    private Runnable textChangedRunnable = () -> {
        updateWeekData();
        Log.e("VARDANYAN", "run: saved" );
    };

    private TextWatcher createTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                handler.removeCallbacks(textChangedRunnable);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.postDelayed(textChangedRunnable, 400);
            }
        };
    }


    public void setViewModel(ClassListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private void updateWeekData() {
        String dayOfWeek = dayNameEditText.getText().toString();
        List<String> times = getTimes();
        viewModel.saveDayData(dayId, dayOfWeek, times);
    }

    private List<String> getTimes() {
        List<String> times = new ArrayList<>();

        times.add(timeEditTexts0.getText().toString());
        times.add(timeEditTexts1.getText().toString());
        times.add(timeEditTexts2.getText().toString());
        times.add(timeEditTexts3.getText().toString());
        times.add(timeEditTexts4.getText().toString());
        times.add(timeEditTexts5.getText().toString());
        times.add(timeEditTexts6.getText().toString());

        return times;
    }

    public void setTimes(List<String> times) {
        timeEditTexts0.setText(times.get(0));
        timeEditTexts1.setText(times.get(1));
        timeEditTexts2.setText(times.get(2));
        timeEditTexts3.setText(times.get(3));
        timeEditTexts4.setText(times.get(4));
        timeEditTexts5.setText(times.get(5));
        timeEditTexts6.setText(times.get(6));

        timeEditTexts0.addTextChangedListener(createTextWatcher());
        timeEditTexts1.addTextChangedListener(createTextWatcher());
        timeEditTexts2.addTextChangedListener(createTextWatcher());
        timeEditTexts3.addTextChangedListener(createTextWatcher());
        timeEditTexts4.addTextChangedListener(createTextWatcher());
        timeEditTexts5.addTextChangedListener(createTextWatcher());
        timeEditTexts6.addTextChangedListener(createTextWatcher());
    }


    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public String getDayName() {
        return dayNameEditText.getText().toString();
    }

    public void setDayName(String dayName) {
        dayNameEditText.setText(dayName);
    }
}







