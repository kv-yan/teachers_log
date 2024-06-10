package com.varda.table.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.varda.table.R;
import com.varda.table.activity.table.TableViewModel;
import com.varda.table.callback.StudentScoreClick;
import com.varda.table.dialog.StudentScoreDialog;
import com.varda.table.model.Assessment;
import com.varda.table.model.Student;


public class AssessmentItemView extends LinearLayout {
    private TextView textView;
    private Assessment assessment;
    private TableViewModel viewModel;

    public AssessmentItemView(Context context, Student student, Assessment assessment, TableViewModel viewModel) {
        super(context);
        this.viewModel = viewModel;
        this.assessment = assessment;
        init(context, student);
    }

    public AssessmentItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AssessmentItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context, Student student) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_assessment_item, this, true);
        textView = findViewById(R.id.assessment_text_view);

        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentScoreDialog dialog = new StudentScoreDialog(context, student,assessment, textView, viewModel);
                dialog.show();
            }
        });
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_assessment_item, this, true);
        textView = findViewById(R.id.assessment_text_view);
    }

    public void setAssessment(String assessment) {
        textView.setText(assessment);
    }

}
