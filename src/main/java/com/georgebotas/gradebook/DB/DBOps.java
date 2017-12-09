package com.georgebotas.gradebook.DB;

import com.georgebotas.gradebook.Model.Grades;
import com.georgebotas.gradebook.Model.Student;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;

@Component
public class DBOps implements IDBOps {

    public DBOps() { }

    public EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Gradebook");
    public EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
    public EntityTransaction transaction;

    @Override
    public boolean validateStudentID(Long student_ID) {
        Student student = manager.find(Student.class, student_ID);
        if (student== null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean validateGradeID(Long grades_ID) {
        Grades grades = manager.find(Grades.class, grades_ID);
        if (grades== null) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Student> studentList() {
        ArrayList<Student> students = null;
        transaction = manager.getTransaction();
        transaction.begin();
        students = (ArrayList<Student>) manager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        transaction.commit();
        return students;
    }

    @Override
    public void createStudent(String studentName, Integer studentGender, String studentClass) {
        transaction = manager.getTransaction();
        transaction.begin();
        Student student = new Student();
        student.setStudentName(studentName);
        student.setStudentGender(studentGender);
        student.setStudentClass(studentClass);
        manager.persist(student);
        transaction.commit();
    }

    @Override
    public void editName(Long student_ID, String studentName) {
        transaction = manager.getTransaction();
        transaction.begin();
        Student student = manager.find(Student.class, student_ID);
        student.setStudentName(studentName);
        manager.persist(student);
        transaction.commit();
    }

    @Override
    public void editGender(Long student_ID, Integer studentGender) {
        transaction = manager.getTransaction();
        transaction.begin();
        Student student = manager.find(Student.class, student_ID);
        student.setStudentGender(studentGender);
        manager.persist(student);
        transaction.commit();
    }

    @Override
    public void editClass(Long student_ID, String studentClass) {
        transaction = manager.getTransaction();
        transaction.begin();
        Student student = manager.find(Student.class, student_ID);
        student.setStudentClass(studentClass);
        manager.persist(student);
        transaction.commit();
    }

    @Override
    public void deleteStudent(Long student_ID) {
        transaction = manager.getTransaction();
        transaction.begin();
        Student student = manager.find(Student.class, student_ID);
        manager.remove(student);
        transaction.commit();
    }

    @Override
    public void addGrade(Long student_ID, Integer grade, String subject) {
        transaction = manager.getTransaction();
        transaction.begin();
        Student student = manager.find(Student.class, student_ID);
        Grades grades = new Grades();
        grades.setGrade(grade);
        grades.setSubject(subject);
        //student.getGrades().add(grades);
        manager.persist(grades);
        manager.persist(student);
        transaction.commit();
    }

    @Override
    public void editGrade(Long student_ID, Long grade_ID, Integer grade) {
        transaction = manager.getTransaction();
        transaction.begin();
        Student student = manager.find(Student.class, student_ID);
        Grades grades = manager.find(Grades.class, grade_ID);
        grades.setGrade(grade);
        //student.getGrades().add(grades);
        manager.persist(grades);
        manager.persist(student);
        transaction.commit();
    }

    @Override
    public void removeGrade(Long student_ID, Long grade_ID) {
        transaction = manager.getTransaction();
        transaction.begin();
        Student student = manager.find(Student.class, student_ID);
        Grades grades = manager.find(Grades.class, grade_ID);
        manager.remove(grades);
        //student.getGrades().remove(grades);
        manager.persist(grades);
        manager.persist(student);
        transaction.commit();
    }


    //ArrayList<Grades>gradeList = (ArrayList<Grades>) manager.createQuery("SELECT g FROM Grades g", Grades.class).getResultList();
     //   grades.setSubjectAverage(calculateAverage(gradeList));

    private Integer calculateAverage(ArrayList<Grades> grades) {
        Integer sum = 0;
        if(!grades.isEmpty()) {
            for (Grades grade : grades) {
                sum += grade.getGrade();
            }
            return sum.intValue()/grades.size();
        }
        return sum;
    }
}


