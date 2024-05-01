package com.varda.table.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.varda.table.R;
import com.varda.table.callback.StudentScoreClick;
import com.varda.table.model.Student;

import java.util.List;

public class StudentsNameAdapter extends RecyclerView.Adapter<StudentsNameAdapter.ViewHolder> {

    private List<Student> scoreList;

    public StudentsNameAdapter(List<Student> studentList) {
        this.scoreList = studentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_name_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = scoreList.get(position);
        holder.noteContent.setText(student.getName());
    }


    @Override
    public int getItemCount() {
        return scoreList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView noteContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteContent = itemView.findViewById(R.id.student_name);
        }
    }
}
