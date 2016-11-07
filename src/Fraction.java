import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Rohan Rk
 */
public class Fraction {

    private int whole;
    private int numerator;
    private int denominator;

    /**
     * Public constructor
     * 
     * @param w The whole part of this fraction
     * @param n The numerator of this fraction
     * @param d The denominator
     */
    public Fraction(int w, int n, int d) {

        whole = w;
        numerator = n;
        setdenominator(d);
    }

    public int getWhole() {
        return whole;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getdenominator() {
        return denominator;
    }

    public void setdenominator(int d) {

        if (d != 0) {
            denominator = d;
        } else {
            throw new InputMismatchException("denominator cannot be 0");
        }
    }

    public void simplify() {

        int factor = gcd(Math.abs(numerator), denominator);
        numerator = numerator/factor;
        denominator = denominator/factor;
    }

    public void negate() {

        whole = whole * -1;
        numerator = numerator * -1;
    }

    public int gcd(int a, int b) {
        while (b > 0) {

            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public void reciprocateFraction() {

        this.convertImproperFraction();
        int temp = numerator;
        numerator = denominator;
        denominator = numerator;
    }

    public void convertMixedNumeral() {

        if (whole == 0) {
            whole = numerator/denominator;
            numerator = numerator % denominator;
        }
    }

    public void convertImproperFraction() {

        if (whole != 0) {
            numerator = whole * denominator + numerator;
            whole = 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%d_%d/%d", whole, Math.abs(numerator), denominator);
    }

    public static Fraction parseFraction(String string) {

        Scanner Numbers = new Scanner(string).useDelimiter("[^0-9]");
        int whole = 0;
        int num = 0;
        int den = 1;
        if(string.contains("/")) {

            if(string.contains("_")) {
                whole = Integer.parseInt(Numbers.next());
                num = Integer.parseInt(Numbers.next());
                den = Integer.parseInt(Numbers.next());
            } else {
                num = Integer.parseInt(Numbers.next());
                den = Integer.parseInt(Numbers.next());
            }
        } else {
            whole = Integer.parseInt(Numbers.next());
        }
        if (string.contains("-")) {
            whole = whole * -1;
            num = num * -1;
        }
        Fraction frac = new Fraction(whole, num, den);
        return frac;

    }

    public static Fraction addFractions(Fraction a, Fraction b) {

        a.convertImproperFraction();
        b.convertImproperFraction();
        int newNum = (a.numerator * b.denominator) + (b.numerator * a.denominator);
        int newDen = a.denominator * b.denominator;
        Fraction result = new Fraction(0, newNum, newDen);
        result.simplify();
        result.convertMixedNumeral();
        return result;
    }

    public static Fraction multiplyFractions(Fraction a, Fraction b) {

        a.convertImproperFraction();
        b.convertImproperFraction();
        int newNum = a.numerator * b.numerator;
        int newDen = a.denominator * b.denominator;
        Fraction result = new Fraction(0, newNum, newDen);
        result.simplify();
        result.convertMixedNumeral();
        return result;
    }

}
