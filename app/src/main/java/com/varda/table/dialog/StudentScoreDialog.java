package com.varda.table.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.varda.table.R;
import com.varda.table.adapter.ScoreAdapter;
import com.varda.table.callback.StudentScoreClick;
import com.varda.table.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentScoreDialog extends Dialog {
    Student student;
    Context context;
    StudentScoreClick studentItemClick;

    public StudentScoreDialog(Context context, Student student, StudentScoreClick studentItemClick) {
        super(context);
        this.context = context;
        this.student = student;
        this.studentItemClick = studentItemClick;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_assesment_dialog);

        TextView nameTextView = findViewById(R.id.student_name);
        RecyclerView scoreRecyclerView = findViewById(R.id.score_recycler_view);

        nameTextView.setText(student.getName());

        scoreRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        ScoreAdapter adapter = getScoreAdapter();
        scoreRecyclerView.setAdapter(adapter);
    }

    @NonNull
    private ScoreAdapter getScoreAdapter() {
        List<String> scoreList = new ArrayList<>();
        scoreList.add("1");
        scoreList.add("2");
        scoreList.add("3");
        scoreList.add("4");
        scoreList.add("5");
        scoreList.add("6");
        scoreList.add("7");
        scoreList.add("8");
        scoreList.add("9");
        scoreList.add("10");
        scoreList.add("բ");
        scoreList.add("հ/բ");
        scoreList.add("ու");

        return new ScoreAdapter(context, student, scoreList, studentItemClick);
    }
}
