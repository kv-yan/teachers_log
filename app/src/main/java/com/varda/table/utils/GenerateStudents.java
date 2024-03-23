package com.varda.table.utils;

import com.varda.table.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateStudents {

    public static List<Student> generateStudents(int count) {
        List<Student> students = new ArrayList<>();
        String[] names = {"Ավետիսյան Գրետա", "Անահիտ Հարությունյան", "Լիլիթ Մայսյան", "Մասիս Գրիգորյան", "Լյուդմիլա Թուրինյան", "Վահագն Աբգարյան", "Կարեն Բախշյան", "Մելիք Հարությունյան", "Արման Պողոսյան", "Սիմոն Բաղդասարյան", "Լիլիթ Սիմոնյան", "Սերգեյ Դեբսունյան", "Սերգեյ Արճանյան", "Լյուսինե Սարգսյան", "Վահագն Ավետիսյան", "Նարեկ Գարագյոզյան", "Աննա Հարությունյան", "Գոռ Գոռգունյան", "Սիլվիա Թուրինյան", "Ամալյա Գրիգորյան", "Մարիամ Սողոյան", "Մարգարիտա Պարուհի", "Գևորգ Աբգարյան", "Տիգրան Սարգսյան", "Տիգրան Կահիսյան", "Մամիկոն Դանիելյան", "Բարեկ Խաչատրյան", "Կարեն Բաղդասարյան", "Կոստա Չորաքարյան"};


        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String name = names[random.nextInt(names.length)];
            List<String> assessments = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                if (random.nextBoolean()) {
                    assessments.add(String.valueOf(random.nextInt(6) + 5));
                } else {
                    assessments.add("");
                }
            }
            String averageGrade = String.valueOf(random.nextInt(4) + 7);
            String marks = "";
            String email = "armenian" + i + "@gmail.com";

            students.add(new Student(i + 1 + ". " + name, assessments, averageGrade, marks, email));
        }
        return students;
    }
}