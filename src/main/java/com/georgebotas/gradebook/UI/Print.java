package com.georgebotas.gradebook.UI;

public class Print {

    public static void mainMenu() {
        String mainMenu = "THE GRADEBOOK: PLEASE CHOOSE AN OPTION BY ENTERING THE CORESPONDING NUMBER:\n\n" +
                "1. SHOW GRADEBOOK\n2. CREATE NEW STUDENT\n3. EDIT A STUDENT\n4. REMOVE A STUDENT\n5. ADD A NEW GRADE\n6. EDIT A GRADE\n7. REMOVE A GRADE" +
                "\n8. SORT GRADEBOOK\n9. EXIT";
        System.out.println(mainMenu);
    }

    public static void createName() {
        String screateName = "ENTER THE STUDENT'S NAME:";
        System.out.println(screateName);
    }

    public static void selectGender() {
        String selectGender = "SELECT THE STUDENT'S GENDER (0 for MALE, 1 for FEMALE):";
        System.out.println(selectGender);
    }

    public static void createClass() {
        String createClass = "ENTER THE STUDENT'S CLASS:";
        System.out.println(createClass);
    }

    public static void editMenu() {
        String editMenu = "CHOOSE AN OPTION BY ENTERING THE CORESPONDING NUMBER:\n 1. EDIT STUDENT'S NAME\n 2. EDIT STUDENT'S GENDER\n 3. EDIT STUDENT'S CLASS";
        System.out.println(editMenu);
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

    public static void enterSubject() {
        String enterSubject = "ENTER THE SUBJECT:";
        System.out.println(enterSubject);
    }

    public static void removeGrade() {
        String removeGrade = "SELECT THE GRADE YOU'D LIKE TO REMOVE:";
        System.out.println(removeGrade);
    }

    public static void sortMenu() {
        String sortMenu = "CHOOSE THE TYPE OF SORTING:\n 1. SORT BY STUDENT NAME\n 2. SORT BY STUDENT GENDER\n 3. SORT BY CLASS\n 4. SORT BY GRADES";
        System.out.println(sortMenu);
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

    public static void exception() {
        String exception = "AN EXCEPTION OCCURED. THE PROGRAM WILL NOW EXIT.";
        System.out.println(exception);
    }
}
