package com.varda.table.activity.table;

import android.os.Bundle;
import android.util.Log;
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
import com.varda.table.dialog.StudentListDialog;
import com.varda.table.dialog.StudentMissingListDialog;
import com.varda.table.factory.TableViewModelFactory;
import com.varda.table.model.Assessment;
import com.varda.table.model.Student;
import com.varda.table.utils.Constants;
import com.varda.table.view.DayItemView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
        onDrawView();

        binding.excellentStudents.setOnClickListener(studentCategoryClick("Գերազանցիկ աշակերտներ", tableViewModel.excellentStudents));
        binding.percussiveStudents.setOnClickListener(studentCategoryClick("Հարվածային աշակերտներ", tableViewModel.percussiveStudents));
        binding.lazyStudents.setOnClickListener(studentCategoryClick("Ծույլ աշակերտներ", tableViewModel.lazyStudents));
        binding.missingStudents.setOnClickListener(studentMissingClick("Բացականեր", tableViewModel.missedStudents));

        initTableHeader();
        initAverageGrade();
        getMissedStudents();
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
                    Student newStudent = new Student(name, getAssessmentForNewStudent(), "", email);
                    tableViewModel.addStudentToClass((int) tableViewModel.currentClassId, newStudent);
                    String updated = tableViewModel.getClassById((int) tableViewModel.currentClassId).getValue().getStudents();
                    adapter.setStudents(tableViewModel.getStudentListFromJson(updated));
                    onDrawView();
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
                    onDrawView();
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

    private void onDrawView() {
        int clickedIndex = getIntent().getIntExtra(Constants.CLICKED_CLASSES_ITEM, 0);
        tableViewModel.getClassById(clickedIndex);
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

        tableViewModel.currentClass.observe(this, classes -> {
            List<Student> students = tableViewModel.getStudentListFromJson(classes.getStudents());
            if (students != null) {
                adapter.setStudents(students);
            }
        });
        binding.rvTable.setAdapter(adapter);
        initAverageGrade();
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

    private void initTableHeader() {
        tableViewModel.currentClass.observe(this, classes -> {
            binding.tableHeader.daysLayout.removeAllViews();
            try {
                List<Assessment> assessments = tableViewModel.getStudentListFromJson(classes.getStudents()).get(0).getAssessment();
                for (Assessment assessment : assessments) {
                    DayItemView dayItemView = new DayItemView(this, assessment);
                    binding.tableHeader.daysLayout.addView(dayItemView);
                }
            } catch (IndexOutOfBoundsException exception) {
                Log.e("TABLE", "initTableHeader: ");
            }
        });
    }

    private View.OnClickListener studentCategoryClick(String studentsCategories, List<Student> students) {
        return view -> new StudentListDialog(TableActivity.this, students, studentsCategories).show();
    }

    private View.OnClickListener studentMissingClick(String studentsCategories, List<Student> students) {
        return view -> new StudentMissingListDialog(TableActivity.this, students, studentsCategories).show();
    }

    private void initAverageGrade() {



        tableViewModel.currentClass.observe(this, classes -> {
            AtomicInteger excellentCount = new AtomicInteger(0);
            AtomicInteger percussiveCount = new AtomicInteger(0);
            AtomicInteger lazyCount = new AtomicInteger(0);
            tableViewModel.excellentStudents.clear();
            tableViewModel.percussiveStudents.clear();
            tableViewModel.lazyStudents.clear();
            List<Student> students = tableViewModel.getStudentListFromJson(classes.getStudents());

            for (Student student : students) {
                if (!student.getAverageGrade().isEmpty()) {
                    float averageGrade = Float.parseFloat(student.getAverageGrade());
                    if (averageGrade <= 5) {
                        lazyCount.getAndIncrement();
                        tableViewModel.lazyStudents.add(student);
                    } else if (averageGrade <= 8.5) {
                        percussiveCount.getAndIncrement();
                        tableViewModel.percussiveStudents.add(student);
                    } else {
                        excellentCount.getAndIncrement();
                        tableViewModel.excellentStudents.add(student);
                    }
                }
            }

                binding.excellentStudents.setText("Գերազանցիկ աշակերտների քանակը՝ " + excellentCount.get());
                binding.percussiveStudents.setText("Հարվածային աշակերտների քանակը՝ " + percussiveCount.get());
                binding.lazyStudents.setText("Ծույլ աշակերտների քանակը՝ " + lazyCount.get());
        });
    }

    private void getMissedStudents() {
        tableViewModel.currentClass.observe(this, classes -> {
            tableViewModel.missedStudents.clear();
            for (Student student : tableViewModel.getStudentListFromJson(classes.getStudents())) {
                for (Assessment item : student.getAssessment()) {
                    if (item.getScore().contains("բ") || item.getScore().contains("հ/բ")) {
                        if (!tableViewModel.missedStudents.contains(student)) {
                            tableViewModel.missedStudents.add(student);
                        }
                    }
                }
            }
        });
    }
}
