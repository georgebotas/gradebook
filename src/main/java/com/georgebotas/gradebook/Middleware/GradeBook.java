package com.georgebotas.gradebook.Middleware;

import com.georgebotas.gradebook.DB.IDBOps;
import com.georgebotas.gradebook.Model.Grades;
import com.georgebotas.gradebook.Model.Student;
import com.georgebotas.gradebook.Model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GradeBook implements IGradeBook {

    @Autowired
    IDBOps idbops;

    public GradeBook() { }

    public boolean validateStudentID(Long student_ID) { return idbops.validateStudentID(student_ID); }

    public boolean validateSubjectID(Long subject_ID) { return idbops.validateSubjectID(subject_ID); }

    public boolean validateGradeID(Long grade_ID) { return idbops.validateGradeID(grade_ID); }

    public ArrayList<Student> showGradeBook(){ return idbops.studentList(); }

    public ArrayList<Subject> subjectList(){ return idbops.subjectList(); }

    public void createStudent(String studentName, Integer studentGender, String studentClass) { idbops.createStudent(studentName, studentGender, studentClass); }

    public void editName(Long student_ID, String studentName) { idbops.editName(student_ID, studentName); }

    public void editGender(Long student_ID, Integer studentGender) { idbops.editGender(student_ID, studentGender); }

    public void editClass(Long student_ID, String studentClass) { idbops.editClass(student_ID, studentClass); }

    public void deleteStudent(Long student_ID) { idbops.deleteStudent(student_ID); }

    public void createSubject(String subjectName) { idbops.createSubject(subjectName);}

    public void deleteSubject(Long subject_ID) { idbops.deleteSubject(subject_ID); }

    public void addGrade(Long student_ID, Integer subject_Index, Integer grade) { idbops.addGrade(student_ID, subject_Index, grade);}

    public void editGrade(Long student_ID, Long subject_ID, Long grade_ID, Integer grade) { idbops.editGrade(student_ID, subject_ID, grade_ID ,grade);}

    public void removeGrade(Long student_ID, Long subject_ID, Long grade_ID) { idbops.removeGrade(student_ID, subject_ID, grade_ID);}

    public List<Grades> showGrades(Long student_ID){return idbops.showGrades(student_ID);}

    public String infoName(Long student_ID) { return idbops.infoName(student_ID); }

    public Integer infoGender(Long student_ID) { return idbops.infoGender(student_ID); }

    public String infoClass(Long student_ID){ return idbops.infoClass(student_ID); }

    public void test(){ idbops.test();}
}

