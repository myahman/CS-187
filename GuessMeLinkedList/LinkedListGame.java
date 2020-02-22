package guessme;

/**
 * A LinkedList-based implementation of the Guess-A-Number game.
 */
public class LinkedListGame {

  // TODO: declare data members as necessary
  private boolean isOver;
  private int numGuesses;
  private int guess;
  private LLIntegerNode eliminatedHead;
  private LLIntegerNode eliminatedTail;
  private LLIntegerNode priorGuessHead;
  private LLIntegerNode priorGuessTail;
  


   /********************************************************
   * NOTE: for this project you must use linked lists
   * implemented by yourself. You are NOT ALLOWED to use
   * Java arrays of any type, or any class in the java.util
   * package (such as ArrayList).
   *******************************************************/

  /********************************************************
   * NOTE: you are allowed to add new methods if necessary,
   * but DO NOT remove any provided method, and do NOT add
   * new files (as they will be ignored by the autograder).
   *******************************************************/

  // LinkedListGame constructor method
  public LinkedListGame() {
    // TODO
	isOver = false; 
	numGuesses = 0;
	eliminatedHead = new LLIntegerNode(1000);
	eliminatedTail = eliminatedHead;
	priorGuessHead = null;
	priorGuessTail = priorGuessHead;
	for (int i=1001; i<10000; ++i) {
		insertEliminated(i);
	}

  }

  /** Resets data members and game state so we can play again.
   * 
   */
  public void reset() {
    // TODO
	isOver = false;
	numGuesses = 0;
	priorGuessHead = null;
	priorGuessTail = priorGuessHead;
	eliminatedHead.setInfo(1000);
	eliminatedTail = eliminatedHead;
	for (int i=1000; i<10000; ++i) {
		insertEliminated(i);
	}
  }

  /** Returns true if n is a prior guess; false otherwise.
   * 
   */
  public boolean isPriorGuess(int n) {
    // TODO
	LLIntegerNode node = priorGuessHead;
	while (node != null) {
		if (node.getInfo() == n) {
			return true;
		}
		node = node.getLink();
	}
    return false;
  }

  /** Returns the number of guesses so far.
   * 
   */
  public int numGuesses() {
    // TODO
    return numGuesses;
  }

  /**
   * Returns the number of matches between integers a and b.
   * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
   * The return value must be between 0 and 4.
   * 
   * <p>A match is the same digit at the same location. For example:
   *   1234 and 4321 have 0 match;
   *   1234 and 1114 have 2 matches (1 and 4);
   *   1000 and 9000 have 3 matches (three 0's).
   */
  public static int numMatches(int a, int b) {
    // TODO
	int one;
	int two;
	int numMatches = 0;
	
	while (a > 0) {
		one = a%10;
		two = b%10;
		if (one == two) {
			numMatches = numMatches + 1;
		}
		a = a/10;
		b = b/10;
	} 
	return numMatches;
  }

  /**
   * Returns true if the game is over; false otherwise.
   * The game is over if the number has been correctly guessed
   * or if no candidate is left.
   */
  public boolean isOver() {
    // TODO
    return isOver;
  }
 
  /**
   * Returns the guess number and adds it to the list of prior guesses.
   * The insertion should occur at the end of the prior guesses list,
   * so that the order of the nodes follow the order of prior guesses.
   */ 
  public int getGuess() {
    // TODO: add guess to the list of prior guesses.
	LLIntegerNode node = eliminatedHead;
	while (node != null) {
		if (node.getInfo() != 0) {
			guess = node.getInfo();
			insertPriorGuess(guess);
			numGuesses = numGuesses + 1;
			return guess;
		}
		node = node.getLink();
	}
	//numGuesses = numGuesses + 1;
    return guess;
  }

  /**
   * Updates guess based on the number of matches of the previous guess.
   * If nmatches is 4, the previous guess is correct and the game is over.
   * Check project description for implementation details.
   * 
   * <p>Returns true if the update has no error; false if no candidate 
   * is left (indicating a state of error);
   */
  public boolean updateGuess(int nmatches) {
    // TODO
	if (nmatches == 4) {
		isOver = true;
		return isOver;
	}
	boolean bool = false;
	LLIntegerNode node = eliminatedHead;
	while (node != null) {
		if (numMatches(node.getInfo(), guess) != nmatches) {
			node.setInfo(0);
		}
		else {
			if (node.getInfo() != 0) {
				if (!bool) {
					bool = !bool;
				}
			}
		}
		node = node.getLink();
	}
	if (!bool) {
		isOver = true;
		return false;
	}
	
    return true;
  }

  /**
   *  Returns the head of the prior guesses list.
   *  Returns null if there hasn't been any prior guess
   */
  public LLIntegerNode priorGuesses() {
    // TODO
    return priorGuessHead;
  }

  /**
   * Returns the list of prior guesses as a String. For example,
   * if the prior guesses are 1000, 2111, 3222, in that order,
   * the returned string should be "1000, 2111, 3222", in the same order,
   * with every two numbers separated by a comma and space, except the
   * last number (which should not be followed by either comma or space).
   *
   * <p>Returns an empty string if here hasn't been any prior guess
   */
  public String priorGuessesString() {
    // TODO
	String str = "";
	LLIntegerNode node = priorGuessHead;
	if (node == null) {
		str = "";
	}
	while (node != null) {
		str = str + node.getInfo();
		if (node.getLink() != null) {
			str = str + ", ";
		}
		node = node.getLink();
	}
	
    return str;
  }
  
  public void insertEliminated(int element) {
	  LLIntegerNode newNode = new LLIntegerNode(element);
	  if (eliminatedTail == null) {
		  eliminatedHead = newNode;
	  }
	  else {
		  eliminatedTail.setLink(newNode);
	  }
	  eliminatedTail = newNode;
  }

  public void insertPriorGuess(int element) {
	  LLIntegerNode newNode = new LLIntegerNode(element);
	  if (priorGuessTail == null) {
		  priorGuessHead = newNode;
	  }
	  else {
		  priorGuessTail.setLink(newNode);
	  }
	  priorGuessTail = newNode;
  }
}
