package com.varda.table.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.varda.table.R;
import com.varda.table.callback.StudentScoreClick;
import com.varda.table.dialog.StudentScoreDialog;
import com.varda.table.model.Student;

import kotlin.jvm.internal.Lambda;


public class AssessmentItemView extends LinearLayout {
    private TextView textView;
    private StudentScoreClick scoreClick;

    public AssessmentItemView(Context context, Student student) {
        super(context);
        init(context,student);
    }

    public AssessmentItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AssessmentItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context ,Student student) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_assessment_item, this, true);
        textView = findViewById(R.id.assessment_text_view);

        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentScoreDialog dialog = new StudentScoreDialog(context , student ,textView);
                dialog.show();
            }
        });
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_assessment_item, this, true);
        textView = findViewById(R.id.assessment_text_view);
    }

    private void init(Context context, StudentScoreClick click) {
        LayoutInflater.from(context).inflate(R.layout.custom_assessment_item, this, true);
        textView = findViewById(R.id.assessment_text_view);
        this.scoreClick = click;
    }

    public void setAssessment(String assessment) {
        textView.setText(assessment);
    }

    public void setScoreClick(StudentScoreClick click) {
        this.scoreClick = click;
    }
}
