package com.varda.table.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.varda.table.R;

public class AddNewStudentDialogHelper {

    public interface DialogCallback {
        void onSave(String name,String mail);

        void onUpdate(String name,String mail);

        void onDelete(String name,String mail);

        void onCancel();
    }

    public static void showAddClassDialog(Context context, DialogCallback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.add_new_student_dialog, null);
        EditText editTextStudent_name = dialogView.findViewById(R.id.student_name);
        EditText editTextParents_email = dialogView.findViewById(R.id.parents_email);

        builder.setView(dialogView).setTitle("Add Student").setPositiveButton("Ավելացնել", (dialog, which) -> {
            String studentName = editTextStudent_name.getText().toString();
            String patentEmail = editTextParents_email.getText().toString();
            callback.onSave(studentName, patentEmail);
        }).setNegativeButton("Cancel", (dialog, which) -> callback.onCancel());
        builder.create().show();
    }
}
