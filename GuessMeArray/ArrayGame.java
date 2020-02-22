package guessme;

/**
 * An Array-based implementation of the Guess-A-Number game.
 */
public class ArrayGame {

  // stores the next number to guess
  private int guess;
  private int numGuesses;
  private boolean[] notEliminated;
  private int[] priorGuesses;
  private boolean isOver;

  // TODO: declare additional data members, such as arrays that store
  // prior guesses, eliminated candidates etc.

  // NOTE: only primitive type arrays are allowed, such as int[], boolean[] etc.
  // You MAY NOT use any Collection type (such as ArrayList) provided by Java.

  /********************************************************
   * NOTE: you are allowed to add new methods if necessary,
   * but DO NOT remove any provided method, otherwise your
   * code will fail the JUnit tests.
   * Also DO NOT create any new Java files, as they will
   * be ignored by the autograder!
   *******************************************************/

  // ArrayGame constructor method
  public ArrayGame() {
    guess = 1000; 
    numGuesses = 0;
    notEliminated = new boolean[9000]; 
    priorGuesses = new int[1000];
    for (int i=0; i<notEliminated.length; ++i) {
      notEliminated[i] = true;
    }
    for (int j=0; j<priorGuesses.length; ++j) {
      priorGuesses[j] = 0;
    }
    isOver = false;
  }

  /**
   *  Resets data members and game state so we can play again.
   */
  public void reset() {
    // TODO
    guess = 1000;
    numGuesses = 0;
    for (int i=0; i<notEliminated.length; ++i) {
      notEliminated[i] = true;
    }
    for (int j=0; j<priorGuesses.length; ++j) {
      priorGuesses[j] = 0;
    }
    isOver = false;
  } 

  /**
   *  Returns true if n is a prior guess; false otherwise.
   */
  public boolean isPriorGuess(int n) {
    // TODO
    boolean found = false;
    for (int i=0; i<priorGuesses.length-1; ++i) {
      if (n == priorGuesses[i]) {
        found = true;
      }
    }
    return found;

  }

  /**
   *  Returns the number of guesses so far.
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
  public static int numMatches(int a, int b) { // DO NOT remove the static qualifier
    // TODO
    int numMatches = 0;
    
    int [] aArray = new int[5];
    int [] bArray = new int[5];
    int count = 3;
    while (count > -1) {
      aArray[count] = a%10;
      bArray[count] = b%10;
      a = a/10;
      b = b/10;
      count = count - 1;
    }
    
    for (int i=0; i<=4; ++i) {
      if (aArray[i] == bArray[i]) {
        numMatches = numMatches + 1;
      }
    }
     
    return numMatches -1;
  }

  /**
   * Returns true if the game is over; false otherwise.
   * The game is over if the number has been correctly guessed
   * or if all candidates have been eliminated.
   */
  public boolean isOver() {
    return isOver;
  }

  /**
   *  Returns the guess number and adds it to the list of prior guesses.
   */
  public int getGuess() {
    // TODO: add guess to the list of prior guesses.
    for (int i=0; i<notEliminated.length; ++i) {
      if (notEliminated[i] == true) {
        guess = i + 1000;
        numGuesses = numGuesses + 1;
        priorGuesses[numGuesses] = guess;
        
        return guess;
      }
    }
    return 1000;
  }

  /**
   * Updates guess based on the number of matches of the previous guess.
   * If nmatches is 4, the previous guess is correct and the game is over.
   * Check project description for implementation details.
   * 
   * <p>Returns true if the update has no error; false if all candidates
   * have been eliminated (indicating a state of error);
   */
  public boolean updateGuess(int nmatches) {
    // TODO
    if (nmatches == 4) {
      isOver = true;
      return true;
    }
    boolean bool = false;
    for (int i=0; i<notEliminated.length; ++i) {
      if (numMatches(i+1000, priorGuesses[numGuesses]) != nmatches) {
        notEliminated[i] = false;
      }
      else {
        if (notEliminated[i] == true) {
          if (!bool) {
            bool = !bool;
          }
        }
      }
    }
    if (!bool) {
      isOver = true;
      return false;
    }
    
    return true;
  }

  /**
   * Returns the list of guesses so far as an integer array.
   * The size of the array must be the number of prior guesses.
   * Returns null if there has been no prior guess
   */
  public int[] priorGuesses() {
    // TODO
    int[] newArray;
    int count = 0;
    int index = 0;
    
    if (numGuesses == 0) {
      return null;
    }
    for (int j=0; j<priorGuesses.length; ++j) {
      if (priorGuesses[j] != 0) {
        count = count + 1;
      }
    }
    newArray = new int[count];
    for (int currNum : priorGuesses) {
      if (currNum != 0) {
        newArray[index] = currNum;
        index = index + 1;
      }
    }
    for (int z=0; z<newArray.length-1; ++z) {
      if (newArray[z] == 0) {
        newArray[z] = newArray[z+1];
      }
    }
    
    return newArray;
  }
}
 











