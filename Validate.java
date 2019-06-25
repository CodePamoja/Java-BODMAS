package bodmas;



public class Validates {
	
 //this method checks if the end of the expression is valid
   public static boolean endExpression(){
    
       //k is the position of the last character
       int k = tokens.length -1;
	   
       //condition checks the last character of the array
       //if the last character matches the given condition, the expression is valid
       //else, it is invalid	   
       if(Character.isDigit(tokens[k])|| tokens[k]==' ' || tokens[k]==')'){
	    System.out.println("End of expression is valid");   
            return true;
       } else{
	    System.out.println("End of expression is invalid!");    
            return false;
       }
      
    }	

	//This method ensures that two subsequent operators are valid and in the correct order
public static boolean operatorSeq(String expression)
    {

        char[] tokens = expression.toCharArray();

        // Stack for numbers: 'values'
        for (int i = 0; i < (tokens.length - 1); i++) {
        		
        		if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/'  || tokens[i] == '(' || tokens[i] == ')'  || tokens[i] == '&') {
        			
        			// check when user enters similar subsequent operators
        			
        			
        			if(tokens[i+1] == '+' || tokens[i+1] == '-' || tokens[i + 1] == '(' ){
        				if(tokens[i+1] == '+' || tokens[i+1] == '-' ) {// when use enters + and - subsequently, remove + from equation and retain -
        					if(tokens[i] == '-' || tokens[i+1] == '-') {
        						tokens[i] = ' ';
        						tokens[i+1] = '-';
            					
            				}
        					
        				}
        				
        				
        				
        				continue;//return true confirming correct order of operators
        			}else if(tokens[i+1] == '*' || tokens[i+1] == '/'  || tokens[i+1] == ')'  || tokens[i+1] == '&') {
        				System.out.println("Illegal order of operators: " + tokens[i+1] + " cannot come after" + tokens[i]);
        				return false;//return false confirming wrong order of subsequent operators
    				}
        		}
        		}

		return true;

        }


	public static boolean validateCharacters(){

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
                    return false;
                }
            }

            //Check whether element at current index is a blank Space
            else if (tokens[i] == ' ' && i < tokens.length - 1) {
                //Check whether it is between a number and a decimal point
                if (tokens[i-1] >= '0' && tokens[i-1] <= '9' && tokens[i+1] == '.') {
                    return false;
                }

                //Check whether it is between two numbers
                else if (tokens[i-1] >= '0' && tokens[i-1] <= '9' && tokens[i+1] >= '0' && tokens[i+1] <= '9') {
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
                return false;
            }
        }

        return true;
    }
}
