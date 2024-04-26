package com.varda.table.callback;

import android.view.View;

import com.varda.table.model.Student;

public interface StudentItemClick {
    public View.OnLongClickListener onLongClick(Student student);
    public View.OnClickListener onAssessmentClick(Student student);
}
