package com.varda.table.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.varda.table.R;
import com.varda.table.callback.StudentItemClick;
import com.varda.table.model.Student;
import com.varda.table.view.AssessmentItemView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private List<Student> students;
    private StudentItemClick onLongClickListener;

    public StudentAdapter(List<Student> students) {
        this.students = students;
    }

    public StudentAdapter(List<Student> students, StudentItemClick onLongClickListener) {
        this.students = students;
        this.onLongClickListener = onLongClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = students.get(position);
        holder.bind(student);

        holder.itemView.setOnLongClickListener(onLongClickListener.onLongClick(student));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void setStudents(List<Student> newStudents) {
        this.students = newStudents;
        this.notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView studentName;
        LinearLayout assessmentLinerLayout;
        EditText studentAverageGrade;
        EditText studentMarks;
        EditText studentParentsEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.student_name);
            assessmentLinerLayout = itemView.findViewById(R.id.student_assessment_recycler_view);
            studentAverageGrade = itemView.findViewById(R.id.student_average_grade);
            studentMarks = itemView.findViewById(R.id.student_marks);
            studentParentsEmail = itemView.findViewById(R.id.student_parents_email);
        }

        public void bind(Student student) {
            studentName.setText(student.getName());
            studentAverageGrade.setText(student.getAverageGrade());
            studentMarks.setText(student.getMarks());
            studentParentsEmail.setText(student.getParentsEmail());

            // Remove all existing assessment views
            assessmentLinerLayout.removeAllViews();

            // Add assessment views
            for (String assessment : student.getAssessment()) {
                AssessmentItemView assessmentItemView = new AssessmentItemView(assessmentLinerLayout.getContext());
                assessmentItemView.setAssessment(assessment);
                assessmentLinerLayout.addView(assessmentItemView);
            }
        }
    }
}
