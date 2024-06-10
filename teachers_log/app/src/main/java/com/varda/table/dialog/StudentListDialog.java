package com.varda.table.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.varda.table.R;
import com.varda.table.adapter.ScoreAdapter;
import com.varda.table.adapter.StudentsNameAdapter;
import com.varda.table.model.Assessment;
import com.varda.table.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentListDialog extends AlertDialog {
    List<Student> students;
    String studentsCategory;
    Context context;

    public StudentListDialog(Context context, List<Student> students, String studentsCategory) {
        super(context);
        this.context = context;
        this.students = students;
        this.studentsCategory = studentsCategory;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_assesment_dialog);

        TextView nameTextView = findViewById(R.id.student_name);
        RecyclerView scoreRecyclerView = findViewById(R.id.score_recycler_view);

        nameTextView.setText(studentsCategory);

        scoreRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        StudentsNameAdapter adapter = new StudentsNameAdapter(students);
        scoreRecyclerView.setAdapter(adapter);

    }
}
