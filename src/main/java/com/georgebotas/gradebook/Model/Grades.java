package com.georgebotas.gradebook.Model;

import javax.persistence.*;

@Entity
@Table(name = "grades")
public class Grades {

    private String subject;
    private Integer grade;
    private Integer subjectAverage;
    private Integer generalAverage;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long grade_id;

    @ManyToOne
    //@JoinColumn(name = "student_id")
    private Student student;

    public Grades() {
    }

    public Grades(String subject, Integer grade) {
        this.subject = subject;
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getSubjectAverage() {
        return subjectAverage;
    }

    public void setSubjectAverage(Integer subjectAverage) {
        this.subjectAverage = subjectAverage;
    }

    public Integer getGeneralAverage() {
        return generalAverage;
    }

    public void setGeneralAverage(Integer generalAverage) {
        this.generalAverage = generalAverage;
    }

    public Long getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(Long grade_id) {
        this.grade_id = grade_id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

