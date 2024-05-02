package com.varda.table.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.varda.table.R;
import com.varda.table.model.Assessment;
import com.varda.table.model.Student;

import java.util.List;
import java.util.Objects;

public class StudentsMissingAdapter extends RecyclerView.Adapter<StudentsMissingAdapter.ViewHolder> {

    private List<Student> scoreList;

    public StudentsMissingAdapter(List<Student> studentList) {
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
        int counter = 0;
        for (Assessment assessment : student.getAssessment()) {
            if (Objects.equals(assessment.getScore(), "բ") || Objects.equals(assessment.getScore(), "հ/բ")) {
                counter+=1;
            }
        }
        holder.name.setText(student.getName());
        holder.missedCount.setText(counter + " բացակա");
    }


    @Override
    public int getItemCount() {
        return scoreList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView missedCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.student_name);
            missedCount = itemView.findViewById(R.id.missed_count);
        }
    }
}
