/* Tried to correct operator sequence,
 * Added the brackets checker
 */
// Run main to Test whether it works

import java.util.Scanner;
import java.util.Stack;

public class Validate {

    public static char[] tokens;

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
        int i = 0;
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
            else if (tokens[i] == ' ' && i < tokens.length - 1) {
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

                //Swap the space with the previous character
                else  {
                    tokens[i] = tokens[i-1];
                    tokens[i-1] = ' ';
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
        return true;
    }

    //This method ensures that two subsequent operators are valid and in the correct order
    public static boolean operatorSeq() {
        for (int i = 0; i < (tokens.length); i++) {

            if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/'  || tokens[i] == '(' || tokens[i] == ')'  || tokens[i] == '&') {

                //check for opening bracket
                if (tokens[i] == '(' && (tokens[i + 1] == '*' || tokens[i + 1] == '/' || tokens[i + 1] == '&')) {
                    System.out.println("Invalid sequence; "+tokens[i]+" cannot be followed by "+tokens[i+1]);
                    return false;
                }

                //check for closing bracket
                else if (tokens[i] == ')' && i != tokens.length-1 && !(tokens[i + 1] == '+' || tokens[i + 1] == '-' || tokens[i + 1] == '*' || tokens[i + 1] == '/' || tokens[i + 1] == '(' || tokens[i + 1] == ')' || tokens[i + 1] == '&' || tokens[i + 1] == ' ')) {
                    System.out.println("Invalid sequence; "+tokens[i]+" cannot be followed by "+tokens[i+1]);
                    return false;
                }

                //check for multiplication
                else if (tokens[i] == '*' && (tokens[i + 1] == '*' || tokens[i + 1] == '/' || tokens[i + 1] == '&')) {
                    System.out.println("Invalid sequence; "+tokens[i]+" cannot be followed by "+tokens[i+1]);
                    return false;
                }

                //check for division
                else if (tokens[i] == '/' && (tokens[i + 1] == '*' || tokens[i + 1] == '/' || tokens[i + 1] == '&')) {
                    System.out.println("Invalid sequence; "+tokens[i]+" cannot be followed by "+tokens[i+1]);
                    return false;
                }

                //check for 'of'
                else if (tokens[i] == '&' && (tokens[i + 1] == '*' || tokens[i + 1] == '/' || tokens[i + 1] == '&')) {
                    System.out.println("Invalid sequence; "+tokens[i]+" cannot be followed by "+tokens[i+1]);
                    return false;
                }

                //check for addition
                else if (tokens[i] == '+' && (tokens[i + 1] == '*' || tokens[i + 1] == '/' || tokens[i + 1] == '&')) {
                    System.out.println("Invalid sequence; "+tokens[i]+" cannot be followed by "+tokens[i+1]);
                    return false;
                } else if (tokens[i] == '+' && (tokens[i + 1] == '+')) {
                    tokens[i] = ' ';
                } else if (tokens[i] == '+' && (tokens[i + 1] == '-')) {
                    tokens[i] = ' ';
                    tokens[i + 1] = '-';
                }

                //check for subtraction
                else if (tokens[i] == '-' && (tokens[i + 1] == '*' || tokens[i + 1] == '/' || tokens[i + 1] == '&')) {
                    System.out.println("Invalid sequence; "+tokens[i]+" cannot be followed by "+tokens[i+1]);
                    return false;
                } else if (tokens[i] == '-' && (tokens[i + 1] == '-')) {
                    tokens[i] = ' ';
                    tokens[i + 1] = '+';
                } else if (tokens[i] == '-' && (tokens[i + 1] == '+')) {
                    tokens[i] = ' ';
                    tokens[i + 1] = '-';
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Expression to Evaluate: ");
        String expression = scanner.nextLine();

        tokens = expression.toCharArray();

        if (validateFirstindex()) {
            if (endExpression()) {
                if (checkBrackets()) {
                    if (validCharacters()) {
                        if (operatorSeq()) {
                            System.out.println("Expression is Valid");
                            for (int i=0; i<tokens.length; i++) {
                                System.out.print(tokens[i]);
                            }
                        }
                    }
                }
            }
        }
    }
}
