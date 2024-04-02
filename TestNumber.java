/************************************************************
 * C211 Fall 2023
 * Project Phase 2
 * Authors: Dana Vrajitoru
 * Class: LargeNumber
 * A test class for the class LargeNumber.
 ************************************************************/

package phase2;

import java.util.Scanner;

public class TestNumber {

    // A quick test for the methods so far
    public static void main(String[] args) {
        testConstructorInt();
		testCopy();
	    	testPower();
	    testMultiply();
	    
    }
	
	// Tests the constructor with an integer
    public static void testConstructorInt() {
        LargeNumber n = new LargeNumber(628451);
        System.out.println("The number is: " + n); // test toString
        System.out.println("The number has " + n.getSize() + " digits.");
        if (n.getSign() > 0)
            System.out.println("The number is positive.");
        else
            System.out.println("The number is negative.");
        System.out.println(n.toInt());
    }
	
	// Tests the copy constructor
    public static void testCopy() {
        LargeNumber n1, n2;
        n1 = new LargeNumber(46274);
        n2 = new LargeNumber(n1);
        n2.number.set(1, 3);
        n2.number.add(5);
        System.out.println("n1: " + n1 + " n2: " + n2);
    }
	
	/////////////////////////////////////////////////////////
	// Function stubs to be implemented by the teams
	
 // Team 4
    //tests multiply method
    public static void testMultiply() {
    	LargeNumber a = new LargeNumber(1256869);
    	LargeNumber b = new LargeNumber(-8934544);
    	
    	a.multiply(b);
    	System.out.println("The product is " + a + ".");
	}
	
	// Team 5
    public static void testDivide() {
	}
	
	// Team 7
    public static void testPercent() {
	}
	
	// Team 2
    public static void testSubtract() {
	LargeNumber num1 = new LargeNumber("5678");
        LargeNumber num2 = new LargeNumber("1234");

        num1.subtractExact(num2);

        System.out.println("Difference: " + num1)
	}
	
	// Team 6
	public static void testString() {
    }
	
	// Team 1
  class TestNumber {

    public static void main(String[] args) {
        testAdd();
    }

    public static void testAdd() {
        LargeNumber num1 = new LargeNumber("123456789");
        LargeNumber num2 = new LargeNumber("987654321");

        num1.add(num2);

        System.out.println("Sum: " + num1); // Output should be 1111111110
    }
}

	
    // Team 3
    public static void testCompare() {
	}
	// Team 8
    public static void testPower() {
        
        LargeNumber base = new LargeNumber(689521); 
        LargeNumber exponent = new LargeNumber(105);  
        
        base.power(exponent);

        System.out.println( largeNumber.value + 
                " to the power of " + other.value + "is  " + largeNumber.value);
    
    }
}
