package com.georgebotas.gradebook.Middleware;

import com.georgebotas.gradebook.DB.IDBOps;
import com.georgebotas.gradebook.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Component
public class GradeBook implements IGradeBook {

    @Autowired
    IDBOps idbops;

    public GradeBook() { }

    @Override
    public boolean validateStudentID(Long student_ID) { return idbops.validateStudentID(student_ID); }

    @Override
    public boolean validateGradeID(Long grade_ID) { return idbops.validateGradeID(grade_ID); }

    @Override
    public void showGradeBook(){
        ArrayList<Student> students = idbops.studentList();
        int count = 0;
        System.out.printf("%-10s %-21s %-21s %-21s %-21s \n", "STUDENT ID:", "NAME:", "GENDER:", "CLASS:", "GRADES:");
        for (Student student : students) {
            System.out.printf("%d. %-12s %-21s %-21s %-21s %s \n", count + 1, student.getStudent_id(), student.getStudentName(), student.getStudentGender(),
                    student.getStudentClass()); //student.getGrades())
            count++;
        }
        System.out.println();
    }

    @Override
    public void createStudent(String studentName, Integer studentGender, String studentClass) { idbops.createStudent(studentName, studentGender, studentClass); }

    @Override
    public void editName(Long student_ID, String studentName) { idbops.editName(student_ID, studentName); }

    @Override
    public void editGender(Long student_ID, Integer studentGender) { idbops.editGender(student_ID, studentGender); }

    @Override
    public void editClass(Long student_ID, String studentClass) { idbops.editClass(student_ID, studentClass); }

    @Override
    public void deleteStudent(Long student_ID) { idbops.deleteStudent(student_ID); }

    @Override
    public void addGrade(Long student_ID, Integer grade, String subject) { idbops.addGrade(student_ID, grade, subject);}

    @Override
    public void editGrade(Long student_ID, Long grade_ID, Integer grade) { idbops.editGrade(student_ID, grade_ID ,grade);}

    @Override
    public void removeGrade(Long student_ID, Long grade_ID) { idbops.removeGrade(student_ID, grade_ID);}

    @Override
    public void sortStudentsName() {

        ArrayList<Student> students = idbops.studentList();
        int count = 0;
        Collections.sort(students, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s1.getStudentName().compareTo(s2.getStudentName());
            }
        });
        System.out.printf("%-10s %-21s %-21s %-21s %-21s \n", "STUDENT ID:", "NAME:", "GENDER:", "CLASS:", "GRADES:");
        for (Student student : students) {
            System.out.printf("%d. %-12s %-21s %-21s %-21s %s \n", count + 1, student.getStudent_id(), student.getStudentName(), student.getStudentGender(),
                    student.getStudentClass()); //student.getGrades());
            count++;
        }
        System.out.println();
    }

    @Override
    public void sortStudentsGender() {

        ArrayList<Student> students = idbops.studentList();
        int count = 0;
        Collections.sort(students, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s1.getStudentGender() - s2.getStudentGender();
            }
        });
        System.out.printf("%-10s %-21s %-21s %-21s %-21s \n", "STUDENT ID:", "NAME:", "GENDER:", "CLASS:", "GRADES:");
        for (Student student : students) {
            System.out.printf("%d. %-12s %-21s %-21s %-21s %s \n", count + 1, student.getStudent_id(), student.getStudentName(), student.getStudentGender(),
                    student.getStudentClass()); //student.getGrades());
            count++;
        }
        System.out.println();
    }

    @Override
    public void sortStudentsClass() {

        ArrayList<Student> students = idbops.studentList();
        int count = 0;
        Collections.sort(students, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s1.getStudentClass().compareTo(s2.getStudentClass());
            }
        });
        System.out.printf("%-10s %-21s %-21s %-21s %-21s \n", "STUDENT ID:", "NAME:", "GENDER:", "CLASS:", "GRADES:");
        for (Student student : students) {
            System.out.printf("%d. %-12s %-21s %-21s %-21s %s \n", count + 1, student.getStudent_id(), student.getStudentName(), student.getStudentGender(),
                    student.getStudentClass()); //student.getGrades());
            count++;
        }
        System.out.println();
    }


    @Override
    public void sortStudentsAverage() {
        System.out.println("Work in progress...");
/*
        ArrayList<Student> students = idbops.studentList();
        int count = 0;
        Collections.sort(students, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s2.getGrades(). - s1.getGrades();
            }
        });
        System.out.printf("%-10s %-21s %-21s %-21s %-21s \n", "STUDENT ID:", "NAME:", "GENDER:", "CLASS:", "GRADES:");
        for (Student student : students) {
            System.out.printf("%d. %-12s %-21s %-21s %-21s %s \n", count + 1, student.getStudent_id(), student.getStudentName(), student.getStudentGender(),
                    student.getStudentClass(), student.getGrades());
            count++;
        }
        System.out.println();
        */
    }

}

