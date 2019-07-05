package bodmas;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class Validate {

    public static char[] tokens;
    public static List<Character> tempList;

    //This method checks if the character at the beginning of the expression is valid
    public static boolean validateFirstindex(){
        char first= tokens[0];

        if ((first>='0' && first<='9')|| first=='+' || first=='-' || first=='(' || first==' '){

            return true;
        }else{
            System.out.println("Invalid sequence; "+tokens[0]+" cannot be at the beginning of an expression");
            return false;
        }
    }

    //This method checks if the end of the expression is valid
    public static boolean endExpression() {

        //k is the position of the last character
        int k = tokens.length -1;

        //condition checks the last character of the array
        //if the last character matches the given condition, the expression is valid
        //else, it is invalid
        if(Character.isDigit(tokens[k])|| tokens[k]==' ' || tokens[k]==')'){
            return true;
        } else{
            System.out.println("Invalid sequence; "+tokens[tokens.length-1]+" cannot be at the end of an expression");
            return false;
        }
    }

    //Check whether all brackets are balanced
    public static boolean checkBrackets() {
        //Initialize stack to store brackets
        Stack<Character> brackets = new Stack<Character>();

        for (int i=0; i<tokens.length; i++) {
            if (tokens[i] == '(') {
                brackets.push(tokens[i]);
            }

            else if (tokens[i] == ')') {
                if (brackets.empty()) {
                    System.out.println("Brackets are not balanced");
                    return false;
                }
                else brackets.pop();
            }
        }

        if (!brackets.empty()) {
            System.out.println("Brackets are not balanced");
            return false;
        }
        return true;
    }

    //This method ensures the Expression contains valid characters
    public static boolean validCharacters(){
        //Iterate through entire tokens
        int i = 1;
        while(i < tokens.length) {

            //Check whether element at current index is a number
            if(tokens[i] >= '0' && tokens[i] <= '9') {
                i++;
            }

            //Check whether element at current index is an operator
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                i++;
            }

            //Check whether element at current index is a Special Character
            else if (tokens[i] == '(' || tokens[i] == ')' || tokens[i] == '&') {
                i++;
            }

            //Check whether element at current index is a decimal point
            else if (tokens[i] == '.') {
                //Check if it is followed by a valid character
                if (tokens[i+1] >= '0' && tokens[i+1] <= '9') {
                    i++;
                }
                else {
                    System.out.println("Illegal character at "+tokens[i]);
                    return false;
                }
            }

            //Check whether element at current index is 'O'
            else if (tokens[i] == 'O' || tokens[i] == 'o' ) {
                //Check if it is followed by 'f' then replace it with '&'
                if (tokens[i+1] >= 'F' || tokens[i+1] <= 'f') {
                    tokens[i+1] = '&';
                    tokens[i] = ' ';
                    i++;
                }
                else {
                    System.out.println("Illegal character sequence near "+tokens[i]);
                    return false;
                }
            }

            //Check whether element at current index is a blank Space
            else if (tokens[i] == ' ' && i < tokens.length - 1 ) {
                //Check whether it is between a number and a decimal point
                if (tokens[i-1] >= '0' && tokens[i-1] <= '9' && tokens[i+1] == '.') {
                    System.out.println("Illegal character sequence near "+tokens[i]);
                    return false;
                }

                //Check whether it is between two numbers
                else if (tokens[i-1] >= '0' && tokens[i-1] <= '9' && tokens[i+1] >= '0' && tokens[i+1] <= '9') {
                    System.out.println("Illegal character sequence near "+tokens[i]);
                    return false;
                }

                //Move i to the next character
                else  {
                    i++;
                }
            }

            //Check whether current element is a blank space at the end
            else if (tokens[i] == ' ' && i == tokens.length - 1) {
                i++;
            }

            else {
                System.out.println("Illegal character at "+tokens[i]);
                return false;
            }
        }

        removeSpaces();
        return true;
    }

    //This method ensures that two subsequent operators are valid and in the correct order
    public static boolean operatorSeq() {
        for (int i = 0; i < (tokens.length-1); i++) {

            if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/'  || tokens[i] == '(' || tokens[i] == ')'  || tokens[i] == '&') {

                //check for opening bracket
                if (tokens[i] == '(' && (tokens[i + 1] == '*' || tokens[i + 1] == '/' || tokens[i + 1] == '&')) {
                    System.out.println("Invalid sequence; '(' cannot be followed by "+tokens[i+1]);
                    return false;
                }

                //check for closing bracket
                else if (tokens[i] == ')' && i != tokens.length-1 && (tokens[i + 1] == '(')) {
                    System.out.println("Invalid sequence; ')' cannot be followed by "+tokens[i+1]);
                    return false;
                }

                //check for multiplication
                else if (tokens[i] == '*' && (tokens[i + 1] == '*' || tokens[i + 1] == '/' || tokens[i + 1] == '&' || tokens[i + 1] == ')')) {
                    System.out.println("Invalid sequence; '*' cannot be followed by "+tokens[i+1]);
                    return false;
                }

                //check for division
                else if (tokens[i] == '/' && (tokens[i + 1] == '*' || tokens[i + 1] == '/' || tokens[i + 1] == '&' || tokens[i + 1] == ')')) {
                    System.out.println("Invalid sequence; '/' cannot be followed by "+tokens[i+1]);
                    return false;
                }

                //check for 'of'
                else if (tokens[i] == '&' && (tokens[i + 1] == '*' || tokens[i + 1] == '/' || tokens[i + 1] == '&' || tokens[i + 1] == ')')) {
                    System.out.println("Invalid sequence; 'of' cannot be followed by "+tokens[i+1]);
                    return false;
                }

                //check for addition
                else if (tokens[i] == '+' && (tokens[i + 1] == '*' || tokens[i + 1] == '/' || tokens[i + 1] == '&' || tokens[i + 1] == ')')) {
                    System.out.println("Invalid sequence; '+' cannot be followed by "+tokens[i+1]);
                    return false;
                } else if (tokens[i] == '+' && (tokens[i + 1] == '+')) {
                    tokens[i] = ' ';
                } else if (tokens[i] == '+' && (tokens[i + 1] == '-')) {
                    tokens[i] = ' ';
                    tokens[i + 1] = '-';
                }

                //check for subtraction
                else if (tokens[i] == '-' && (tokens[i + 1] == '*' || tokens[i + 1] == '/' || tokens[i + 1] == '&' || tokens[i + 1] == ')')) {
                    System.out.println("Invalid sequence; '-' cannot be followed by "+tokens[i+1]);
                    return false;
                } else if (tokens[i] == '-' && (tokens[i + 1] == '-')) {
                    tokens[i] = ' ';
                    tokens[i + 1] = '+';
                } else if (tokens[i] == '-' && (tokens[i + 1] == '+')) {
                    tokens[i] = ' ';
                    tokens[i + 1] = '-';
                }
            }

            else if ((tokens[i] >= '0' && tokens[i] <= '9') && tokens[i+1] == '(') {
                insertMultiplication(i+1);
            }
        }
        removeSpaces();

        return true;
    }

    public static void removeSpaces() {
        tempList = new ArrayList<Character>();
        int k = 0;
        while (k < tokens.length) {
            if(tokens[k] == ' ') {
                k++;
            }
            else {
                tempList.add(tokens[k]);
                k++;
            }
        }

        for (int i=0; i<tokens.length; i++){
            tokens[i] = ' ';
        }

        for (int i=0; i<tempList.size(); i++){
            tokens[i] = tempList.get(i);
        }
    }

    //when number is followed by an opening bracket, call this method to insert the multiplication operator
    public static void insertMultiplication(int index) {
        tempList = new ArrayList<Character>();
        int i = 0;
        while (i<tokens.length) {
            if (i == index) {
                tempList.add('*');
                tempList.add(tokens[i]);
            }
            else {
                tempList.add(tokens[i]);
            }
            i++;
        }

        tokens = new char[tempList.size()];
        for (i=0; i<tempList.size(); i++) {
            tokens[i] = tempList.get(i);
        }
    }

    public static char[] validate(char[] expression) {
        tokens = expression;
        char[] error = {'E'};
        if (validateFirstindex()) {
            if (endExpression()) {
                if (checkBrackets()) {
                    if (validCharacters()) {
                        if (operatorSeq()) {
                            return tokens;
                        }
                    }
                }
            }
        }
        return error;
    }
}
