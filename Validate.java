package bodmas;



public class Validates {
	
 //this method checks if the end of the expression is valid
   public static boolean endExpression(String expression){
 
       char[] tokens = expression.toCharArray();
    
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
