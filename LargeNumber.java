/************************************************************
 * C211 Fall 2023
 * Project Phase 2
 * Authors: Dana Vrajitoru
 * Class: LargeNumber
 * Implements an integer number of an unlimited size and 
 * a few useful arithmetic operations.
 ************************************************************/

package phase2;

import java.util.ArrayList; // import the ArrayList class

// A class designed to store large integers in an array called number
// with the sign stored in the attribute sign.
public class LargeNumber implements Comparable<LargeNumber> {

    ArrayList<Integer> number;
    int sign;

    // Default constructor: initialize as 0
    public LargeNumber() {
        number = new ArrayList<Integer>(1);
        number.set(0, 0);
        sign = 1; // positive
    }

    // Constructor with a given integer
    public LargeNumber(int n) {
        init(n);
    }

    // Initialize the object based on converting an integer to decimals
    public void init(int n) {
        if (n < 0) {
            n = -n; // store the absolute value in number
            sign = -1;
        } else
            sign = 1;
        if (number != null)
            number.clear(); // Clear existing data if any
        else
            number = new ArrayList<Integer>(0); // create an empty array

        // If n is 0 the while loop will not store anything in the array
        if (n == 0)
            number.add(0);
        
        // Convert the n to decimals and add it to the array one by one
        while (n > 0) {
            int dec = n % 10;
            number.add(dec);
            n = n / 10;
        }
    }

    // copy constructor
    public LargeNumber(LargeNumber other) {
        init(other);
    }

    // Initialize the object with a copy of another object
    public void init(LargeNumber other) {
        sign = other.sign;

        if (number != null)
            number.clear(); // Clear existing data if any

        number = new ArrayList<Integer>(0);

        // copy them manually
        for (Integer i : other.number)
            number.add(i);

        // use copy from Collections - something wrong with the size
        // Collections.copy(number, other.number);
    }
	
    // find out the number of digits
    public int getSize() {
        return number.size();
    }
    
    // find out the sign
    public int getSign() {
        return sign;
    }
    
    // overriding the toString() method
    public String toString() {
        String result = "";
        for (Integer i : number)
            result = "" + i + result; // add each new digit at the front
        if (sign < 0) // add the sign at the front
            result = "-" + result;
        return result;
    }

	
    // Convert to an integer
    public int toInt() {
        int wholeNumber = 0;

        for (int i = this.getSize() - 1; i >= 0; i--) {
            wholeNumber = wholeNumber * 10 + number.get(i);
        }
        return wholeNumber;
    }
	
	// Check if the number is equal to 0.
    public boolean isZero() {
        return getSize() == 1 && number.get(0) == 0;
    }
	
	/////////////////////////////////////////////////////////
	// Function stubs to be implemented by the teams
	
    	// Function allowing us to sort an array of large numbers
	// Team 3
    @Override
    public int compareTo(LargeNumber o) {
        // TODO Auto-generated method stub
        return 0;
    }
	
    // Team 6
    public LargeNumber(String n) {
    }

    // Team 6
    public void init(String n) {
	}
	
	// Team 1
    // Addition Function
 class LargeNumber {
    private ArrayList<Integer> digits;

    public LargeNumber() {
        digits = new ArrayList<>();
    }

    public LargeNumber(String number) {
        digits = new ArrayList<>();
        for (int i = 0; i < number.length(); i++) {
            digits.add(Character.getNumericValue(number.charAt(i)));
        }
    }

    public void add(LargeNumber other) {
        // Ensure that the current number has at least as many digits as the other number
        if (digits.size() < other.digits.size()) {
            expandDigits(other.digits.size() - digits.size());
        }

        int carry = 0;
        for (int i = 0; i < other.digits.size(); i++) {
            int sum = digits.get(digits.size() - i - 1) + other.digits.get(other.digits.size() - i - 1) + carry;
            digits.set(digits.size() - i - 1, sum % 10);
            carry = sum / 10;
        }

        // If there's still a carry after adding all digits, add it as a new digit
        if (carry > 0) {
            digits.add(0, carry);
        }
    }

    // Method to expand the digits list to accommodate more digits
    private void expandDigits(int count) {
        for (int i = 0; i < count; i++) {
            digits.add(0, 0);
        }
    }

