package camo_calc;

import java.util.ArrayList;
import java.util.Stack;

public class Calculator {
	private static final char NUMBER_CHARS[] = {'-','.','0','1','2','3','4','5','6','7','8','9'};
	private static final char PRIORITY_ORDER[] = {'+','/','*','^'};
	private static final  char PARENTHESIS[] = {'(',')'};
	
	

	public static String[] postFix(String expression)
	{
		/*
		 * This function convert an infix argument to a postfix ordered array following the order of priority 
		 * stated priorityOrder[] attribute.
		 * the infix string should not have any spaces and it should be a operand infront of every non-first negative character. (if the numberChars[]
		 * and priorityOrder[] input should be
		 * 2+-4. The reason of this is that the negative character is treated as a number character, not as a operand.
		 * It also should be noted that if there are 2 operands of the same level of priority, the one at the left will be added to postFixArr first
		 * 
		 */
		ArrayList<String> postfixArr = new ArrayList<String>();	//Holds every term and operand of the input ordered in postfix
		StringBuilder tmpStr = new StringBuilder(50);			//Used to concat number characters
		Stack<Character> tempSt = new Stack<Character>();		// Stack used as a buffer for the operands
		boolean isCCharNum = false;								//
		char cChar;												//Current char of the input
		
		for(int i =0; i <expression.length() ; i++)			
		{	
			isCCharNum = false;
			cChar = expression.charAt(i);
			
			if(cChar == PARENTHESIS[0])
			{
				tempSt.push(cChar);
			}
			else
			{
				if(cChar != PARENTHESIS[1])
				{
					for(int x = 0 ; x < NUMBER_CHARS.length ; x++)	//Check if the cChar is a number
					{
						if(cChar == NUMBER_CHARS[x])
						{

							tmpStr.append(cChar);
							isCCharNum = true;

							break;
						}
						
					}
					if(!isCCharNum)
					{	
						if(tmpStr.length()!=0)
						{
							postfixArr.add(tmpStr.toString());
							tmpStr.setLength(0);
						}
						
						if(!tempSt.empty() && tempSt.peek() != PARENTHESIS[0])
						{
							int a,b;// a holds the "level of priority" of cChar and b holds the one of the last operand in the stack
							boolean poped;
							do
							{
								poped = false;
								a=-1;
								b=-1;
								for(int x =0; x < PRIORITY_ORDER.length ; x++) //finds the level of priority of cChar and the last operand in the stack.
								{
									if(cChar == PRIORITY_ORDER[x])
										a=x;
									if(tempSt.peek() == PRIORITY_ORDER[x])
										b=x;
								}
								if(a!=-1 && b!=-1 && a<=b)
								{
									poped = true;

									postfixArr.add(String.valueOf(tempSt.pop()));
									
								}
							}while(!tempSt.empty() && poped );
							tempSt.push((cChar));
						}
						else
						{
							tempSt.push(cChar);
						}
	
					}
					
				}
				else
				{
					if(tmpStr.length()>0)
					{
						postfixArr.add(tmpStr.toString());
						tmpStr.setLength(0);
					}

					while(tempSt.peek()!=PARENTHESIS[0])
						postfixArr.add(String.valueOf(tempSt.pop()));
					tempSt.pop();
				}
			}
			
			
		}
			if(tmpStr.length()>0)
			{
				postfixArr.add(tmpStr.toString());
				tmpStr.setLength(0);
			}
			
		while(tempSt.size()>0)
		{
			//postfix.append(tempSt.pop());
			postfixArr.add(String.valueOf(tempSt.pop()));
		}
		return  postfixArr.toArray(new String[postfixArr.size()]);
	}
	
	public static double solve(String input)
	{
		
		String[] postfixExp = postFix(input.trim());
		double result = 0;
		Stack<String> numSt = new Stack<String>();
		String cStr;
		boolean isCStrNum = false;
		for(int i = 0; i < postfixExp.length;i++)
		{
			isCStrNum = false;
			cStr = postfixExp[i];
		
			for(int x = 0 ; x < NUMBER_CHARS.length ; x++)	//Check if the cChar is a number
			{
				if(cStr.charAt(0) == NUMBER_CHARS[x])
				{
					isCStrNum=true;
					break;
				}
			}
			if(isCStrNum)
			{
				numSt.push(cStr);
			}
			else
			{
				switch(cStr)
				{
					case "+":
					{
						Double num2 =Double.parseDouble(numSt.pop()),num1=Double.parseDouble(numSt.pop());
						numSt.push(String.valueOf((num1+num2)));
						break;
					}
					case "*":
					{
						Double num2 =Double.parseDouble(numSt.pop()),num1=Double.parseDouble(numSt.pop());
						numSt.push(String.valueOf((num1*num2)));
						break;
					}
					case "/":
					{
						Double num2 =Double.parseDouble(numSt.pop()),num1=Double.parseDouble(numSt.pop());
						numSt.push(String.valueOf((num1/num2)));
						break;
					}
					case "^":
					{
						Double num2 =Double.parseDouble(numSt.pop()),num1=Double.parseDouble(numSt.pop());
						numSt.push(String.valueOf((Math.pow(num1, num2))));
						break;
					}
				}
			}
		}
		return Double.parseDouble(numSt.pop());
	}
	public String parseInput(String input)
	{
		//Remove all white space
		return input.replaceAll("\\s+","");
	}

}
