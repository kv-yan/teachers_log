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

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    private List<String> scoreList;
    private Context context;
    private Student student;
    private StudentScoreClick onClickListener;

    public ScoreAdapter(Context context, Student student, List<String> scoreList) {
        this.scoreList = scoreList;
        this.context = context;
        this.student = student;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assesment_number_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String score = scoreList.get(position);
        holder.noteContent.setText(score);
        holder.itemView.setOnClickListener(onClickListener.onScoreClick(score));
    }


    @Override
    public int getItemCount() {
        return scoreList.size();
    }

    public void setActionClick(StudentScoreClick onClickListener) {
        this.onClickListener = onClickListener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView noteContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteContent = itemView.findViewById(R.id.assessment_number);
        }
    }
}
