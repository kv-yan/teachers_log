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
import com.varda.table.dialog.StudentScoreDialog;
import com.varda.table.model.Assessment;
import com.varda.table.model.Student;


public class DayItemView extends LinearLayout {
    private TextView daytextView;
    private Assessment assessment;

    public DayItemView(Context context, Assessment assessment) {
        super(context);
        this.assessment = assessment;
        init(context);
    }

    public DayItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DayItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.day_item, this, true);
        daytextView = findViewById(R.id.day_text_view);
        daytextView.setText(assessment.dayOf);
        daytextView.setRotation(-90);

    }

    public void setAssessment(String assessment) {
        daytextView.setText(assessment);
    }

}
