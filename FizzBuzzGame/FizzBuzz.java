package fizzbuzz;

public class FizzBuzz {
  private final int fizzNumber;
  private final int buzzNumber;

  /**
   * Construct an object that can compute fizzbuzz values for a game of 
   * Fizz and Buzz.
   * 
   * @param fizzNumber an integer between 1 and 9
   * @param buzzNumber an integer between 1 and 9
   */
  public FizzBuzz(int fizzNumber, int buzzNumber) {
    this.fizzNumber = fizzNumber;
    this.buzzNumber = buzzNumber;
  }

  /**
   * Returns the fizzbuzz value for n. The rules are:
   * - if n is divisible by fizzNumber, or contains the digit fizzNumber, return "fizz" 
   * - if n is divisible by buzzNumber, or contains the digit buzzNumber, return "buzz"
   * - however, if both the above conditions are true, you must return "fizzbuzz"
   * - if none of the above conditions is true, return the number itself.
   *
   * <p>For example, getValue(1) returns "1".
   * 
   * @param n a positive integer
   * @return the fizzbuzz value, as a String
   */
  public String getValue(int n) {  
	String answer;
	
	if (n % fizzNumber == 0) {
		if (n % buzzNumber == 0) {
			answer = "fizzbuzz";
		}
		else if (hasBuzzDigit(n)) {
			answer = "fizzbuzz";
		}
		else {
		    answer = "fizz";
		}
	}
	
	else if (n % buzzNumber == 0) {
		if (hasFizzDigit(n)) {
			answer = "fizzbuzz";
		}
		else {
		    answer = "buzz";
		}
	}
	
	else if (hasFizzDigit(n)) {
		if (hasBuzzDigit(n)) {
			answer = "fizzbuzz";
		}
		else {
		   answer = "fizz";
		}
	}
	
	else if (hasBuzzDigit(n)) {
		answer = "buzz";
	}
	
	//else if (hasBuzzDigit(n)) {
		//answer = "buzz";
	//}

    else {
    	answer = Integer.toString(n); // return the number itself as a String
    }
    return answer;
  }

  /**
   * Returns an array of the fizzbuzz values from start to end, inclusive.
   * 
   * <p>For example, if the fizz number is 3 and buzz number is 4,
   * getValues(2,6) should return an array of Strings:
   * 
   * <p>{"2", "fizz", "buzz", "5", "fizz"}
   * 
   * @param start
   *            the number to start from; start > 0
   * @param end
   *            the number to end at; end >= start
   * @return the array of fizzbuzz values
   */
  public String[] getValues(int start, int end) {
	  int count = 0;
	  int arrayLength = (1 + (end - start));
	  String[] newArray = new String[arrayLength]; 
	  for (int i = start; i <= end; ++i) {
		  newArray[count] = getValue(i);
		  count = count + 1;
	  }
	  return newArray;
    //return new String[] {"1", "2", "fizz", "buzz"};
  }
  
  public boolean hasFizzDigit(int n) {
	  while (n > 0) {
		  int newNum = n % 10;
		  if (newNum == fizzNumber) {
			  return true;
		  }
		  else {
			  n = n / 10;
		  }
	  }
	  return false;
  }
  
  public boolean hasBuzzDigit(int n) {
	  while (n > 0) {
		  int newNum = n % 10;
		  if (newNum == buzzNumber) {
			  return true;
		  }
		  else {
			  n = n / 10;
		  }
	  }
	  return false;
  }
}
