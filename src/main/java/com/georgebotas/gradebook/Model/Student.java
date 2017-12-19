package com.georgebotas.gradebook.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    private Long student_id;
    private String studentName;
    private Integer studentGender;
    private String studentClass;
    private List<Subject> subjects= new ArrayList<Subject>();

    public Student() { }

    public Student(String studentName, Integer studentGender, String studentClass) {
        this.studentName = studentName;
        this.studentGender = studentGender;
        this.studentClass = studentClass;
    }

    public Student(String studentName, Integer studentGender, String studentClass, List<Subject> subjects) {
        this.studentName = studentName;
        this.studentGender = studentGender;
        this.studentClass = studentClass;
        this.subjects = subjects;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getStudent_id() { return student_id; }

    public void setStudent_id(Long student_id) { this.student_id = student_id; }

    public String getStudentName() { return studentName; }

    public void setStudentName(String studentName) { this.studentName = studentName; }

    public Integer getStudentGender() { return studentGender; }

    public void setStudentGender(Integer studentGender) { this.studentGender = studentGender; }

    public String getStudentClass() { return studentClass; }

    public void setStudentClass(String studentClass) { this.studentClass = studentClass; }

    @OneToMany(mappedBy="student")
    public List<Subject> getSubjects() { return subjects; }

    public void setSubjects(List<Subject> subjects) { this.subjects = subjects; }
}