package com.mypackage.service;

import com.mypackage.exceptions.MyException;

/**
 * Utility class used to implement server-side validation of user input
 */
final class Validator {
    private static String pattern;
    public Validator(){}

    private static boolean isEmpty(String input){
        if(input == null) return true;
        if(input.trim() == "") {
            return true;
        }
        return false;
    }

    static boolean isValidName(String name) {
        if(isEmpty(name)) {
            throw new MyException("No name provided");
        }
        pattern = "[A-Za-z]{3,20} [A-Za-z]{3,20}";
        if(!name.matches(pattern)) {
            throw new MyException("Invalid Name entry.");
        }
        return true;
    }

    static boolean isValidEmail(String email) {
        if(isEmpty(email)) {
            throw new MyException("No email provided");
        }
        pattern = "[A-Za-z]{1,32}([A-Za-z]|[0-9]){0,32}@[A-Za-z]{1,121}([A-Za-z]|[0-9]){0,121}.[a-z]{3}";
        if(!email.matches(pattern)){
            throw new MyException("Invalid email entry.");
        }
        return true;
    }

    public static boolean isValidUserId(int userId) {
        if(userId > 0) {
            return true;
        }
        throw new MyException("No userId provided");
    }
    public static boolean isValidSecret(String secret) {
        if(isEmpty(secret)) {
            throw new MyException("No password provided");
        }
        return true;
    }

    public static boolean isValidUserName(String username) {
        if(isEmpty(username)) {
            throw new MyException("No username provided");
        }
        return true;
    }

    public static boolean isValidType(String type) {
        if(isEmpty(type)){
            throw new MyException("No reimbursement type provided");
        }
        Reimb_Types[] types = Reimb_Types.values();
        boolean isInTypes = false;
        for (Reimb_Types t: types) {
            if(type.equalsIgnoreCase(t.toString())){
                isInTypes = true;
            }
        }
        if(isInTypes) return true;
        else throw new MyException("Invalid reimbursement type provided");
    }

    public static boolean isValidEditType(String type) {
        if(isEmpty(type)){
            throw new MyException("No edit type provided");
        }
        Status_Types[] types = Status_Types.values();
        for (Status_Types t: types) {
            if(type.equalsIgnoreCase(t.toString())){
                return true;
            }
        }
        throw new MyException("Invalid edit type provided");
    }

    public static boolean isValidQueryType(String query){
        if(isEmpty(query)){
            throw new MyException("No query type provided");
        }
        Query_Types[] types = Query_Types.values();
        for (Query_Types t: types) {
            if(query.equalsIgnoreCase(t.toString())){
                return true;
            }
        }
        throw new MyException("Invalid query type provided");
    }

    public static boolean isValidAmount(double amount) {
        if(amount > 0) {
            return true;
        }
        else {
            throw new MyException("Invalid amount provided");
        }
    }

    public static boolean isValidReimbID(int reimbID){
        if(reimbID >= 0) {
            return true;
        }
        else {
            throw new MyException("Invalid reimbursement ID provided");
        }
    }

}
