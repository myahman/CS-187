package evaluator;

import parser.ArithParser;
import stack.LinkedStack;

public class InfixEvaluator extends Evaluator {

  private LinkedStack<String> operators = new LinkedStack<String>();
  private LinkedStack<Integer> operands  = new LinkedStack<Integer>();

  /** return stack object (for testing purpose). */
  public LinkedStack<String> getOperatorStack() { 
    return operators; 
  }
  
  public LinkedStack<Integer> getOperandStack() { 
    return operands;
  }


  /** This method performs one step of evaluation of a infix expression.
   *  The input is a token. Follow the infix evaluation algorithm
   *  to implement this method. If the expression is invalid, throw an
   *  exception with the corresponding exception message.
   */
  public void evaluate_step(String token) throws Exception {
    if (isOperand(token)) {
      // TODO: What do we do if the token is an operand?
      operands.push(Integer.parseInt(token));
    } else {
      /* TODO: What do we do if the token is an operator?
               If the expression is invalid, make sure you throw
               an exception with the correct message.

               You can call precedence(token) to get the precedence
               value of an operator. It's already defined in 
               the Evaluator class.
       */ 
      if (token.equals("(")) {
    	  operators.push(token);
      }
      else if (!isOperand(token) && (operators.isEmpty() == true || precedence(token) > precedence(operators.top()))) {
    	  operators.push(token);
      }
      else if (token.equals(")")) {
    	  boolean found = false;
    	  for (int i = operators.size(); i>0; i--) {
    		  if (operators.top().equals("(")) {
    			  found = true;
    			  break;
    		  }
    		  process_operator();
    	  }
    	  if (found !=true) {
    		  throw new Exception("missing (");
    	  }
    	  operators.pop();
    	  return;
      }
      else {
    	if (precedence(token) <= precedence(operators.top()) && !operators.isEmpty()) {
        	process_operator();
        }
    	operators.push(token);
      }
    }
  }
  
  /** This method evaluates an infix expression (defined by expr)
   *  and returns the evaluation result. It throws an Exception object
   *  if the infix expression is invalid.
   */
  public Integer evaluate(String expr) throws Exception {

    for (String token : ArithParser.parse(expr)) {
      evaluate_step(token);
    }

    /* TODO: what do we do after all tokens have been processed? */
    while (operators.size() !=0) {
    	process_operator();
    }
    // The operand stack should have exactly one operand after the evaluation is done
    if (operands.size() > 1) {
      throw new Exception("too many operands");
    } else if (operands.size() < 1) {
      throw new Exception("too few operands");
    }

    return operands.pop();
  }

  public static void main(String[] args) throws Exception {
    System.out.println(new InfixEvaluator().evaluate("1 - 4 * 10 - 5"));
  }
  
  public void process_operator() throws Exception {
	  String op = "";
	  Integer n1;
	  Integer n2;
	  Integer answer;
	  op = operators.pop();
	  if (op.equals(")")) {
		  return;
	  }
	  if (!op.equals("!") && !op.equals("+") && !op.equals("-") && !op.equals("/") && !op.equals("*") && !op.equals("(") && !op.equals(")")) {
	    	 throw new Exception("invalid operator");
	      }
	  if (op.equals("!")) {
		  n1 = operands.pop();
		  	if (n1 == null) {
		  		throw new Exception("too few operands");
		  	}
		  answer = n1*-1;
		  operands.push(answer);
	  }
	  else if (!op.equals("(") ) {
		  n1 = operands.pop();
		  n2 = operands.pop();
		  if (n1 == null || n2 == null) {
			  throw new Exception("too few operands");
		  }
		  else if (op.equals("+")) {
			  answer = n1+n2;
			  operands.push(answer);
		  }
		  else if (op.equals("-")) {
			  answer = n2-n1;
			  operands.push(answer);
		  }
		  else if (op.equals("*")) {
			  answer = n1*n2;
			  operands.push(answer);
		  }
		  else if (op.equals("/")) {
			  if (n1.equals(0)) {
				  throw new Exception("division by zero");
			  }
			  answer = n2/n1;
			  operands.push(answer);
		  }
	  }
	  //operands.push(answer);
  } 
}
