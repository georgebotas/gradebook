package com.georgebotas.gradebook.Model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "students")
public class Student {

    public Student() { }

    public Student(String studentName, Integer studentGender, String studentClass) {
        this.studentName = studentName;
        this.studentGender = studentGender;
        this.studentClass = studentClass;
    }

    private String studentName;
    private Integer studentGender;
    private String studentClass;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;

    //@OneToMany(mappedBy="student")
   //private ArrayList<Grades>grades = new ArrayList<Grades>();

    public String getStudentName() { return studentName; }

    public void setStudentName(String studentName) { this.studentName = studentName; }

    public Integer getStudentGender() { return studentGender; }

    public void setStudentGender(Integer studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    //public ArrayList<Grades> getGrades() { return grades; }

    //public void setGrades(ArrayList<Grades> grades) { this.grades = grades; }
}