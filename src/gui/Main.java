package gui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import camo_calc.Calculator;
import camo_calc.Function;
import methods.Euler;
//import methods

public class Main
{ 
	private static final String INSERT_DIFF_EQN_MSG = "Inserte la ecuacion Diferencial:";
	private static final String INPUT_ERROR_MSG = "Hubo un problema con el input, porfavor pruebe de nuevo.";
	public static void main(String args[])
	{

		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		try
		{
			String in= JOptionPane.showInputDialog(null,INSERT_DIFF_EQN_MSG);
			Function f = new Function(in);
			Euler e = new Euler();
			e.setDydx(f);
			Double arr[][] = e.solve(new Double[]{2.0,7.0}, 0.001, 3);
					for (int i = 0; i < arr.length; i++) {
					    for (int j = 0; j < arr[i].length; j++) {
					        System.out.print(arr[i][j] + " ");
					    }
					    System.out.println();
					}
		}
		catch( Exception e )
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,INPUT_ERROR_MSG);
		}
		
	}
}
