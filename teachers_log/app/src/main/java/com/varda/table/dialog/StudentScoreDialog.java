package com.varda.table.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.varda.table.R;
import com.varda.table.activity.table.TableViewModel;
import com.varda.table.adapter.ScoreAdapter;
import com.varda.table.callback.StudentScoreClick;
import com.varda.table.helper.mail.MailHelper;
import com.varda.table.model.Assessment;
import com.varda.table.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentScoreDialog extends AlertDialog {
    Student student;
    TextView textView;
    Context context;
    Assessment assessment;
    TableViewModel viewModel;

    public StudentScoreDialog(Context context, Student student, Assessment assessment, TextView textView, TableViewModel viewModel) {
        super(context);
        this.context = context;
        this.student = student;
        this.textView = textView;
        this.assessment = assessment;
        this.viewModel = viewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_assesment_dialog);

        TextView nameTextView = findViewById(R.id.student_name);
        RecyclerView scoreRecyclerView = findViewById(R.id.score_recycler_view);

        nameTextView.setText(student.getName());

        scoreRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        ScoreAdapter adapter = getScoreAdapter();
        scoreRecyclerView.setAdapter(adapter);

        adapter.setActionClick((student, clickedScore) -> view -> {
            textView.setText(clickedScore);
            int missedCount = 0;
            for (Assessment assessments : student.getAssessment()) {
                if (assessments.getDayOf() == assessment.getDayOf()) {
                    assessments.setScore(clickedScore);
                    if (clickedScore == "բ" || clickedScore == "հ/բ") {
                        missedCount += 1;
                    }
                }
            }


            if (clickedScore.contains("բ")) {
                StringBuilder msgContentBuilder = new StringBuilder();
                student.calculateMidAssessment();
                msgContentBuilder.append("Հարգելի ").append(student.getName()).append("ի ծնող, ուզում ենք տեղեկացնել, որ ձեր երեխան ստացել է բացակա ").append(assessment.getDayOf()).append("-ին և ունի ").append(missedCount).append(" բացակայություն ").append(student.getLastAssessment().getDayOf()).append("-ի դրությամբ:\nԵրեխայի միջին գնահատականն է` ").append(student.getAverageGrade()).append("\nՀարգանքներով` ClassNote");

                MailHelper.send(context, student.getParentsEmail(), "Երեխայի բացակայություններ", msgContentBuilder.toString());
                Log.e("Vardanyan", "onClicked " + clickedScore + "text :: " + msgContentBuilder);
            }
            if (clickedScore == "1" || clickedScore == "2" || clickedScore == "3" || clickedScore == "4") {

                student.getAverageGrade();
                student.calculateMidAssessment();
                String msgContent = "Հարգելի " + student.getName() + "ի ծնող, ուզում ենք տեղեկացնել, որ ձեր երեխան ստացել է անբավարար գնահատական " + assessment.getDayOf() + " օրը։ \nԵրեխայի միջին գնահատականն է` " + student.getAverageGrade() + "\nԲացակայությունների քանակը՝ " + student.getMissedCount() + "\nՀարգանքներով` ClassNote";
                MailHelper.send(context, student.getParentsEmail(), "Երեխայի անբավարար գնահատական", msgContent);

                Log.e("Vardanyan", "onClicked " + clickedScore + "text :: " + msgContent);
            }

            viewModel.saveAssessment(student);
            StudentScoreDialog.this.cancel();
        });
    }

    @NonNull
    private ScoreAdapter getScoreAdapter() {
        List<String> scoreList = new ArrayList<>();
        scoreList.add("1");
        scoreList.add("2");
        scoreList.add("3");
        scoreList.add("4");
        scoreList.add("5");
        scoreList.add("6");
        scoreList.add("7");
        scoreList.add("8");
        scoreList.add("9");
        scoreList.add("10");
        scoreList.add("բ");
        scoreList.add("հ/բ");
        scoreList.add("ու");

        return new ScoreAdapter(context, student, scoreList);
    }
}
