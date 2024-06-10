package com.varda.table.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.varda.table.R;
import com.varda.table.helper.time.TimePrefHelper;

import java.util.ArrayList;
import java.util.List;

public class AddStartEndTimeDialogHelper {

    public interface DialogCallback {
        void onSave(List<String> list);

        void onCancel();
    }

    public static void showAddClassDialog(Context context, DialogCallback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_add_start_end_time, null);

        List<String> currentTimes  = TimePrefHelper.getTimeList(context);



        EditText time1 = view.findViewById(R.id.time_1);
        EditText time2 = view.findViewById(R.id.time_2);
        EditText time3 = view.findViewById(R.id.time_3);
        EditText time4 = view.findViewById(R.id.time_4);
        EditText time5 = view.findViewById(R.id.time_5);
        EditText time6 = view.findViewById(R.id.time_6);
        EditText time7 = view.findViewById(R.id.time_7);
        EditText time8 = view.findViewById(R.id.time_8);

        time1.setText(currentTimes.get(0));
        time2.setText(currentTimes.get(1));
        time3.setText(currentTimes.get(2));
        time4.setText(currentTimes.get(3));
        time5.setText(currentTimes.get(4));
        time6.setText(currentTimes.get(5));
        time7.setText(currentTimes.get(6));
        time8.setText(currentTimes.get(7));


        builder.setView(view).setTitle("Դասաժամեր").setPositiveButton("Պահպանել", (dialog, which) -> {
            List<String> times = new ArrayList();
            times.add(time1.getText().toString());
            times.add(time2.getText().toString());
            times.add(time3.getText().toString());
            times.add(time4.getText().toString());
            times.add(time5.getText().toString());
            times.add(time6.getText().toString());
            times.add(time7.getText().toString());
            times.add(time8.getText().toString());

            callback.onSave(times);
        }).setNegativeButton("Չեղարկել", (dialog, which) -> {});
        builder.create().show();
    }
}
