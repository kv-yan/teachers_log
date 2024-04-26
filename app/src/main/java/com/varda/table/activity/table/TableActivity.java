package com.varda.table.activity.table;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.varda.table.callback.StudentItemClick;
import com.varda.table.adapter.StudentAdapter;
import com.varda.table.databinding.ActivityTableBinding;
import com.varda.table.dialog.AddNewStudentDialogHelper;
import com.varda.table.dialog.StudentDialog;
import com.varda.table.factory.TableViewModelFactory;
import com.varda.table.model.Student;
import com.varda.table.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableActivity extends AppCompatActivity {
    TableViewModel tableViewModel;

    ActivityTableBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TableViewModelFactory factory = new TableViewModelFactory(getApplication());
        tableViewModel = new ViewModelProvider(this, factory).get(TableViewModel.class);


        StudentAdapter adapter = new StudentAdapter(Collections.emptyList(), new StudentItemClick() {
            @Override
            public View.OnLongClickListener onLongClick(Student student) {
                return view -> {
                    StudentDialog studentDialog = new StudentDialog(TableActivity.this,student);
                    studentDialog.show();
                    return false;
                };
            }

            @Override
            public View.OnClickListener onAssessmentClick(Student student) {
                return null;
            }
        });
        binding.rvTable.setLayoutManager(new LinearLayoutManager(this));

        int clickedIndex = getIntent().getIntExtra(Constants.CLICKED_CLASSES_ITEM, 0);


        tableViewModel.getClassById(clickedIndex).observe(this, classes -> {
            List<Student> students = tableViewModel.getStudentListFromJson(classes.getStudents());
            if (students != null) {
                adapter.setStudents(students);
            }
        });


        binding.button.setOnClickListener(view -> {
            AddNewStudentDialogHelper.showAddClassDialog(this, new AddNewStudentDialogHelper.DialogCallback() {
                @Override
                public void onSave(String name, String email) {

                    Student newStudent = new Student(name, new ArrayList<>(), "", "", email);
                    tableViewModel.addStudentToClass((int) tableViewModel.currentClassId, newStudent);
                    String updated = tableViewModel.getClassById((int) tableViewModel.currentClassId).getValue().getStudents();
                    adapter.setStudents(tableViewModel.getStudentListFromJson(updated));
                }

                @Override
                public void onUpdate(String name, String mail) {

                }

                @Override
                public void onDelete(String name, String mail) {

                }

                @Override
                public void onCancel() {

                }
            });
        });


        binding.rvTable.setAdapter(adapter);
    }
}
