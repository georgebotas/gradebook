package com.georgebotas.gradebook.Middleware;

import com.georgebotas.gradebook.Model.Grades;
import com.georgebotas.gradebook.Model.Student;
import com.georgebotas.gradebook.Model.Subject;

import java.util.ArrayList;
import java.util.List;

public interface IGradeBook {

    boolean validateStudentID(Long student_ID);

    boolean validateSubjectID(Long subject_ID);

    boolean validateGradeID(Long grade_ID);

    ArrayList<Student> showGradeBook();

    ArrayList<Subject> subjectList();

    void createStudent(String studentName, Integer studentGender, String studentClass);

    void editName(Long student_ID, String studentName);

    void editGender(Long student_ID, Integer studentGender);

    void editClass(Long student_ID, String studentClass);

    void deleteStudent(Long student_ID);

    void addGrade(Long student_ID, Integer subject_Index, Integer grade);

    void editGrade(Long student_ID, Long subject_ID, Long grade_ID, Integer grade);

    void removeGrade(Long student_ID, Long subject_ID, Long grade_ID);

    void createSubject(String subjectName);

    void deleteSubject(Long subject_ID);

    List<Grades> showGrades(Long student_ID);

    String infoName(Long student_ID);

    Integer infoGender(Long student_ID);

    String infoClass(Long student_ID);

    void test();

}
