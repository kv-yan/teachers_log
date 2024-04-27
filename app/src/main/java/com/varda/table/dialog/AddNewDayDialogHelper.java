package com.varda.table.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.varda.table.R;

public class AddNewDayDialogHelper {

    public interface DialogCallback {
        void onSave(String inputText);

        void onUpdate(String inputText);

        void onDelete(String inputText);

        void onCancel();
    }

    public static void showAddNewDayDialog(Context context, DialogCallback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_add_class, null);
        EditText editTextClassName = dialogView.findViewById(R.id.edit_text_class_name);
        builder.setView(dialogView).setTitle("New Day").setPositiveButton("Save", (dialog, which) -> {
            String dayName = editTextClassName.getText().toString();
            callback.onSave(dayName);
        }).setNegativeButton("Cancel", (dialog, which) -> callback.onCancel());
        builder.create().show();
    }
}
