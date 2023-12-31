package emailapp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Email {

    // Strings for first and last name inputs by user
    private String firstName;
    private String lastName;

    // Used for password generation with randomPassword() method
    private String password;
    private int passwordLength = 10;

    // String for the abreviation of the user's department 
    private String department;

    // Stores email generated based on firstName, lastName, companySuffix, and department
    private String email;

    // Defines the mailbox capacity in megabytes.
    private int mailboxCapacity = 500;

    // name of company used for the email
    private String companySuffix = "newcompany.com";
    private boolean exceptionTest;


    // Constructor

    public Email () {
        // Call a method asking for the first name then return the first name
        this.firstName = setFirstName();

        // Call a method asking for the last name then return the last name
        this.lastName = setLastName();

        // Call a method asking for the department then return the department
        // Uses a do-while loop and catches a InputMismatchException to prevent crash
        do {
            try{
                this.department = setDepartment();
                exceptionTest = true;
            } catch (InputMismatchException s) {
                System.out.println("Please input a integer value.");
                exceptionTest = false;
            }
        } while (exceptionTest == false);

        // Calls randomPassword method that returns a random password based on passwordLength
        this.password = randomPassword(passwordLength);

        // Combine elements to generate a email
        this.email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + department + companySuffix;
    }

    // Asks user for First Name and returns user input
    private String setFirstName() {
        System.out.println("Enter First Name: ");
        Scanner in = new Scanner(System.in);
        String firstName = in.next();
        return firstName;
    }

    // Does exact same as setFirstName() method
    private String setLastName()  {
        System.out.println("Enter Last Name: ");
        Scanner in = new Scanner(System.in);
        String lastName = in.next();
        return lastName;
    } 

    /**
     * Asks the user for the department code and uses while loop to test whether integers
     * entered that are between 0-3.
     * 
     * @return Department choice for email
     * @throws InputMismatchException
     */
    private String setDepartment() throws InputMismatchException {
        boolean test = true;
        int depChoice;
        System.out.println("The Following Departments Are:\n1 for sales\n2 for Development\n3 for Accounting\n0 for none\nPlease Enter Department Code: ");
        Scanner in = new Scanner(System.in);
        
        while(test == true) {
            depChoice = in.nextInt();
            if (depChoice == 1) {
                test = false;
                return "sales.";
            } else if (depChoice == 2) {
                test = false;
                return "dev.";
            } else if (depChoice == 3) {
                test = false;
                return "acct.";
            } else if (depChoice == 0) {
                test = false;
                return "";
            } else {
                System.out.println("Please input a correct number for the department code.");
                test = true;
            }
        }
        return "error for setDepartment";
    }   

    /**
     * Generates a random password
     * Math.random() is multiplied by the length of the string passwordSet and is casted
     * as a integer.
     * This integer value is then used to select the character at a specific point in passwordSet
     * and the character is then inputted into the character array.
     * 
     * @param length
     * @return new string that represents the sequence of characters in the character array.
     */
    private String randomPassword(int length) {
        String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%";
        char[] password = new char[length];
        for (int i = 0; i<length; i++) {
            int ran = (int) (Math.random() * passwordSet.length());
            password[i] = passwordSet.charAt(ran);
        }
        return new String(password);
    }

    // Set the mailbox capacity
    protected void setMailboxCapacity(int capacity) {
        this.mailboxCapacity = capacity;

    }

    // Change password
    protected void changePassword(String password) {
        this.password = password;
    }


    // The following are get methods.
    protected int getMailboxCapacity() {
        return mailboxCapacity;
    }


    protected String getPassword() {
        return password;
    }

    // Returns multiple values of all info.
    protected String showInfo() {
        return "DISPLAY NAME: " + firstName + " " + lastName +
               "\nCOMPANY EMAIL: " + email +
               "\nPASSWORD: " + password +
               "\nMAILBOX CAPACITY: " + mailboxCapacity + "mb";
    }
}