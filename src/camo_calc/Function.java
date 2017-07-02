package camo_calc;

import java.util.ArrayList;

public class Function {
	private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	private String funcExpr;
	private ArrayList<Character> vars ;
	
	public Function(String func)
	{
		setVars(new ArrayList<Character>());
		funcExpr=func;
		for(int c = 0 ; c <funcExpr.length(); c++)
		{
			char cChar = funcExpr.charAt(c); // current Character
			boolean isChar = false;
			for(int a = 0; a < ALPHABET.length && !isChar; a++)
			{
				if(cChar==ALPHABET[a])
				{
					isChar=true;
				}
			}
			boolean isInVars = false;
			if(isChar)
			{
				for(int v = 0; v < getVars().size() && !isInVars; v++)
				{
					if(cChar == getVars().get(v))
					{
						isInVars = true;
					}
				}
				if(!isInVars)
					getVars().add(cChar);
			}
			
		
		}
	}
	
	public double evaluate(Double[] vals) throws Exception
	{
		if(vals.length!= getVars().size())
		{
			throw new Exception();
		}
		String expr = funcExpr;
		for(int v = 0 ; v < getVars().size(); v ++ )
		{
			expr = expr.replaceAll(getVars().get(v).toString(),vals[v].toString());
		}
		
		
		return Calculator.solve(expr);
	}
	
	public static void main( String args[]) throws Exception
	{
		@SuppressWarnings("unused")
		Function f = new Function("2+4*f*z/z^2");
		System.out.print(f.evaluate(new Double[] {1.0,-1.0}));
	}

	public ArrayList<Character> getVars() {
		return vars;
	}

	public void setVars(ArrayList<Character> vars) {
		this.vars = vars;
	}
	

}
