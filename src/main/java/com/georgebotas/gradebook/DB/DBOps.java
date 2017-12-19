package com.georgebotas.gradebook.DB;

import com.georgebotas.gradebook.Model.Grades;
import com.georgebotas.gradebook.Model.Student;
import com.georgebotas.gradebook.Model.Subject;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class DBOps implements IDBOps {

    public DBOps() { }

    public EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Gradebook");
    public EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
    public EntityTransaction transaction;

    public boolean validateStudentID(Long student_ID) {
        Student student = manager.find(Student.class, student_ID);
        if (student== null) {
            return false;
        }
        return true;
    }

    public boolean validateSubjectID(Long subject_ID) {
        Subject subject = manager.find(Subject.class, subject_ID);
        if (subject== null) {
            return false;
        }
        return true;
    }

    public boolean validateGradeID(Long grades_ID) {
        Grades grades = manager.find(Grades.class, grades_ID);
        if (grades== null) {
            return false;
        }
        return true;
    }

    public ArrayList<Student> studentList() {
        ArrayList<Student> students = null;
        transaction = manager.getTransaction();
        transaction.begin();
        students = (ArrayList<Student>) manager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        transaction.commit();
        return students;
    }

    public void createStudent(String studentName, Integer studentGender, String studentClass) {
        transaction = manager.getTransaction();
        transaction.begin();
        Student student = new Student();
        student.setStudentName(studentName);
        student.setStudentGender(studentGender);
        student.setStudentClass(studentClass);
        List<Subject>subjects = (ArrayList<Subject>) manager.createQuery("SELECT s FROM Subject s", Subject.class).getResultList();
        student.setSubjects(subjects);
        manager.persist(student);
        transaction.commit();
    }

    public void editName(Long student_ID, String studentName) {
        transaction = manager.getTransaction();
        transaction.begin();
        Student student = manager.find(Student.class, student_ID);
        student.setStudentName(studentName);
        manager.persist(student);
        transaction.commit();
    }

    public void editGender(Long student_ID, Integer studentGender) {
        transaction = manager.getTransaction();
        transaction.begin();
        Student student = manager.find(Student.class, student_ID);
        student.setStudentGender(studentGender);
        manager.persist(student);
        transaction.commit();
    }

    public void editClass(Long student_ID, String studentClass) {
        transaction = manager.getTransaction();
        transaction.begin();
        Student student = manager.find(Student.class, student_ID);
        student.setStudentClass(studentClass);
        manager.persist(student);
        transaction.commit();
    }

    public void deleteStudent(Long student_ID) {
        transaction = manager.getTransaction();
        transaction.begin();
        Student student = manager.find(Student.class, student_ID);
        manager.remove(student);
        transaction.commit();
    }

    public void createSubject(String subjectName) {
        transaction = manager.getTransaction();
        transaction.begin();
        Subject subject = new Subject();
        subject.setSubjectName(subjectName);
        List<Student> students = (ArrayList<Student>) manager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        for (Student student : students){
            student.getSubjects().add(subject);
        }
        manager.persist(subject);
        transaction.commit();
    }

    public void editSubject(Long subject_ID, String subjectName) {
        transaction = manager.getTransaction();
        transaction.begin();
        Subject subject = manager.find(Subject.class, subject_ID);
        subject.setSubjectName(subjectName);
        manager.persist(subject);
        transaction.commit();
    }

    public void deleteSubject(Long subject_ID) {
        transaction = manager.getTransaction();
        transaction.begin();
        Subject subject = manager.find(Subject.class, subject_ID);
        manager.remove(subject);
        transaction.commit();
    }

    public void addGrade(Long student_ID, Integer subject_Index, Integer grade) {
        transaction = manager.getTransaction();
        transaction.begin();
        Student student = manager.find(Student.class, student_ID);
        List<Subject>subjects = student.getSubjects();
        Subject subject = subjects.get(subject_Index);
        subject.getGrades().add(new Grades(grade, subject));
        //grades.setSubjectAverage(calculateAverage(gradeList));
        //grades.setGeneralAverage(calculateAverage(gradeList));
        //manager.persist(grades);
        manager.persist(subject);
        manager.persist(student);
        transaction.commit();
    }

    public void editGrade(Long student_ID, Long subject_ID, Long grade_ID, Integer grade) {
        transaction = manager.getTransaction();
        transaction.begin();
        Student student = manager.find(Student.class, student_ID);
        Subject subject = manager.find(Subject.class, subject_ID);
        Grades grades = manager.find(Grades.class, grade_ID);
        grades.setGrade(grade);
        ArrayList<Grades>gradeList = (ArrayList<Grades>) manager.createQuery("SELECT g FROM Grades g", Grades.class).getResultList();
        grades.setSubjectAverage(calculateAverage(gradeList));
        grades.setGeneralAverage(calculateAverage(gradeList));
        manager.persist(grades);
        manager.persist(subject);
        manager.persist(student);
        transaction.commit();
    }

    public void removeGrade(Long student_ID, Long subject_ID, Long grade_ID) {
        transaction = manager.getTransaction();
        transaction.begin();
        Student student = manager.find(Student.class, student_ID);
        Subject subject = manager.find(Subject.class, subject_ID);
        Grades grades = manager.find(Grades.class, grade_ID);
        manager.remove(grades);
        ArrayList<Grades>gradeList = (ArrayList<Grades>) manager.createQuery("SELECT g FROM Grades g", Grades.class).getResultList();
        grades.setSubjectAverage(calculateAverage(gradeList));
        grades.setGeneralAverage(calculateAverage(gradeList));
        manager.persist(grades);
        manager.persist(subject);
        manager.persist(student);
        transaction.commit();
    }


    public ArrayList<Subject> subjectList() {
        ArrayList<Subject> subjects = null;
        transaction = manager.getTransaction();
        transaction.begin();
        subjects = (ArrayList<Subject>) manager.createQuery("SELECT s FROM Subject s", Subject.class).getResultList();
        transaction.commit();
        return subjects;
    }

    public List<Grades>showGrades(Long student_ID){
        List<Subject> subjects = new ArrayList<Subject>();
        List<Grades> gradeList = new ArrayList<Grades>();

        transaction = manager.getTransaction();
        transaction.begin();
        Student student = manager.find(Student.class, student_ID);
        subjects = student.getSubjects();
        for (Subject subject:subjects) {
            for (Grades grade:subject.getGrades()){
                gradeList.add(grade);
            }
        }
        transaction.commit();
        return gradeList;
    }

    public String infoName(Long student_ID) {
        Student student = manager.find(Student.class, student_ID);
        return student.getStudentName();
    }

    public Integer infoGender(Long student_ID) {
        Student student = manager.find(Student.class, student_ID);
        return student.getStudentGender();
    }

    public String infoClass(Long student_ID) {
        Student student = manager.find(Student.class, student_ID);
        return student.getStudentClass();
    }

    //ArrayList<Grades>gradeList = (ArrayList<Grades>) manager.createQuery("SELECT g FROM Grades g", Grades.class).getResultList();
    //grades.setSubjectAverage(calculateAverage(gradeList));

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

    private Integer gradesSubjectAverage(Long student_ID, Long subject_ID){
        transaction = manager.getTransaction();
        transaction.begin();
        Student student = manager.find(Student.class, student_ID);
        Subject subject = manager.find(Subject.class, subject_ID);
        ArrayList<Grades>gradeList = (ArrayList<Grades>) manager.createQuery("SELECT g FROM Grades g", Grades.class).getResultList();
        Integer subjAvr = calculateAverage(gradeList);
        transaction.commit();
        return subjAvr;
    }

    private Integer gradesGeneralAverage(Long student_ID, Long subject_ID){
        transaction = manager.getTransaction();
        transaction.begin();
        Student student = manager.find(Student.class, student_ID);
        ArrayList<Grades>gradeList = (ArrayList<Grades>) manager.createQuery("SELECT g FROM Grades g", Grades.class).getResultList();
        Integer subjAvr = calculateAverage(gradeList);
        transaction.commit();
        return subjAvr;
    }

    public void test(){
        transaction = manager.getTransaction();
        transaction.begin();
        Scanner Scan = new Scanner(System.in);
        System.out.println("ENTER STUDENT ID");
        Long student_ID = Scan.nextLong();
        Scan.nextLine();
        Student student = manager.find(Student.class, student_ID);
        List<Subject>subjects = student.getSubjects();
        //List<Grades>grades = new ArrayList<Grades>();
        for (Subject subject : subjects){
            System.out.printf("%-21s %-21s \n", "SUB. ID:", "SUB. NAME:");
            System.out.printf("%-21s %-21s \n", subject.getSubject_id(), subject.getSubjectName());
            System.out.printf("%-21s %-21s \n", "GR. ID:", "GRADE:");
            for (Grades grade : subject.getGrades()) {
                System.out.printf("%-21s %-21s\n", grade.getGrade_id(), grade.getGrade());
            }

        }
        //transaction.commit();
    }
}