    // Other methods like subtraction, multiplication, division, etc. can be implemented similarly

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int digit : digits) {
            sb.append(digit);
        }
        return sb.toString();
    }
}

	
    // Team 2
    public void subtract(LargeNumber other) {
    	// Determine the sign and magnitude relationship between this and other
    	int compareMagnitude = compareMagnitude(other);
    	// If they have the same magnitude and sign, the result is zero.
    	if (compareMagnitude == 0 && this.sign == other.sign) {
        	this.number.clear(); // Clear the current number to reset it
        	this.number.add(0); // The result is zero
        	this.sign = 1; // The sign of the result is positive
        	return;
    	}

        // Determine which number is larger based on the magnitude comparison.
        boolean thisIsLarger = compareMagnitude > 0; // True if this number is larger
        LargeNumber larger = thisIsLarger ? this : other; // Assign the larger number
        LargeNumber smaller = thisIsLarger ? other : this; // Assign the smaller number

        ArrayList<Integer> result = new ArrayList<>();
        int borrow = 0; // Initialize borrow to 0
        for (int i = 0; i < larger.number.size(); i++) {
        	// Get digits from each number
        	int largerDigit = i < larger.number.size() ? larger.number.get(i) : 0;
        	int smallerDigit = i < smaller.number.size() ? smaller.number.get(i) : 0;
        	// If the smaller number is being subtracted from the larger, adjust digits for subtraction
        	if (!thisIsLarger) {
            		smallerDigit = -smallerDigit; // Invert the subtraction
        	}

        	int diff = largerDigit - smallerDigit - borrow; // Compute the subtraction
        	if (diff < 0) {
            		borrow = 1; // Set borrow for the next digit if the result is negative
            		diff += 10; // Adjust the difference by adding base (10) to it
        	} else {
            		borrow = 0; // No borrow needed if the result is non-negative
        	}

        	result.add(Math.abs(diff)); // Add to the result list
    	}

    	if (borrow != 0 && thisIsLarger) {
        	// Handle the final borrow, only if this.number was originally larger
        	int lastIndex = result.size() - 1;
        	int lastDigit = result.get(lastIndex) - borrow;
        	result.set(lastIndex, Math.abs(lastDigit));
    	}

    	// Removing any leading zeros from the result
    	for (int i = result.size() - 1; i > 0 && result.get(i) == 0; i--) {
        	result.remove(i);
    	}

    	// Update this LargeNumber with the result
    	this.number = result;
    	// Set the correct sign based on the original comparison
    	this.sign = thisIsLarger == (this.sign == other.sign) ? 1 : -1;
	}

// Helper method to compare the magnitude of two LargeNumber instances
// Code to be added here
	
 // Team 4 
    //method multiplies two LargeNumbers together
       public void multiply(LargeNumber other) {
       	int size1 = this.getSize();
       	int size2 = other.getSize();
       	
       	ArrayList<Integer> result = new ArrayList<>(); //stores result
       
       	for (int i = 0; i < size1 + size2; i++){
       		result.add(0);
       		}
       	
       	
       	//multiplies each number with corresponding number
       	for (int i = 0; i < size1; i++){
       		int carry = 0;
       		int n1 = this.number.get(i);
       	
       		for (int j = 0; j < size2; j++){
       			int n2 = other.number.get(j);
       			int
       			mul = n1 * n2 + result.get(i + j) + carry;
       			result.set(i + j, mul % 10); //updates digit
       			carry = mul / 10;
       			}
       	//stores remaining
       		if (carry > 0)
       			result.set(i + size2, result.get(i + size2) + carry);
       		}
       	
       	while (result.size() > 1 && result.get(result.size() - 1) == 0){
       		result.remove(result.size() - 1);
       		}
       	//updates to result
       	this.number = result;
       	this.sign *= other.sign;
       }
	
	// Team 5
	public void divide(LargeNumber other) {
	}
	
	// Team 7
	// Notes from Dr. LeClair: "I believe you are on Team 7 which got the percent function. 
    	// The percent function takes in another large number and says what percent it is. A.percent(B) return what percent B is of A"
	public LargeNumber percent(LargeNumber other) {
        LargeNumber hundredPercent = new LargeNumber(100);
        LargeNumber otherTimesOneHundred = other.multiply(hundredPercent);

        LargeNumber percentage = otherTimesOneHundred.divide(this);
        

        return percentage;
    }
	
	
	// Team 8
public void power(LargeNumber other) {                                                                                                                                                                                                                   LargeNumber largeNumber = new LargeNumber(10); // Replace with your actual LargeNumber
        LargeNumber other = new LargeNumber(5); // Replace with your actual LargeNumber

        // Multiply largeNumber by itself other.value times
        for (int i = 0; i < other.value; i++) {
            largeNumber.multiply(other);
        }

        System.out.println( largeNumber.value + 
                " to the power of " + other.value + "is  " + largeNumber.value);
    }
}
}
