package com.georgebotas.gradebook.UI;

public class UserInputValidator {

    public static boolean validateUserString(String userName) {
        return !userName.trim().isEmpty();
    }

    public static boolean validateUserInt(int userInt) { return String.valueOf(userInt).matches("[0-9]+"); }

}
