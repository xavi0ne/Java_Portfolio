/**
 * 
 * @author: Xavier Torres
 * @last modified: 2/15/2025
 * 
 * StringRecursion - A class that performs String operations
 * using various methods to print letters using recursion, to
 * replace letters, and to trim white space that may remain around
 * a String.
 */

public class StringRecursion {

    public static void main(String [] args) {

        printLetters("Cheetah");
        System.out.println();
        System.out.println(replace("data Structures", 'e', 'r'));
        System.out.println();
        System.out.println(indexOf('i', "xavione"));
        System.out.println();
        System.out.println(trim(" xavione "));
    
    }
    
/*
 * printLetters() - a method that leverages recursion to 
 * print the string and separates each character with a 
 * comma. 
 */
    public static void printLetters(String str) {
 
        if (str == null || str.isEmpty()) {         //method simply returns when null or empty.

            return;
        }
        System.out.print(str.charAt(0));        //base case.

        if(str.length() > 1) {      //adds the comma for each character after first element if greater than its length.

            System.out.print(", ");
        }
        printLetters(str.substring(1));     //recursive case to continue adding commas after each char.

    }

    /*
     * replace() - performs recursive approach with actions
     * to form a String by replacing the old char's with the new
     * using ternery operator and returns the result. 
     */
    public static String replace(String str, char oldChar, char newChar) {

        if (str == null) {

            return null;
        }
        if (str.equals("")) {

            return "";
        }

        char reorg = (str.charAt(0) == oldChar) ? newChar : str.charAt(0);

        return reorg + replace(str.substring(1), oldChar, newChar);
    }

/*
 * indexOf() - performs search to find and return the index
 * of the first occurrence of the char in the String using 
 * recursion. 
 */
    public static int indexOf(char ch, String str) {

        if (str == null || str == "" ) {

            return -1;
        }

        if (str.charAt(0) == ch) {      //base case

            return 0;
        }

        int found = indexOf(ch, str.substring(1));      //recursive case performs search within string

        if (found != -1) {

            return found +1;        //index is adjusted when char is found.
        }

        return -1;
    }

/*
 * trim() - leverages recursion to take a String and 
 * remove any space that leads or trails around the 
 * original String. 
 */
    public static String trim(String str) {

        if (str == null) {

            return null;
        }
        if (str.equals("")) {

            return "";
        }

        if (!(str.charAt(0) == ' ') && !(str.charAt(str.length() - 1) == ' ')) {

            return str;     //base case
        }

        if (str.charAt(0) == ' ') {      

            return trim(str.substring(1));      //recursive case performs beginning space removal
        }

        if (str.charAt(str.length() - 1) == ' ') {

            return trim(str.substring(0, str.length() - 1));        //recursive case performs ending space removal.
        }

        return str;
    }
}