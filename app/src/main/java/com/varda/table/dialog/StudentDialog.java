package com.varda.table.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.varda.table.R;
import com.varda.table.model.Student;

public class StudentDialog extends Dialog {
    private final String studentName;
    private final int assessment;
    private final int missingCount;
    private final String notes;
    private final String parentsEmail;

    public StudentDialog(Context context, Student student) {
        super(context);
        this.studentName = student.getName();

        this.assessment = student.calculateMidAssessment() ;
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
        assessmentTextView.setText("Միջին գնահատական` \n   " + assessment);
        missingCountTextView.setText("Բացակաների քանակ` \n    " + missingCount);
        notesTextView.setText(notes);
        emailTextView.setText("Ծնողի էլ հասցեն` \n    " + parentsEmail);
    }
}
