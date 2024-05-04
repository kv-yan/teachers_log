package com.varda.table.ui.classList.view;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.varda.table.R;
import com.varda.table.dialog.AddStartEndTimeDialogHelper;
import com.varda.table.helper.time.TimePrefHelper;
import com.varda.table.ui.classList.ClassListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ClassListView extends LinearLayout {

    private ClassListViewModel viewModel;

    private TextView dayNameEditText;
    private EditText time1;
    private EditText time2;
    private EditText time3;
    private EditText time4;
    private EditText time5;
    private EditText time6;
    private EditText time7;
    private EditText time8;

    private TextView time1EndTime;
    private TextView time2EndTime;
    private TextView time3EndTime;
    private TextView time4EndTime;
    private TextView time5EndTime;
    private TextView time6EndTime;
    private TextView time7EndTime;
    private TextView time8EndTime;


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
        time1 = findViewById(R.id.time_1);
        time2 = findViewById(R.id.time_2);
        time3 = findViewById(R.id.time_3);
        time4 = findViewById(R.id.time_4);
        time5 = findViewById(R.id.time_5);
        time6 = findViewById(R.id.time_6);
        time7 = findViewById(R.id.time_7);
        time8 = findViewById(R.id.time_8);

        time1EndTime = findViewById(R.id.time_1_end_time);
        time2EndTime = findViewById(R.id.time_2_end_time);
        time3EndTime = findViewById(R.id.time_3_end_time);
        time4EndTime = findViewById(R.id.time_4_end_time);
        time5EndTime = findViewById(R.id.time_5_end_time);
        time6EndTime = findViewById(R.id.time_6_end_time);
        time7EndTime = findViewById(R.id.time_7_end_time);
        time8EndTime = findViewById(R.id.time_8_end_time);
    }

    private TextWatcher createTextWatcher() {
        return new TextWatcher() {
            private static final long DELAY_MILLIS = 500;
            private final Handler handler = new Handler();
            private Runnable textChangedRunnable = () -> {
                updateWeekData();
            };

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                handler.removeCallbacks(textChangedRunnable);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Do nothing
            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.postDelayed(textChangedRunnable, DELAY_MILLIS);
            }
        };
    }



    public void setViewModel(ClassListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private List<String> getTimes() {
        List<String> times = new ArrayList<>();

        times.add(time1.getText().toString());
        times.add(time2.getText().toString());
        times.add(time3.getText().toString());
        times.add(time4.getText().toString());
        times.add(time5.getText().toString());
        times.add(time6.getText().toString());
        times.add(time7.getText().toString());
        times.add(time8.getText().toString());

        return times;
    }

    public void setTimes(List<String> times, List<String> startEndTime) {
        time1.setText(addNumbers(times.get(0), 1));
        time2.setText(addNumbers(times.get(1), 2));
        time3.setText(addNumbers(times.get(2), 3));
        time4.setText(addNumbers(times.get(3), 4));
        time5.setText(addNumbers(times.get(4), 5));
        time6.setText(addNumbers(times.get(5), 6));
        time7.setText(addNumbers(times.get(6), 7));
        time8.setText(addNumbers(times.get(7), 8));

        time1.addTextChangedListener(createTextWatcher());
        time2.addTextChangedListener(createTextWatcher());
        time3.addTextChangedListener(createTextWatcher());
        time4.addTextChangedListener(createTextWatcher());
        time5.addTextChangedListener(createTextWatcher());
        time6.addTextChangedListener(createTextWatcher());
        time7.addTextChangedListener(createTextWatcher());
        time8.addTextChangedListener(createTextWatcher());
        try {
            time1EndTime.setText(startEndTime.get(0));
            time2EndTime.setText(startEndTime.get(1));
            time3EndTime.setText(startEndTime.get(2));
            time4EndTime.setText(startEndTime.get(3));
            time5EndTime.setText(startEndTime.get(4));
            time6EndTime.setText(startEndTime.get(5));
            time7EndTime.setText(startEndTime.get(6));
            time8EndTime.setText(startEndTime.get(7));
        } catch (IndexOutOfBoundsException ex) {
            time1EndTime.setText("");
            time2EndTime.setText("");
            time3EndTime.setText("");
            time4EndTime.setText("");
            time5EndTime.setText("");
            time6EndTime.setText("");
            time7EndTime.setText("");
            time8EndTime.setText("");
        }

        time1EndTime.setOnClickListener(timeClick());
        time2EndTime.setOnClickListener(timeClick());
        time3EndTime.setOnClickListener(timeClick());
        time4EndTime.setOnClickListener(timeClick());
        time5EndTime.setOnClickListener(timeClick());
        time6EndTime.setOnClickListener(timeClick());
        time7EndTime.setOnClickListener(timeClick());
        time8EndTime.setOnClickListener(timeClick());
    }

    private View.OnClickListener timeClick() {
        return view -> AddStartEndTimeDialogHelper.showAddClassDialog(getContext(), new AddStartEndTimeDialogHelper.DialogCallback() {
            @Override
            public void onSave(List<String> list) {
                TimePrefHelper.setTimeList(getContext(), list);
            }

            @Override
            public void onCancel() {

            }
        });
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


    private String addNumbers(String text, int number) {
        return number + ". " + text;
    }

    private void updateWeekData() {
        List<String> times = new ArrayList<>();
        times.add(stripNumber(time1.getText().toString()));
        times.add(stripNumber(time2.getText().toString()));
        times.add(stripNumber(time3.getText().toString()));
        times.add(stripNumber(time4.getText().toString()));
        times.add(stripNumber(time5.getText().toString()));
        times.add(stripNumber(time6.getText().toString()));
        times.add(stripNumber(time7.getText().toString()));
        times.add(stripNumber(time8.getText().toString()));

        String dayOfWeek = dayNameEditText.getText().toString();
        viewModel.saveDayData(dayId, dayOfWeek, times);
    }

    private String stripNumber(String text) {
        String[] parts = text.split("\\.", 2);
        if (parts.length > 1) {
            return parts[1].trim();
        }
        return text;
    }
}