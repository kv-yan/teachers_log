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
import com.varda.table.activity.table.TableViewModel;
import com.varda.table.callback.StudentItemClick;
import com.varda.table.model.Assessment;
import com.varda.table.model.Student;
import com.varda.table.view.AssessmentItemView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private List<Student> students;
    private StudentItemClick studentItemClick;
    private TableViewModel viewModel;

    public StudentAdapter(List<Student> students, TableViewModel viewModel, StudentItemClick studentItemClick) {
        this.students = students;
        this.studentItemClick = studentItemClick;
        this.viewModel = viewModel;
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
        holder.bind(student ,position+1, viewModel);
        holder.itemView.setOnLongClickListener(studentItemClick.onLongClick(student));
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
            assessmentLinerLayout = itemView.findViewById(R.id.student_assessment);
            studentAverageGrade = itemView.findViewById(R.id.student_average_grade);
            studentMarks = itemView.findViewById(R.id.student_marks);
            studentParentsEmail = itemView.findViewById(R.id.student_parents_email);
        }

        public void bind(Student student, int position, TableViewModel viewModel) {
            studentName.setText(position +". " + student.getName());
            studentAverageGrade.setText(student.getAverageGrade());
            studentMarks.setText(student.getMarks());
            studentParentsEmail.setText(student.getParentsEmail());

            assessmentLinerLayout.removeAllViews();
            for (Assessment assessment : student.getAssessment()) {
                AssessmentItemView assessmentItemView = new AssessmentItemView(assessmentLinerLayout.getContext(), student, assessment, viewModel);
                assessmentItemView.setAssessment(assessment.getScore());
                assessmentLinerLayout.addView(assessmentItemView);
            }
        }
    }
}
