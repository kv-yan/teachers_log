package com.varda.table.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddNewDayDialogHelper {

    public interface DialogCallback {
        void onSave(String inputText);


        void onCancel();
    }


    public static void showDatePickerDialog(Context context, DialogCallback callback) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
                String selectedDate = sdf.format(calendar.getTime());

                callback.onSave(selectedDate);
                Toast.makeText(context, selectedDate, Toast.LENGTH_SHORT).show();
            }
        }, year, month, dayOfMonth);

        datePickerDialog.show();
    }

}
