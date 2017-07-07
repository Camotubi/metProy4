package gui;


import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import camo_calc.Function;
import methods.Euler;
//import methods
import methods.Kutta;

public class Main
{ 
	private static final String INSERT_DIFF_EQN_MSG = "Inserte la ecuacion Diferencial:";
	private static final String INPUT_ERROR_MSG = "Hubo un problema con el input, porfavor pruebe de nuevo.";
	private static final String[] EULER_COLNAMES = {"Iter","x0","y0","dydx"};
	private static final String[] KUTTA_COLNAMES = {"Iter","x0","y0","K","K1","K2","K3","K4"};
	public static void main(String args[]) {
	
		
		MenuPrincipal();

	}
	
	
	
	static void MenuPrincipal(){
		int opcion = 0;
		do{
			opcion=0;
			try{
				opcion=Integer.parseInt(JOptionPane.showInputDialog(null, "1.Pagina de presentaci�n.\n"
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
	
	static String input( String msg){
		String ecuacion;
		
		boolean error = false;
		
		do
		{
			try
			{
				return(JOptionPane.showInputDialog(null, msg));
			}
			catch(Exception e)
			{ //No se como se va introducir esa ecuaci�n o.o i mean, si tiene mas de una variable
				JOptionPane.showMessageDialog(null, "Hubo un problema en el input, intentelo denuevo");
				error=true;
			}
			
		}while(error);
		return("");
}
	
	static void MetodoRungeKutta(){
		String ecuacion;
		double x0,xf,y0,h;
		ecuacion=input("Inserte la ecuacion Diferencial");
		x0=Double.parseDouble(input("Inserte el x0:"));
		y0=Double.parseDouble(input("Inserte el y0:"));
		h=Double.parseDouble(input("Inserte el h:"));
		xf=Double.parseDouble(input("Inserte la x final:"));
		Kutta kutta= new Kutta();
		kutta.setDydx(new Function(ecuacion));

		try {
			new matrixResult(kutta.solve(new Double[] {x0, y0},h, xf),KUTTA_COLNAMES).showme();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hubo un problema con el metodo");
			e.printStackTrace();
		}
		
	}
	
	static void MetodoEuler(){
		String ecuacion;
		double x0,xf,y0,h;
		ecuacion=input("Inserte la ecuacion Diferencial");
		x0=Double.parseDouble(input("Inserte el x0:"));
		y0=Double.parseDouble(input("Inserte el y0:"));
		h=Double.parseDouble(input("Inserte el h:"));
		xf=Double.parseDouble(input("Inserte la x final:"));
		Euler euler = new Euler();
		euler.setDydx(new Function(ecuacion));
		try {
			new matrixResult(euler.solve(new Double[] {x0, y0},h, xf),EULER_COLNAMES).showme();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hubo un problema con el metodo");
			e.printStackTrace();
		}
	}
	
	static void PaginaDePresentacion(){

		ImageIcon presentacion = new ImageIcon(
				new ImageIcon(
						Main.class.getClassLoader().getResource("presentacion.jpg")).getImage().getScaledInstance(450,650, Image.SCALE_DEFAULT));
		JOptionPane.showMessageDialog(null, presentacion);
	}
	
	
	
}
