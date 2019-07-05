package bodmas;

import java.util.Scanner;

import static bodmas.Evaluate.evaluate;
import static bodmas.Validate.validate;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Expression to Evaluate: ");
        String expression = scanner.nextLine();

        char[] tokens = expression.toCharArray();

        char[] validated = validate(tokens);

        if (validated[0] == 'E') {
            System.out.println("Review the Expression then Try Again");
        }

        else {
            System.out.println(evaluate(validated));
        }
    }
}
