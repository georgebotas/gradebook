package com.georgebotas.gradebook.UI;

import com.georgebotas.gradebook.Middleware.IGradeBook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu implements IMenu {

    @Autowired
    IGradeBook igradebook;

    private final Logger logger = LogManager.getLogger();
    private Long studentID;
    private Long gradeID;

    public Menu() {
    }

    private int userSelect;
    private static final Scanner SCAN = new Scanner(System.in);

    public void select() {

        Print.mainMenu();
        userSelect = SCAN.nextInt();
        SCAN.nextLine();

        switch (userSelect) {
            case 1:
                showGradeBook();
                break;
            case 2:
                create();
                break;
            case 3:
                editStudent();
                break;
            case 4:
                delete();
                break;
            case 5:
                addGrade();
                break;
            case 6:
                editGrade();
                break;
            case 7:
                removeGrade();
                break;
            case 8:
                sort();
                select();
                break;
            case 9:
                System.exit(0);
            default:
                Print.invalidNumber();
                select();
                break;
        }
    }

    private void editStudent() {
        Print.studentSelect();
        studentID = SCAN.nextLong();
        SCAN.nextLine();
        while (!igradebook.validateStudentID(studentID)) {
            Print.invalidID();
            studentID = SCAN.nextLong();
            SCAN.nextLine();
        }
        logger.info("THE STUDENT WITH THE ID '" + studentID + "' HAS BEEN SELECTED FOR EDITING.");
        edit(studentID);
        select();
    }

    private void edit(Long studentID) {
        String userString;
        int userInt;
        Print.editMenu();
        userSelect = SCAN.nextInt();
        SCAN.nextLine();
        switch (userSelect) {
            case 1:
                Print.editName();
                SCAN.nextLine();
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
                edit(studentID);
                break;
        }
    }

    private void sort() {
        Print.sortMenu();
        userSelect = SCAN.nextInt();
        switch (userSelect) {
            case 1:
                Print.sortName();
                logger.info("ATTEMPTING TO SORT AND LIST THE GRADEBOOK BY THE STUDENTS' NAMES.");
                igradebook.sortStudentsName();
                logger.info("SORTING SUCCESSFUL");
                break;
            case 2:
                Print.sortGender();
                logger.info("ATTEMPTING TO SORT AND LIST THE GRADEBOOK BY THE STUDENTS' GENDERS.");
                igradebook.sortStudentsGender();
                logger.info("SORTING SUCCESSFUL");
                break;
            case 3:
                Print.sortClass();
                logger.info("ATTEMPTING TO SORT AND LIST THE GRADEBOOK BY CLASSES.");
                igradebook.sortStudentsClass();
                logger.info("SORTING SUCCESSFUL");
                break;
            case 4:
                Print.sortAverage();
                logger.info("ATTEMPTING TO SORT AND LIST THE GRADEBOOK BY AVERAGE.");
                igradebook.sortStudentsAverage();
                logger.info("SORTING SUCCESSFUL.");
                break;
            default:
                Print.invalidNumber();
                sort();
                break;
        }
    }

    private void showGradeBook() {
        logger.info("ATTEMPTING TO LIST THE GRADEBOOK.");
        igradebook.showGradeBook();
        logger.info("LIST GRADEBOOK SUCCESSFUL.");
        select();
    }

    private void addGrade() {
        Print.studentSelect();
        studentID = SCAN.nextLong();
        while (!igradebook.validateStudentID(studentID)) {
            Print.invalidID();
            studentID = SCAN.nextLong();
            SCAN.nextLine();
        }
        Print.addGrade();
        Integer grade = SCAN.nextInt();
        SCAN.nextLine();
        while (grade <= 0 || grade > 10) {
            Print.invalidGrade();
            grade = SCAN.nextInt();
            SCAN.nextLine();
        }
        Print.enterSubject();
        String subject = SCAN.nextLine();
        while (!UserInputValidator.validateUserString(subject)) {
            Print.invalidString();
            subject = SCAN.nextLine();
        }
        logger.info("THE STUDENT WITH THE ID '" + studentID + "' FOR THE SUBJECT " + subject + " HAS BEEN SELECTED FOR GRADE ASSIGNMENT.");
        Print.addGrade();
        igradebook.addGrade(studentID, grade, subject);
        logger.info("THE STUDENT WITH THE ID '" + studentID + "' HAS BEEN ASSIGNED THE GRADE'" + grade + "' FOR THE SUBJECT " + subject + ".");
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
        igradebook.editGrade(studentID, gradeID, grade);
        logger.info("THE GRADE WITH THE ID '" + gradeID + "' ASSIGNED TO THE STUDENT WITH THE ID '" + studentID + "' HAS CHANGED TO" + grade + ".");
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
        Print.removeGrade();
        gradeID = SCAN.nextLong();
        SCAN.nextLine();
        while (!igradebook.validateGradeID(gradeID)) {
            Print.invalidID();
            gradeID = SCAN.nextLong();
            SCAN.nextLine();
        }
        logger.info("THE GRADE WITH THE ID '" + gradeID + " HAS BEEN SELECTED FOR REMOVAL.");

        igradebook.removeGrade(studentID, gradeID);
        logger.info("THE GRADE WITH THE ID '" + gradeID + "' ASSIGNED TO THE STUDENT WITH THE ID '" + studentID + "' HAS BEEN REMOVED.");
    }

    private void create() {
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

    private void delete() {
        Print.studentDelete();
        studentID = SCAN.nextLong();
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
}