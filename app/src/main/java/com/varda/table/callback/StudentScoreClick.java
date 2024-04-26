package com.varda.table.callback;

import android.view.View;

import com.varda.table.model.Student;

public interface StudentScoreClick {
    public View.OnClickListener onScoreClick(Student student);
}
