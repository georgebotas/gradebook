package com.georgebotas.gradebook.DB;

import com.georgebotas.gradebook.Model.Student;

import java.util.ArrayList;

public interface IDBOps {

    boolean validateStudentID(Long student_ID);

    boolean validateGradeID(Long grades_ID);

    ArrayList<Student> studentList();

    void createStudent(String studentName, Integer studentGender, String studentClass);

    void editName(Long student_ID, String studentName);

    void editGender(Long student_ID, Integer studentGender);

    void editClass(Long student_ID, String studentClass);

    void deleteStudent(Long student_ID);

    void addGrade(Long student_ID, Integer grade, String subject);

    void editGrade(Long student_ID, Long grade_ID, Integer grade);

    void removeGrade(Long student_ID, Long grade_ID);
}
