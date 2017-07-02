package camo_calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[])
	{
		if(args.length == 0)
			cli("");
		else
			if(args[0]=="-g")
				gui();
			else
				cli(args[0]);
				
		
	}

	public static void cli(String input)
	{
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		Calculator calc = new Calculator();
		if(input == "")
			try
			{
				input = rd.readLine();
			}
			catch(IOException e)
			{
				System.out.println("Read issues");
			}

		System.out.println("\n"+calc.solve(input));
	}

	public static void gui()
	{

	}
}
