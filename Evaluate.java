package bodmas;

import java.util.Stack;

public class Evaluate {

    public static double evaluate(char[] tokens) {

        // Stack for numbers: 'values'
        Stack<Double> values = new Stack<Double>();


        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<Character>();

        int i = 0;
        while ( i < tokens.length ) {

            // Current token is a number, push it to stack for numbers
            if ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.') {

                StringBuffer sbuf = new StringBuffer();
                // There may be more than one digits in number

                int j = i;
                while (j < tokens.length) {
                    if ((tokens[j] >= '0' && tokens[j] <= '9') || tokens[j] == '.'){
                        sbuf.append(tokens[j++]);
                    }
                    else break;
                }
                i = j-1;
                values.push(Double.parseDouble(sbuf.toString()));
                i++;
            }

            //Current token is a negative number after an opening bracket, multiplication or division operators
            else if (tokens[i]=='-' && i != 0 && (tokens[i-1]=='(' || tokens[i-1]=='*' || tokens[i-1]=='/')) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(tokens[i]);
                int j = i+1;
                while (j < tokens.length) {
                    if ((tokens[j] >= '0' && tokens[j] <= '9') || tokens[j] == '.'){
                        buffer.append(tokens[j++]);
                    }
                    else break;
                }
                i = j-1;

                values.push(Double.parseDouble(buffer.toString()));
                i++;
            }

            //Current token is a positive number after an opening bracket, multiplication or division operators
            else if (tokens[i]=='+' && i != 0 && (tokens[i-1]=='(' || tokens[i-1]=='*' || tokens[i-1]=='/')) {
                i++;
            }

            // Current token is an opening brace, push it to 'ops'
            else if (tokens[i] == '(' ) {
                ops.push(tokens[i]);
                i++;
            }


            // Closing brace encountered, solve entire brace
            else if (tokens[i] == ')') {
                while (ops.peek() != '(') {
                    values.push(solve(ops.pop(), values.pop(), values.pop()));
                }
                ops.pop();
                i++;
            }

            // Current token is an operator.
            else if (tokens[i] == '&' ||tokens[i] == '+' || tokens[i] == '-' ||
                    tokens[i] == '*' || tokens[i] == '/') {
                // While top of 'ops' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.empty() && !values.empty() && hasPrecedence(tokens[i], ops.peek())) {
                    double a = values.pop();
                    double b;
                    if (values.empty()) {
                        b=0;
                    }
                    else {
                        b=values.pop();
                    }

                    values.push(solve(ops.pop(), a, b));
                }

                // Push current token to 'ops'.
                ops.push(tokens[i]);
                i++;
            }
        }

        // Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty()) {
            char op = ops.pop();
            double x = values.pop();
            double y;
            if (values.empty()) {
                y = 0;
            }
            else {
                y = values.pop();
            }
            values.push(solve(op, x, y));
        }

        // Top of 'values' contains result, return it
        return values.pop();
    }

    // Returns true if 'op2' has higher or same precedence as 'op1',
    // otherwise returns false.
    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;

        if ((op1 == '&' || op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;

        else
            return true;
    }

    // A utility method to apply an operator 'op' on operands 'a'
    // and 'b'. Return the result.
    public static double solve(char op, double b, double a) {
        switch (op)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '&':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }
}
