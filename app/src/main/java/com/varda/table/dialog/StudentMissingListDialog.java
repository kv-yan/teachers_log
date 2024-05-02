package com.varda.table.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.varda.table.R;
import com.varda.table.adapter.StudentsMissingAdapter;
import com.varda.table.adapter.StudentsNameAdapter;
import com.varda.table.model.Student;

import java.util.List;

public class StudentMissingListDialog extends AlertDialog {
    List<Student> students;
    String studentsCategory;
    Context context;

    public StudentMissingListDialog(Context context, List<Student> students, String studentsCategory) {
        super(context);
        this.context = context;
        this.students = students;
        this.studentsCategory = studentsCategory;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_assesment_dialog);

        TextView name = findViewById(R.id.student_name);
        RecyclerView scoreRecyclerView = findViewById(R.id.score_recycler_view);

        name.setText(studentsCategory);

        scoreRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        StudentsMissingAdapter adapter = new StudentsMissingAdapter(students);
        scoreRecyclerView.setAdapter(adapter);

    }
}
