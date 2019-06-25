package bodmas;



public class Validates {

	//This method ensures that two subsequent operators are valid and in the correct order
  public static boolean operatorSeq(String expression)
    {

        char[] tokens = expression.toCharArray();

        // Stack for numbers: 'values'
        for (int i = 0; i < tokens.length; i++) {
        		
        		if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/'  || tokens[i] == '(' || tokens[i] == ')'  || tokens[i] == '&') {
        			
        			// check when user enters similar subsequent operators
        			if(tokens[i] ==  tokens[i+1] ) {
						tokens[i] = ' ';
						i--;
    				}
        			
        			if(tokens[i+1] == '+' || tokens[i+1] == '-' || tokens[i + 1] == '(' ){
        				if(tokens[i+1] == '+' || tokens[i+1] == '-' ) {// when use enters + and - subsequently, remove + from equation and retain -
        					if(tokens[i] == '-' || tokens[i+1] == '-') {
        						tokens[i] = ' ';
        						tokens[i+1] = '-';
            					
            				}
        				}
        				
        
        				return true;//return true confirming correct order of operators
        			}else if(tokens[i+1] == '*' || tokens[i+1] == '/'  || tokens[i+1] == ')'  || tokens[i+1] == '&') {
        				return false;//return false confirming wrong order of subsequent operators
    				}
        		}
        		}
		return true;

        }
}
