package com.georgebotas.gradebook.Model;

import javax.persistence.*;

@Entity
@Table(name = "grades")
public class Grades {

    private Long grade_id;
    private Integer grade;
    private Integer subjectAverage;
    private Integer generalAverage;
    private Subject subject;

    public Grades() {
    }

    public Grades(Integer grade) {
        this.grade = grade;
    }

    public Grades(Integer grade, Subject subject) {
        this.grade = grade;
        this.subject = subject;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(Long grade_id) {
        this.grade_id = grade_id;
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

    @ManyToOne
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}

