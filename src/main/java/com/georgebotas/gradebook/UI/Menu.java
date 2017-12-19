package com.georgebotas.gradebook.UI;

import com.georgebotas.gradebook.DB.DBOps;
import com.georgebotas.gradebook.Middleware.IGradeBook;
import com.georgebotas.gradebook.Model.Grades;
import com.georgebotas.gradebook.Model.Student;
import com.georgebotas.gradebook.Model.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Menu implements IMenu {

    @Autowired
    IGradeBook igradebook;

    private final Logger logger = LogManager.getLogger();
    private Long studentID;
    private Long subjectID;
    private Long gradeID;
    private Integer index;

    public Menu() {
    }

    private int userSelect;
    private static final Scanner SCAN = new Scanner(System.in);

    public void select() {

        Print.mainMenu();
        userSelect = SCAN.nextInt();
        SCAN.nextLine();
        switch (userSelect) {
            case 0:
                igradebook.test();
                select();
                break;
            case 1:
                showGradeBook();
                break;
            case 2:
                createStudent();
                break;
            case 3:
                editStudentSelection();
                break;
            case 4:
                deleteStudent();
                break;
            case 5:
                gradesMenu();
                break;
            case 6:
                sort();
                select();
                break;
            case 7:
                System.exit(0);
                break;
            default:
                Print.invalidNumber();
                select();
                break;
        }
    }

    private void sort() {
        Print.sortMenu();
        userSelect = SCAN.nextInt();
        SCAN.nextLine();
        switch (userSelect) {
            case 1:
                Print.sortName();
                logger.info("ATTEMPTING TO SORT AND LIST THE GRADEBOOK BY THE STUDENTS' NAMES.");
                sortStudentsName();
                logger.info("SORTING SUCCESSFUL");
                break;
            case 2:
                Print.sortGender();
                logger.info("ATTEMPTING TO SORT AND LIST THE GRADEBOOK BY THE STUDENTS' GENDERS.");
                sortStudentsGender();
                logger.info("SORTING SUCCESSFUL");
                break;
            case 3:
                Print.sortClass();
                logger.info("ATTEMPTING TO SORT AND LIST THE GRADEBOOK BY CLASSES.");
                sortStudentsClass();
                logger.info("SORTING SUCCESSFUL");
                break;
            case 4:
                Print.sortAverage();
                logger.info("ATTEMPTING TO SORT AND LIST THE GRADEBOOK BY AVERAGE.");
                sortStudentsAverage();
                logger.info("SORTING SUCCESSFUL.");
                break;
            default:
                Print.invalidNumber();
                sort();
                break;
        }
    }

    private void gradesMenu(){
        Print.gradesMenu();
        userSelect = SCAN.nextInt();
        SCAN.nextLine();
        switch (userSelect){
            case 1:
                showGrades();
                break;
            case 2:
                addGrade();
                break;
            case 3:
                editGrade();
                break;
            case 4:
                removeGrade();
                break;
            case 5:
                showSubjects();
                break;
            case 6:
                createSubject();
                break;
            case 7:
                editSubject();
                break;
            case 8:
                removeSubject();
                break;
        }
    }

    private void showGradeBook() {
        logger.info("ATTEMPTING TO LIST THE GRADEBOOK.");
        ArrayList<Student> students = igradebook.showGradeBook();
        int count = 0;
        System.out.printf("%-10s %-21s %-21s %-21s %-21s \n", "STUDENT ID:", "NAME:", "GENDER:", "CLASS:", "AVERAGE:");
        for (Student student : students) {
            System.out.printf("%d. %-12s %-21s %-21s %-21s %-21s \n", count + 1, student.getStudent_id(), student.getStudentName(), student.getStudentGender() == 0? "MALE" : "FEMALE" ,
                    student.getStudentClass(), "CARNAT");//student.getGrades());
            count++;
        }
        System.out.println();
        logger.info("LIST GRADEBOOK SUCCESSFUL.");
        select();
    }

    private void addGrade() {
        Print.studentSelect();
        studentID = SCAN.nextLong();
        SCAN.nextLine();
        while (!igradebook.validateStudentID(studentID)) {
            Print.invalidID();
            studentID = SCAN.nextLong();
            SCAN.nextLine();
        }
        Print.subjectSelect();
        index = SCAN.nextInt();
        SCAN.nextLine();
        /*while (!igradebook.validateSubjectID(subjectID)) {
            Print.invalidID();
            studentID = SCAN.nextLong();
            SCAN.nextLine();
        }*/
        logger.info("THE STUDENT WITH THE ID '" + studentID + "' FOR THE SUBJECT WITH THE ID '" + subjectID + "' HAS BEEN SELECTED FOR GRADE ASSIGNMENT.");
        Print.addGrade();
        Integer grade = SCAN.nextInt();
        SCAN.nextLine();
        while (grade <= 0 || grade > 10) {
            Print.invalidGrade();
            grade = SCAN.nextInt();
            SCAN.nextLine();
        }
        igradebook.addGrade(studentID, index, grade);
        logger.info("THE STUDENT WITH THE ID '" + studentID + "' HAS BEEN ASSIGNED THE GRADE'" + grade + "' FOR THE SUBJECT WITH THE ID '" + subjectID + "'.");
        select();
    }

    private void editGrade() {
        Print.studentSelect();
        studentID = SCAN.nextLong();
        SCAN.nextLine();
        while (!igradebook.validateStudentID(studentID)) {
            Print.invalidID();
            studentID = SCAN.nextLong();
            SCAN.nextLine();
        }
        logger.info("THE STUDENT WITH THE ID '" + studentID + " HAS BEEN SELECTED.");
        Print.subjectSelect();
        subjectID = SCAN.nextLong();
        SCAN.nextLine();
        while (!igradebook.validateSubjectID(subjectID)) {
            Print.invalidID();
            subjectID = SCAN.nextLong();
            SCAN.nextLine();
        }
        logger.info("THE SUBJECT WITH THE ID '" + subjectID + " HAS BEEN SELECTED.");
        Print.gradeSelect();
        gradeID = SCAN.nextLong();
        SCAN.nextLine();
        while (!igradebook.validateGradeID(gradeID)) {
            Print.invalidID();
            gradeID = SCAN.nextLong();
            SCAN.nextLine();
        }
        logger.info("THE GRADE WITH THE ID '" + gradeID + " HAS BEEN SELECTED.");
        Print.addGrade();
        Integer grade = SCAN.nextInt();
        SCAN.nextLine();
        while (grade <= 0 || grade > 10) {
            Print.invalidGrade();
            grade = SCAN.nextInt();
            SCAN.nextLine();
        }
        igradebook.editGrade(studentID, subjectID, gradeID, grade);
        logger.info("THE GRADE WITH THE ID '" + gradeID + "' ASSIGNED TO THE STUDENT WITH THE ID '" + studentID + "' HAS CHANGED TO '" + grade + "'.");
        select();
    }

    private void removeGrade() {
        Print.studentSelect();
        studentID = SCAN.nextLong();
        SCAN.nextLine();
        while (!igradebook.validateStudentID(studentID)) {
            Print.invalidID();
            studentID = SCAN.nextLong();
            SCAN.nextLine();
        }
        logger.info("THE STUDENT WITH THE ID '" + studentID + " HAS BEEN SELECTED.");
        Print.subjectSelect();
        subjectID = SCAN.nextLong();
        SCAN.nextLine();
        while (!igradebook.validateSubjectID(subjectID)) {
            Print.invalidID();
            subjectID = SCAN.nextLong();
            SCAN.nextLine();
        }
        logger.info("THE SUBJECT WITH THE ID '" + subjectID + " HAS BEEN SELECTED.");
        Print.removeGrade();
        gradeID = SCAN.nextLong();
        SCAN.nextLine();
        while (!igradebook.validateGradeID(gradeID)) {
            Print.invalidID();
            gradeID = SCAN.nextLong();
            SCAN.nextLine();
        }
        logger.info("THE GRADE WITH THE ID '" + gradeID + " HAS BEEN SELECTED FOR REMOVAL.");
        igradebook.removeGrade(studentID, subjectID, gradeID);
        logger.info("THE GRADE WITH THE ID '" + gradeID + "' ASSIGNED TO THE STUDENT WITH THE ID '" + studentID + "' HAS BEEN REMOVED.");
        select();
    }

    private void showGrades(){
        Print.studentSelect();
        studentID = SCAN.nextLong();
        SCAN.nextLine();
        while (!igradebook.validateStudentID(studentID)) {
            Print.invalidID();
            studentID = SCAN.nextLong();
            SCAN.nextLine();
        }
        logger.info("ATTEMPTING TO LIST THE GRADES FOR THE STUDENT WITH THE ID '" + studentID + "'.");

        /* ArrayList<Subject> subjects = igradebook.subjectList();
        int count = 0;
        System.out.printf("%-10s %-21s %-21s %-21s %-21s %-21s %-21s %-21s \n", "STUDENT ID:", "NAME:", "CLASS:", "SUBJECT:", "GR. ID.:", "GRADE:", "AVERAGE", "GEN. AVR.:");
        System.out.printf("%d. %-12s %-21s %-21s ", count + 1, studentID, igradebook.infoName(studentID),igradebook.infoClass(studentID));

        for (Subject subject: subjects) {
            System.out.print(subject.getSubjectName());
            //ArrayList<Integer>gradesAverages = new ArrayList<Integer>();
            //ArrayList<Integer>gradesGeneralAverages = new ArrayList<Integer>();
            for (Grades grade : grades) {
                gradesAverages.add(grade.getSubjectAverage());
                gradesGeneralAverages.add(grade.getGeneralAverage());
                System.out.printf("%-21s %-21s", "CARNAT", "AFUMAT" grade.getGrade_id(), grade.getGrade());
                System.out.print("PRINT MEEEEEEEEEEEEEE PLEEEZZZ! 1ST FOR!");
                count++;
            }
            //System.out.printf("%-21s %-21s", gradesAverages.get(gradesAverages.size()-1), gradesGeneralAverages.get(gradesAverages.size()-1));
            System.out.print("PRINT MEEEEEEEEEEEEEE PLEEEZZZ! 2ND FOR");
        }
        System.out.print("PRINT MEEEEEEEEEEEEEE PLEEEZZZ! I'M OUTSIDE!");
        System.out.println();
        logger.info("PRINTING GRADES SUCCESSFUL.");
        select();*/
    }

    private void createStudent() {
        String studentName;
        Integer studentGender;
        String studentClass;
        logger.info("ATTEMPTING STUDENT CREATION. USER INPUT EXPECTED.");
        Print.createName();
        studentName = SCAN.nextLine();
        while (!UserInputValidator.validateUserString(studentName)) {
            Print.invalidString();
            studentName = SCAN.nextLine();
        }
        Print.selectGender();
        studentGender = SCAN.nextInt();
        SCAN.nextLine();
        while (studentGender != 0 && studentGender != 1) {
            Print.invalidNumber();
            studentGender = SCAN.nextInt();
            SCAN.nextLine();
        }
        Print.createClass();
        studentClass = SCAN.nextLine();
        while (!UserInputValidator.validateUserString(studentClass)) {
            Print.invalidString();
            studentClass = SCAN.nextLine();
        }
        igradebook.createStudent(studentName, studentGender, studentClass);
        logger.info("STUDENT WITH THE NAME '" + studentName + "', GENDER '" + studentGender + "' AND CLASS '" + studentClass + "' HAS BEEN SUCCESSFULLY CREATED.");
        select();
    }

    private void editStudentSelection() {
        Print.studentSelect();
        studentID = SCAN.nextLong();
        SCAN.nextLine();
        while (!igradebook.validateStudentID(studentID)) {
            Print.invalidID();
            studentID = SCAN.nextLong();
            SCAN.nextLine();
        }
        logger.info("THE STUDENT WITH THE ID '" + studentID + "' HAS BEEN SELECTED FOR EDITING.");
        editStudent(studentID);
        select();
    }

    private void editStudent(Long studentID) {
        String userString;
        int userInt;
        Print.editMenu();
        userSelect = SCAN.nextInt();
        SCAN.nextLine();
        switch (userSelect) {
            case 1:
                Print.editName();
                userString = SCAN.nextLine();
                while (!UserInputValidator.validateUserString(userString)) {
                    Print.invalidString();
                    userString = SCAN.nextLine();
                }
                igradebook.editName(studentID, userString);
                logger.info("THE STUDENT'S NAME WITH THE ID '" + studentID + "' HAS CHANGED TO '" + userString + "'.");
                break;
            case 2:
                Print.editGender();
                userInt = SCAN.nextInt();
                SCAN.nextLine();
                while (userInt != 0 && userInt != 1) {
                    Print.invalidNumber();
                    userInt = SCAN.nextInt();
                    SCAN.nextLine();
                }
                igradebook.editGender(studentID, userInt);
                logger.info("THE STUDENT'S GENDER WITH THE ID '" + studentID + "' HAS CHANGED TO '" + (userInt == 0 ? "MALE" : "FEMALE") + "'.");
                break;
            case 3:
                Print.editClass();
                userString = SCAN.nextLine();
                while (!UserInputValidator.validateUserString(userString)) {
                    Print.invalidString();
                    userString = SCAN.nextLine();
                }
                igradebook.editClass(studentID, userString);
                logger.info("THE STUDENT'S CLASS WITH THE ID '" + studentID + "HAS CHANGED TO '" + userString + "'.");
                break;
            default:
                Print.invalidNumber();
                editStudent(studentID);
                break;
        }
    }

    private void deleteStudent() {
        Print.studentDelete();
        studentID = SCAN.nextLong();
        SCAN.nextLine();
        while (!igradebook.validateStudentID(studentID)) {
            Print.invalidID();
            studentID = SCAN.nextLong();
            SCAN.nextLine();
        }
        logger.info("THE STUDENT WITH THE ID '" + studentID + "' HAS BEEN SELECTED FOR REMOVAL.");
        igradebook.deleteStudent(studentID);
        logger.info("THE STUDENT WITH THE ID '" + studentID + "' HAS BEEN SUCCESSFULLY REMOVED.");
        select();
    }

    private void showSubjects(){
        logger.info("ATTEMPTING TO LIST ALL SUBJECTS.");
        ArrayList<Subject> subjects = igradebook.subjectList();
        int count = 0;
        System.out.printf("%-10s %-21s \n", "SUBJECT ID:", "SUBJECT NAME:");
        for (Subject subject : subjects) {
            System.out.printf("%d. %-12s %-21s \n", count + 1, subject.getSubject_id(), subject.getSubjectName());
            count++;
        }
        System.out.println();
        logger.info("LIST SUBJECTS SUCCESSFUL.");
        select();
    }

    private void createSubject(){
        logger.info("ATTEMPTING SUBJECT CREATION. USER INPUT EXPECTED.");
        Print.subjectCreate();
        String subjectName = SCAN.nextLine();
        while (!UserInputValidator.validateUserString(subjectName)) {
            Print.invalidString();
            subjectName = SCAN.nextLine();
        }
        igradebook.createSubject(subjectName);
        logger.info("SUBJECT WITH THE NAME '" + subjectName + "' HAS BEEN SUCCESSFULLY CREATED.");
        select();
    }

    private void editSubject(){
        Print.subjectSelect();
        Long subjectID = SCAN.nextLong();
        while(!igradebook.validateSubjectID(subjectID)){
            Print.invalidID();
            subjectID = SCAN.nextLong();
        }
        logger.info("THE SUBJECT WITH THE ID '" + subjectID + "' HAS BEEN SELECTED FOR EDITING.");
        Print.subjectEdit();
        String subjectName = SCAN.nextLine();
        while (!UserInputValidator.validateUserString(subjectName)) {
            Print.invalidString();
            subjectName = SCAN.nextLine();
        }
        igradebook.editName(subjectID, subjectName);
        logger.info("THE SUBJECT'S NAME WITH THE ID '" + subjectID + "' HAS CHANGED TO '" + subjectName + "'.");
    }

    private void removeSubject(){
        Print.subjectDelete();
        Long subjectID = SCAN.nextLong();
        while(!igradebook.validateSubjectID(subjectID)){
            Print.invalidID();
            subjectID = SCAN.nextLong();
        }
        logger.info("THE SUBJECT WITH THE ID '" + subjectID + "' HAS BEEN SELECTED FOR REMOVAL.");
        igradebook.deleteSubject(subjectID);
        logger.info("THE SUBJECT WITH THE ID '" + subjectID + "' HAS BEEN SUCCESSFULLY DELETED.");
    }

    private void sortStudentsName() {
        ArrayList<Student> students = igradebook.showGradeBook();
        int count = 0;
        Collections.sort(students, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s1.getStudentName().compareTo(s2.getStudentName());
            }
        });
        System.out.printf("%-10s %-21s %-21s %-21s %-21s \n", "STUDENT ID:", "NAME:", "GENDER:", "CLASS:", "GRADES:");
        for (Student student : students) {
            System.out.printf("%d. %-12s %-21s %-21s %-21s %s \n", count + 1, student.getStudent_id(), student.getStudentName(), student.getStudentGender() == 0? "MALE" : "FEMALE" ,
                    student.getStudentClass(), student.getStudentGender());
            count++;
        }
        System.out.println();
    }

    private void sortStudentsGender() {
        ArrayList<Student> students = igradebook.showGradeBook();
        int count = 0;
        Collections.sort(students, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s1.getStudentGender() - s2.getStudentGender();
            }
        });
        System.out.printf("%-10s %-21s %-21s %-21s %-21s \n", "STUDENT ID:", "NAME:", "GENDER:", "CLASS:", "GRADES:");
        for (Student student : students) {
            System.out.printf("%d. %-12s %-21s %-21s %-21s %s \n", count + 1, student.getStudent_id(), student.getStudentName(), student.getStudentGender() == 0? "MALE" : "FEMALE" ,
                    student.getStudentClass(), "CARNAT");//student.getGrades());
            count++;
        }
        System.out.println();

    }

    private void sortStudentsClass() {
        ArrayList<Student> students = igradebook.showGradeBook();
        int count = 0;
        Collections.sort(students, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s1.getStudentClass().compareTo(s2.getStudentClass());
            }
        });
        System.out.printf("%-10s %-21s %-21s %-21s %-21s \n", "STUDENT ID:", "NAME:", "GENDER:", "CLASS:", "GRADES:");
        for (Student student : students) {
            System.out.printf("%d. %-12s %-21s %-21s %-21s %s \n", count + 1, student.getStudent_id(), student.getStudentName(), student.getStudentGender() == 0? "MALE" : "FEMALE" ,
                    student.getStudentClass(), "CARNAT");//student.getGrades());
            count++;
        }
        System.out.println();
    }

    private void sortStudentsAverage() {
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