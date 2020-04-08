package com.wfdss.interviewquestions;

import java.util.HashMap;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        //QUESTION 1
        System.out.println("WHITEBOARD QUESTION 1");
        String q1Input1 = "abcdef";
        String q1Output1 = stringShortener(q1Input1);
        System.out.println("    OUTPUT 1: " + q1Output1);  //Expected String abcdef

        String q1Input2 = "abbcdddeffff";
        String q1Output2 = stringShortener(q1Input2);
        System.out.println("    OUTPUT 2: " + q1Output2);  //Expected String ab2cd3ef4

        String q1Input3 = "aaabbcccdef";
        String q1Output3 = stringShortener(q1Input3);
        System.out.println("    OUTPUT 3: " + q1Output3);  //Expected String a3b2c3def

        //QUESTION 2
        System.out.println("");
        System.out.println("WHITEBOARD QUESTION 2");
        String q2Input1 = "{[()]}(){[]}{}";
        boolean q2Output1 = parenBalanceCheck(q2Input1);
        System.out.println("    OUTPUT 1: " + q2Output1);  //Expected true

        String q2Input2 = "{(}[](){[]}";
        boolean q2Output2 = parenBalanceCheck(q2Input2);
        System.out.println("    OUTPUT 2: " + q2Output2);  //Expected false

        String q2Input3 = "{{[()]}}({({[]})}";
        boolean q2Output3 = parenBalanceCheck(q2Input3);
        System.out.println("    OUTPUT 3: " + q2Output3);  //Expected false
    }

    /****
     * WHITE BOARD QUESTION 1
     * I want to write a String shortener... It will take in a given String, then for characters that repeat, I want to replace the extra characters with the numerical count.
     * 		For example:  Given the String input "abbcdddeffff" the output would be ab2cd3ef4".  How would you do that?
     *
     * SOLUTION BELOW
     */
    public static String stringShortener(String input) {
        char[] inputArray = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        int charCount = 1;  //Start the count at 1
        char temp = inputArray[0];  //Grab first char to compare against
        sb.append(temp);  //Add first char to our output
        for(int i = 1; i < inputArray.length; i++) { //Note starting at index 1 not 0, as we initialized temp to be 0
            if(inputArray[i] == temp) {  //check to see if the chars match
                charCount++;  //Increment the count
                if(i+1 == inputArray.length) {  //If they match, and it's the last element, we need to add to the StringBuilder
                    sb.append(charCount);
                }
            } else {
                if(charCount > 1) {  //Don't want to add a 1... that makes the String longer
                    sb.append(charCount);
                }
                temp = inputArray[i];  //Set a new value for temp
                sb.append(temp);  //Add temp to our StringBuilder
                charCount = 1;  //Reset the count to 1
            }
        }
        return sb.toString();
    }


    /****
     * WHITE BOARD QUESTION 2
     * I want to write a method to check for balanced parentheses and brackets... So if I have a block of code which has been converted to a string and stripped of all
     * characters which are not parentheses or brackets, write some code that will look at that string, and return true of false based on whether or not it's balanced.
     * 		For example:  Given the String input "[{()}]{}[()]" the output would be true; Given the String input "{(}[]()[]" the output would be false.  How would you do that?
     *
     * SOLUTION BELOW
     */
    static boolean parenBalanceCheck(String input) {
        char inputArray[] = input.toCharArray();
        Stack st = new Stack();
        for(int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] == '{' || inputArray[i] == '(' || inputArray[i] == '[') {  //If inputArray[i] is a starting paren push it
                st.push(inputArray[i]);
            }
            if (inputArray[i] == '}' || inputArray[i] == ')' || inputArray[i] == ']') {  //If inputArray[i] is an ending paren, run checks
                if (st.isEmpty()) {  //If it's an ending paren and stack is empty then return false
                    return false;
                } else if ( !isMatchingPair((Character) st.pop(), inputArray[i]) ) {  //Pop the top element from stack, and check for match
                    return false;
                }
            }
        }
        if (!st.isEmpty()) {  //If stack isn't empty then there's a start without close
            return false;  //not balanced
        } else {
            return true;  //balanced
        }
    }

    static boolean isMatchingPair(Character character1, Character character2) {
        //Acceptable Solution
        if (character1 == '(' && character2 == ')') {
            return true;
        } else if (character1 == '{' && character2 == '}') {
            return true;
        } else if (character1 == '[' && character2 == ']') {
            return true;
        } else {
            return false;
        }

        //Better Solution
/*        HashMap<Character, Character> bracketMap = new HashMap<>();
        bracketMap.put('{','}');
        bracketMap.put('[',']');
        bracketMap.put('(',')');
        return bracketMap.get(character1) == character2;*/

    }

}
