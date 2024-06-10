package com.varda.table.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.varda.table.R;
import com.varda.table.model.Student;
import com.varda.table.utils.ClipboardUtil;

public class StudentDialog extends AlertDialog {
    private final String studentName;
    private final int assessment;
    private final int missingCount;
    private final String notes;
    private final String parentsEmail;

    public StudentDialog(Context context, Student student) {
        super(context);
        this.studentName = student.getName();

        this.assessment = student.calculateMidAssessment();
        this.missingCount = student.getMissedCount();
        this.notes = student.getMarks();
        this.parentsEmail = student.getParentsEmail();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_info_dialog);

        TextView nameTextView = findViewById(R.id.student_name);
        TextView assessmentTextView = findViewById(R.id.student_assessment);
        TextView missingCountTextView = findViewById(R.id.student_missing_count);
        TextView notesTextView = findViewById(R.id.student_notes);
        TextView emailTextView = findViewById(R.id.student_parents_email);

        nameTextView.setText(studentName);
        assessmentTextView.setText("Միջին գնահատական` " + assessment);
        missingCountTextView.setText("Բացակաների քանակ` " + missingCount);
        if (notes.isEmpty()) {
            notesTextView.setText("Նշունմեր չկան");
        } else {
            notesTextView.setText("Նշում՝ " + notes);
        }

        emailTextView.setText("Ծնողի էլ հասցեն` \n" + parentsEmail);
        emailTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ClipboardUtil.copyToClipboard(getContext(), parentsEmail, "Էլ հասցեն պատճենվել է");
                return false;
            }
        });
    }
}
