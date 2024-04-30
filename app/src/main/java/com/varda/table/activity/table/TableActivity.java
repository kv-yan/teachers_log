package com.varda.table.activity.table;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.varda.table.R;
import com.varda.table.adapter.StudentAdapter;
import com.varda.table.callback.StudentItemClick;
import com.varda.table.databinding.ActivityTableBinding;
import com.varda.table.dialog.AddNewDayDialogHelper;
import com.varda.table.dialog.AddNewStudentDialogHelper;
import com.varda.table.dialog.StudentDialog;
import com.varda.table.factory.TableViewModelFactory;
import com.varda.table.model.Assessment;
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

        binding.rvTable.setLayoutManager(new LinearLayoutManager(this));

        int clickedIndex = getIntent().getIntExtra(Constants.CLICKED_CLASSES_ITEM, 0);
        StudentAdapter adapter = new StudentAdapter(Collections.emptyList(), tableViewModel, new StudentItemClick() {
            @Override
            public View.OnLongClickListener onLongClick(Student student) {
                return view -> {
                    StudentDialog studentDialog = new StudentDialog(TableActivity.this, student);
                    studentDialog.show();
                    return false;
                };
            }
        });

        tableViewModel.getClassById(clickedIndex).observe(this, classes -> {
            getSupportActionBar().setTitle(classes.getClassName());
            List<Student> students = tableViewModel.getStudentListFromJson(classes.getStudents());
            if (students != null) {
                adapter.setStudents(students);
            }
        });

        binding.rvTable.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.table_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        StudentAdapter adapter = new StudentAdapter(Collections.emptyList(), tableViewModel, new StudentItemClick() {
            @Override
            public View.OnLongClickListener onLongClick(Student student) {
                return view -> {
                    StudentDialog studentDialog = new StudentDialog(TableActivity.this, student);
                    studentDialog.show();
                    return false;
                };
            }
        });
        if (item.getItemId() == R.id.add_new_student) {
            AddNewStudentDialogHelper.showAddClassDialog(this, new AddNewStudentDialogHelper.DialogCallback() {
                @Override
                public void onSave(String name, String email) {
                    Student newStudent = new Student(name, getAssessmentForNewStudent(), "", "", email);
                    tableViewModel.addStudentToClass((int) tableViewModel.currentClassId, newStudent);
                    String updated = tableViewModel.getClassById((int) tableViewModel.currentClassId).getValue().getStudents();
                    adapter.setStudents(tableViewModel.getStudentListFromJson(updated));
                    updateView();
                }


                @Override
                public void onCancel() {

                }
            });
            return true;
        } else if (item.getItemId() == R.id.add_new_day) {

            AddNewDayDialogHelper.showDatePickerDialog(this, new AddNewDayDialogHelper.DialogCallback() {
                @Override
                public void onSave(String inputText) {
                    tableViewModel.addNewDay(inputText);
                    updateView();
                }
                @Override
                public void onCancel() {

                }
            });
            return true;
        }

        binding.rvTable.setAdapter(adapter);
        return super.onOptionsItemSelected(item);
    }

    private void updateView() {
        int clickedIndex = getIntent().getIntExtra(Constants.CLICKED_CLASSES_ITEM, 0);
        StudentAdapter adapter = new StudentAdapter(Collections.emptyList(), tableViewModel, new StudentItemClick() {
            @Override
            public View.OnLongClickListener onLongClick(Student student) {
                return view -> {
                    StudentDialog studentDialog = new StudentDialog(TableActivity.this, student);
                    studentDialog.show();
                    return false;
                };
            }
        });

        tableViewModel.getClassById(clickedIndex).observe(this, classes -> {
            List<Student> students = tableViewModel.getStudentListFromJson(classes.getStudents());
            if (students != null) {
                adapter.setStudents(students);
            }
        });
        binding.rvTable.setAdapter(adapter);
    }

    private List<Assessment> getAssessmentForNewStudent() {
        int clickedIndex = getIntent().getIntExtra(Constants.CLICKED_CLASSES_ITEM, 0);

        try {
            List<Assessment> minCount = tableViewModel.getStudentListFromJson(tableViewModel.getClassById(clickedIndex).getValue().getStudents()).get(0).getAssessment();

            for (Assessment item : minCount) {
                item.setScore("");
            }
            return minCount;

        } catch (Exception exception) {
            return new ArrayList<>(1);
        }
    }

    private void initTableHeader(Student student){
        student.getAssessment();
        for (Assessment assessment: student.getAssessment()) {
            assessment.getScore();
        }
    }


}
