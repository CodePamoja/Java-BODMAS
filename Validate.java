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
    
    //This methods check for balanced brackets
    
    public static boolean checkBrackets()
			{
				  Stack<Character> ops = new Stack<Character>();
				 
				int i = 0;
					for(i=0;i<tokens.length;i++)
					{
						if((tokens[i] == '(' ))
						{
							ops.push(tokens[i]);
							
						}else if(tokens[i] == ')')
						{
							if (!ops.empty())
							{
								ops.pop();
							
								
							}else
							{
								 System.out.println("Imbalanced brackets: ");
								return false;
							}

						

						


					}
				}

				if (!ops.empty())
				{
					 System.out.println("imbalanced brackets: ");
					return false;
					
				}
				return true;
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

        for (i =0; i<tokens.length; i++) {
            if (tokens[i]==' ') {

                for (int j=i; j<tokens.length-1; j++) {
                    tokens[j] = tokens[j+1];
                }
                tokens[tokens.length-1] = ' ';
            }
        }

        return true;
    }

    //This method ensures that two subsequent operators are valid and in the correct order
    public static boolean operatorSeq() {
        //Update

        // Stack for numbers: 'values'
        for (int i = 0; i < (tokens.length -1); i++) {

            if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/'  || tokens[i] == '(' || tokens[i] == ')'  || tokens[i] == '&') {

                // check when user enters similar subsequent operators

                if ( tokens[i] == ')' && tokens[i+1] == '(' ){

                    System.out.println("Invalid String");
                    return false;

                }

                if (tokens[i + 1] == '+' || tokens[i + 1] == '-' || tokens[i + 1] == '(' || tokens[i+1] == ')' ) {
                    if(tokens[i] == '+' && tokens[i + 1] == '+' ) {
                        tokens[i] = ' ';
                    }
                    if(tokens[i] == '-' && tokens[i + 1] == '-' ) {
                        tokens[i] = ' ';
                    }
                    if((tokens[i] == '-' ||   tokens[i + 1] == '-') && (tokens[i] == '+' || tokens[i + 1] == '+' ) ) {
                        tokens[i] = ' ';
                        tokens[i+1] = '-';

                    }



                    //2(+-32 - 34) -23


                    continue;//return true confirming correct order of operators
                }

                if ((tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '(') && (tokens[i + 1] == '*' || tokens[i + 1] == '/' || tokens[i + 1] == '&'  ) ) {

                    System.out.println("Invalid String");
                    return false;

                }

                if ((tokens[i] == '*' || tokens[i] == '/' || tokens[i] == '&') && (tokens[i + 1] == '*' || tokens[i + 1] == '/' || tokens[i + 1] == '&'  ) ) {

                    System.out.println("Invalid String");
                    return false;

                }

            }





        }



        return true;

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Expression to validate: ");
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

