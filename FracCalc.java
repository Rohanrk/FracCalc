import java.util.*;

public class FracCalc {

	public static String operation;

    public static void main(String[] args)
    {

    	//Initialize prints a welcome message once the program starts
    	Initialize();

    	//Invokes a scanner to determine system input
    	Scanner console = new Scanner(System.in);
    	System.out.print("Type your expression or type quit to exit: ");
    	String input = console.nextLine();
    	//We want this program to continue until the user types quit
    	while(!input.equalsIgnoreCase("quit")) {
    	String secondTerm = produceAnswer(input);
    	System.out.println(secondTerm);
    	System.out.print("Type your expression or type quit to exit: ");
    	input = console.nextLine();
    	}
    	//Closes console to avoid resource leaks
    	console.close();
    	cleanup();
    }

    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(final String input) {

    	/* We create a scanner in order to scan and store the three separate tokens
    	 * that exist in the user input
    	 */
    	Scanner decoder = new Scanner(input);
    	final String Frac1 = decoder.next();
    	operation = decoder.next();
    	final String Frac2 = decoder.next();

    	//We interpret the two terms as an array.
    	int[] Fraction1 = interpretTerm(Frac1);
    	int[] Fraction2 = interpretTerm(Frac2);
    	//Handles negatives
    	if(Frac1.contains("-")) {
    		Fraction1[0] = Fraction1[0] * -1;
    		Fraction1[1] = Fraction1[1] * -1;
    	}
    	if(Frac2.contains("-")) {
    		Fraction2[0] = Fraction2[0] * -1;
    		Fraction2[1] = Fraction2[1] * -1;
    	}
    	/*Now that we have everything as an array, we call produceResultFrac
    	 * This will mathematically calculate the desired values and store them as
    	 * an array.
    	 */
    	int[] Solution = produceResultFrac(Fraction1, Fraction2);
    	//Print statement to take the results from arrays.
    	String Statement = Solution[0] + "_" + Solution[1] + "/" + Solution[2];
        // TODO: Implement this function to produce the solution to the input
        decoder.close();
    	return Statement;
    }

    //Helper methods below

	public static int[] interpretTerm(String string) {
		//Since we only care about the numbers, I used a delimiter to extract them.
		Scanner Numbers = new Scanner(string).useDelimiter("[^0-9]");
		//Initializes all parts at standard values
		int whole = 0;
		int num = 0;
		int den = 1;
		//First we want to determine if there is a fractional part
		if(string.contains("/")) {
			//If there is a fractional part, does it contain a whole number?
			if(string.contains("_")) {
				whole = Integer.parseInt(Numbers.next());
				num = Integer.parseInt(Numbers.next());
				den = Integer.parseInt(Numbers.next());
			}
			//If it does not contain a whole number, then whole is 0
			else {
				num = Integer.parseInt(Numbers.next());
				den = Integer.parseInt(Numbers.next());
			}
		}
		//If there is no fractional part numerator is 0 and denominator = 1
		else {
			whole = Integer.parseInt(Numbers.next());
		}

		/* Using the parsed values, we can now create an array with the parts
		* of our mixed numeral
		*/
		int[] Fraction = new int[3];
		Fraction[0] = whole;
		Fraction[1] = num;
		Fraction [2] = den;
		Numbers.close();
		return Fraction;


	}

	//Handles all operations. This method returns an improper fraction answer in array form
	public static int[] produceResultFrac(int[] a, int[] b) {
		int totalNum = 0;
		int totalDen = 0;
		int numerator1 = a[0]*a[2] + a[1];
		int numerator2 = b[0]*b[2] + b[1];

		//If the operation is addition
		if(operation.equals("+")) {

			totalNum = numerator1*b[2] + numerator2*a[2];
			totalDen = a[2]*b[2];

		}
		//If the operation is subtraction
		else if(operation.equals("-")) {

			totalNum = numerator1*b[2] - numerator2*a[2];
			totalDen = a[2]*b[2];
		}
		//If the operation is multiplication
		else if(operation.equals("*")) {

			totalNum = numerator1*numerator2;
			totalDen = a[2]*b[2];
		}
		//If the operation is division
		else if(operation.equals("/")) {

			totalNum = numerator1*b[2];
			totalDen = numerator2*a[2];
		}

		int Factor = gcd(totalNum,totalDen);
		totalNum = totalNum/Factor;
		totalDen = totalDen/Factor;

		int totalWhole = totalNum/totalDen;
		totalNum = totalNum % totalDen;
		//Places the results in an array
		int[] finalValue = new int[3];
		finalValue[0] = totalWhole;
		finalValue[1] = totalNum;
		finalValue[2] = totalDen;
		return finalValue;
	}

	public static int gcd(int a, int b)
	{
	    while (b > 0)
	    {
	        int temp = b;
	        b = a % b; // % is remainder
	        a = temp;
	    }
	    return a;
	}


	//Prints a welcome message along with an important warning.
    public static void Initialize() {

    	System.out.println("Welcome to FracCalc! If you need to calculate expressions with "
    			+ "mixed numerals, you've come to the right place! ");
    	System.out.println("WARNING! This program was written under the assumption that all inputs"
    			+ " are valid.\n Also when prompted for input please type in your expressions with spaces between"
    			+ "\n terms and operations as stated in the FracCalc directions. Failure to do so will cause"
    			+ "\n exceptions to be thrown as the scanner will be expecting three different tokens as opposed to one.");
    }

    //Prints a message just before program terminates
    public static void cleanup() {

    	System.out.println("Thanks for using FracCalc!");
    }


}
