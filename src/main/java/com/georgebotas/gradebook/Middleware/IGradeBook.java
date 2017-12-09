package com.georgebotas.gradebook.Middleware;

public interface IGradeBook {

    boolean validateStudentID(Long student_ID);

    boolean validateGradeID(Long grade_ID);

    void showGradeBook();

    void createStudent(String studentName, Integer studentGender, String studentClass);

    void editName(Long student_ID, String studentName);

    void editGender(Long student_ID, Integer studentGender);

    void editClass(Long student_ID, String studentClass);

    void deleteStudent(Long student_ID);

    void addGrade(Long student_ID, Integer grade, String subject);

    void editGrade(Long student_ID, Long grade_ID, Integer grade);

    void removeGrade(Long student_ID, Long grade_ID);

    void sortStudentsName();

    void sortStudentsGender();

    void sortStudentsClass();

    void sortStudentsAverage();
}
