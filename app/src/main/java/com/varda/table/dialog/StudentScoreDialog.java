package com.varda.table.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.varda.table.R;
import com.varda.table.activity.table.TableViewModel;
import com.varda.table.adapter.ScoreAdapter;
import com.varda.table.model.Assessment;
import com.varda.table.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentScoreDialog extends Dialog {
    Student student;
    TextView textView;
    Context context;
    Assessment assessment;
    TableViewModel viewModel;

    public StudentScoreDialog(Context context, Student student, Assessment assessment, TextView textView, TableViewModel viewModel) {
        super(context);
        this.context = context;
        this.student = student;
        this.textView = textView;
        this.assessment = assessment;
        this.viewModel = viewModel;
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

        adapter.setActionClick(clickedScore -> (View.OnClickListener) view -> {
            textView.setText(clickedScore);

            for (Assessment assessments: student.getAssessment()){
                if (assessments.getDayOf() == assessment.getDayOf()){
                    assessments.setScore(clickedScore);
                    Log.e("VARDANYAN", "onCreate: UPDATED" );
                }
            }

            viewModel.saveAssessment(student);
            StudentScoreDialog.this.cancel();
        });
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

        return new ScoreAdapter(context, student, scoreList);
    }
}
