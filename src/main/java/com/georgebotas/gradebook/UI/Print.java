package com.georgebotas.gradebook.UI;

public class Print {

    public static void mainMenu() {
        String mainMenu = "THE GRADEBOOK: PLEASE CHOOSE AN OPTION BY ENTERING THE CORRESPONDING NUMBER:\n\n" +
                "1. SHOW GRADEBOOK\n2. CREATE NEW STUDENT\n3. EDIT A STUDENT\n4. REMOVE A STUDENT\n5. GRADES AND SUBJECT MENU" +
                "\n6. SORT GRADEBOOK\n7. EXIT";
        System.out.println(mainMenu);
    }

    public static void editMenu() {
        String editMenu = "CHOOSE AN OPTION BY ENTERING THE CORRESPONDING NUMBER:\n1. EDIT STUDENT'S NAME\n2. EDIT STUDENT'S GENDER\n3. EDIT STUDENT'S CLASS";
        System.out.println(editMenu);
    }

    public static void gradesMenu() {
        String editMenu = "CHOOSE AN OPTION BY ENTERING THE CORRESPONDING NUMBER:\n\n1. SHOW ALL GRADES AND AVERAGES FOR A SPECIFIC STUDENT\n2. ADD A NEW GRADE\n3. EDIT GRADE\n" +
                "4. REMOVE GRADE\n5. SHOW AVAILABLE SUBJECTS\n6. CREATE A SUBJECT\n7. EDIT A SUBJECT\n8. REMOVE A SUBJECT";
        System.out.println(editMenu);
    }

    public static void sortMenu() {
        String sortMenu = "CHOOSE THE TYPE OF SORTING:\n 1. SORT BY STUDENT NAME\n 2. SORT BY STUDENT GENDER\n 3. SORT BY CLASS\n 4. SORT BY AVERAGES";
        System.out.println(sortMenu);
    }

    public static void createName() {
        String createName = "ENTER THE STUDENT'S NAME:";
        System.out.println(createName);
    }

    public static void selectGender() {
        String selectGender = "SELECT THE STUDENT'S GENDER (0 for MALE, 1 for FEMALE):";
        System.out.println(selectGender);
    }

    public static void createClass() {
        String createClass = "ENTER THE STUDENT'S CLASS:";
        System.out.println(createClass);
    }

    public static void editName() {
        String editName = "ENTER THE NEW NAME FOR THE SELECTED STUDENT:";
        System.out.println(editName);
    }

    public static void editGender() {
        String editGender = "SELECT THE NEW GENDER FOR THE SELECTED STUDENT (0 for MALE, 1 for FEMALE):";
        System.out.println(editGender);
    }

    public static void editClass() {
        String editClass = "ENTER THE NEW CLASS FOR THE SELECTED STUDENT:";
        System.out.println(editClass);
    }

    public static void studentSelect() {
        String studentSelect = "SELECT A STUDENT BY ENTERING HIS/HERS'S ID:";
        System.out.println(studentSelect);
    }

    public static void gradeSelect() {
        String gradeSelect = "SELECT A GRADE BY ENTERING IT'S ID:";
        System.out.println(gradeSelect);
    }

    public static void studentDelete() {
        String studentDelete = "SELECT A STUDENT YOU'D LIKE TO DELETE BY ENTERING HIS/HER'S ID:";
        System.out.println(studentDelete);
    }

    public static void invalidNumber() {
        String invalidNumber = "PLEASE ENTER A VALID NUMBER:";
        System.out.println(invalidNumber);
    }

    public static void invalidString() {
        String invalidString = "PLEASE ENTER A VALID NAME:";
        System.out.println(invalidString);
    }

    public static void invalidID() {
        String invalidID = "THE ID YOU HAVE ENTERED DOES NOT EXIST IN THE DATABASE.";
        System.out.println(invalidID);
    }

    public static void invalidGrade() {
        String invalidGrade = "THE GRADE YOU'VE ENTERED IS INVALID. PLEASE ENTER A GRADE FROM 1-10";
        System.out.println(invalidGrade);
    }

    public static void emptyGradeBook() {
        String emptyGradeBook = "THE GRADEBOOK IS EMPTY. PLEASE ADD SOME DATA AND TRY AGAIN.";
        System.out.println(emptyGradeBook);
    }

    public static void addGrade() {
        String addGrade = "ENTER THE GRADE:";
        System.out.println(addGrade);
    }

    public static void removeGrade() {
        String removeGrade = "SELECT THE GRADE YOU'D LIKE TO REMOVE BY ENTERING IT'S ID:";
        System.out.println(removeGrade);
    }

    public static void sortName() {
        String sortedName = "THE GRADEBOOK SORTED BY STUDENT NAME:";
        System.out.println(sortedName);
    }

    public static void sortGender() {
        String sortedGender = "THE GRADEBOOK SORTED BY STUDENT GENDER:";
        System.out.println(sortedGender);
    }


    public static void sortClass() {
        String sortedClass = "THE GRADEBOOK SORTED BY CLASS:";
        System.out.println(sortedClass);
    }

    public static void sortAverage() {
        String sortedGrades = "THE GRADEBOOK SORTED BY STUDENT AVERAGE:";
        System.out.println(sortedGrades);
    }

    public static void errorMessage() {
        String exception = "AN EXCEPTION OCCURED. THE PROGRAM WILL NOW EXIT.";
        System.out.println(exception);
    }

    public static void subjectCreate() {
        String subjectCreate = "ENTER THE NAME FOR THE SUBJECT:";
        System.out.println(subjectCreate);
    }

    public static void subjectSelect() {
        String subjectSelect = "SELECT A SUBJECT BY ENTERING IT'S ID:";
        System.out.println(subjectSelect);
    }

    public static void subjectEdit() {
        String subjectEdit = "ENTER A NEW NAME FOR THE SUBJECT:";
        System.out.println(subjectEdit);
    }

    public static void subjectDelete() {
        String subjectEdit = "SELECT THE SUBJECT YOU'D LIKE TO REMOVE BY ENTERING IT'S ID:";
        System.out.println(subjectEdit);
    }
}
