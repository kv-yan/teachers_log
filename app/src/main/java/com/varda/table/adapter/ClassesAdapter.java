package com.varda.table.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.varda.table.R;
import com.varda.table.activity.TableActivity;

import java.util.List;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.ViewHolder> {

    private List<String> classesList;

    public ClassesAdapter(List<String> classesList) {
        this.classesList = classesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classes_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String className = classesList.get(position);
        holder.classesItemText.setText(className);

        holder.itemView.setOnClickListener(view -> {
            Context context = view.getContext();

            Intent tableIntent = new Intent(context, TableActivity.class);

            context.startActivity(tableIntent);
        });
    }


    @Override
    public int getItemCount() {
        return classesList.size();
    }

    public void setClasses(List<String> classes) {
        classesList.clear();
        classesList.addAll(classes);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView classesItemText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            classesItemText = itemView.findViewById(R.id.classes_item_text);
        }
    }
}

