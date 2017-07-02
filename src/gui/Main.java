package gui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import camo_calc.Calculator;
import camo_calc.Function;
import methods.Euler;
//import methods
import methods.Kutta;

public class Main
{ 
	private static final String INSERT_DIFF_EQN_MSG = "Inserte la ecuacion Diferencial:";
	private static final String INPUT_ERROR_MSG = "Hubo un problema con el input, porfavor pruebe de nuevo.";
	public static void main(String args[]) {
	
		
		MenuPrincipal();
		

		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		try
		{
			String in= JOptionPane.showInputDialog(null,INSERT_DIFF_EQN_MSG);
			Function f = new Function(in);
			Euler e = new Euler();
			e.setDydx(f);
			Double arr[][] = e.solve(new Double[]{2.0,7.0}, 0.1, 3);
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
	
	
	
	static void MenuPrincipal(){
		int opcion = 0;
		do{
			opcion=0;
			try{
				opcion=Integer.parseInt(JOptionPane.showInputDialog(null, "1.Pagina de presentación.\n"
						+ "2. Metodo Euler. \n"
						+ "3. Metodo Runge-Kutta. \n "
						+ "4. Salir del Programa." ));
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"Por favor seleccione una de las opciones en pantalla.");
			}
			switch(opcion)
			{
			case 1: PaginaDePresentacion();break; //
			case 2: MetodoEuler();break;
			case 3: MetodoRungeKutta();break;
			case 4: System.exit(0);
			default: JOptionPane.showMessageDialog(null, "Ha escogido una opcion invalida.");
			}
		}while(opcion!=4);
	}
	
	static void input(){
		String ecuacion;
		double x0,y0,h;
		boolean error = false;
		
		do
		{
		
		try 
		{
		ecuacion=JOptionPane.showInputDialog(null, "Ingrese la ecuación diferencial de primer orden.");
		error=false;
		}
		catch(Exception e)
		{ //No se como se va introducir esa ecuación o.o i mean, si tiene mas de una variable
		JOptionPane.showMessageDialog(null, "Error en el formato");
		error=true;
		}
		
		try 
		{	
		x0=Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el valor de x0"));
		error=false;
		}
		catch(Exception e){ 
		JOptionPane.showMessageDialog(null, "Error, no introdujo un numero");
		error=true;
		}
		
		
		try
		{
		y0=Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el valor de y0"));
		error=false;
		}
		catch(Exception e)
		{ 
		JOptionPane.showMessageDialog(null, "Error, no introdujo un numero");
		error=true;
		}
		
		try
		{
		h=Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el valor de h"));
		error=false;
		}
		catch(Exception e)
		{ 
		JOptionPane.showMessageDialog(null, "Error, no introdujo un numero");
		error=true;
		}
		
		}while(error==true);
}
	
	static void MetodoRungeKutta(){
		input();
		
	}
	
	static void MetodoEuler(){
		input();
	}
	
	static void PaginaDePresentacion(){
		ImageIcon presentacion = new ImageIcon(Main.class.getClassLoader().getResource("presentacion.jpg"));
		JOptionPane.showMessageDialog(null, presentacion);
	}
	
	
	
}
