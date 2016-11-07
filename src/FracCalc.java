import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * @author Rohan Rk
 */
public class FracCalc {

    private static String operation;

    public static String produceFraction(String input) {

    	Scanner decoder = new Scanner(input);
    	Fraction fraction1 = Fraction.parseFraction(decoder.next());
    	operation = decoder.next();
    	Fraction fraction2 = Fraction.parseFraction(decoder.next());
        Fraction result = calculate(fraction1, fraction2);
    	while (decoder.hasNext()) {
            operation = decoder.next();
            Fraction other = Fraction.parseFraction(decoder.next());
            result = calculate(result, other);
        }

        decoder.close();
    	return result.toString();
    }

    public static Fraction calculate(Fraction a, Fraction b) {

        if (operation.equals("+")) {
            return Fraction.addFractions(a, b);
        } else if (operation.equals("-")) {
            b.negate();
            return Fraction.addFractions(a, b);
        } else if (operation.equals("*")) {
            return Fraction.multiplyFractions(a, b);
        } else if (operation.equals("/")) {
            b.reciprocateFraction();
            return Fraction.multiplyFractions(a, b);
        }
        return null;
    }

    public static void initialize() {

    	System.out.println("Welcome to FracCalc! ");
    	System.out.println("WARNING! This program was written under the assumption that all inputs"
    			+ " are valid.\n Also when prompted for input please type in your expressions with spaces between"
    			+ "\n terms and operations.");
    }

    public static void cleanup() {
    	System.out.println("Thanks for using FracCalc!");
    }

    public static void main(String[] args) {

        initialize();
        Scanner console = new Scanner(System.in);
        System.out.print("Type your expression or type quit to exit: ");
        String input = console.nextLine();
        while(!input.equalsIgnoreCase("quit")) {
            try {
                String answer = produceFraction(input);
                System.out.println(answer);
            } catch(InputMismatchException e) {
                System.out.println("You cannot have a denomonator of 0\n");
            } catch(NoSuchElementException e) {
                System.out.println("Your expression was invalid. Try again!\n");
            } catch(NumberFormatException e) {
                System.out.println("Your expression was invalid. Try again!\n");
            }
            System.out.print("Type your expression or type quit to exit: ");
            input = console.nextLine();
        }

        console.close();
        cleanup();
    }

}
