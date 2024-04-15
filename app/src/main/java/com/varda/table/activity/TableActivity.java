package com.varda.table.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.varda.table.R;
import com.varda.table.adapter.StudentAdapter;
import com.varda.table.model.Student;
import com.varda.table.utils.GenerateStudents;

import java.util.List;

public class TableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        List<Student> students = GenerateStudents.generateStudents(40);

        RecyclerView recyclerView = findViewById(R.id.rv_table);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        StudentAdapter adapter = new StudentAdapter(students);
        recyclerView.setAdapter(adapter);
    }
}
