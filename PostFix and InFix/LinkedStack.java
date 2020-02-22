package stack;

/**
 * A {@link LinkedStack} is a generic stack that is implemented using
 * a Linked List structure to allow for unbounded size.
 */
public class LinkedStack<T> {

  // TODO: define class variables here
  private int stackCount = 0;
  private LLNode<T> top;

  /**
   * Remove and return the top element on this stack.
   * If stack is empty, return null (instead of throw exception)
   */
  public T pop() {
    // TODO
	T info;
	if (top == null || stackCount == 0) {
		return null;
	}
	info = top.info;
	top = top.link;
	stackCount = stackCount - 1; 
	
    return info;
  }

  /**
   * Return the top element of this stack (do not remove the top element).
   * If stack is empty, return null (instead of throw exception)
   */
  public T top() {
    // TODO
	if (top == null) {
		return null;
	}
    return top.info;
  }

  /**
   * Return true if the stack is empty and false otherwise.
   */
  public boolean isEmpty() {
    // TODO
	if (top == null || stackCount == 0) {
		return true;
	}
    return false;
  }

  /**
   * Return the number of elements in this stack.
   */
  public int size() {
    // TODO
    return stackCount;
  }

  /**
   * Pushes a new element to the top of this stack.
   */
  public void push(T elem) {
    // TODO
	LLNode<T> newNode = new LLNode<T>(elem);
	newNode.link = top;
	top = newNode;
	stackCount = stackCount + 1;
  }

}
