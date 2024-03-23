package com.varda.table.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.varda.table.R;


public class AssessmentItemView extends LinearLayout {
    private TextView textView;

    public AssessmentItemView(Context context) {
        super(context);
        init(context);
    }

    public AssessmentItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AssessmentItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.custom_assessment_item, this, true);
        textView = findViewById(R.id.assessment_text_view);
    }

    public void setAssessment(String assessment) {
        textView.setText(assessment);
    }
}
