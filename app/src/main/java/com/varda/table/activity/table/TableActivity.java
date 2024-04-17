package com.varda.table.activity.table;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.varda.table.R;
import com.varda.table.adapter.StudentAdapter;
import com.varda.table.factory.ClassesViewModelFactory;
import com.varda.table.factory.TableViewModelFactory;
import com.varda.table.model.Student;
import com.varda.table.ui.classes.ClassesViewModel;
import com.varda.table.utils.Constants;
import com.varda.table.utils.GenerateStudents;

import java.util.List;

public class TableActivity extends AppCompatActivity {
    TableViewModel tableViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        TableViewModelFactory factory = new TableViewModelFactory(getApplication());
        tableViewModel = new ViewModelProvider(this, factory).get(TableViewModel.class);
        int clickedIndex = getIntent().getIntExtra(Constants.CLICKED_CLASSES_ITEM, 0);

        Toast.makeText(this, "Clicked " + clickedIndex, Toast.LENGTH_SHORT).show();

        List<Student> students = GenerateStudents.generateStudents(40);

        RecyclerView recyclerView = findViewById(R.id.rv_table);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        StudentAdapter adapter = new StudentAdapter(students);
        recyclerView.setAdapter(adapter);
    }
}
