package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class matrixResult extends JFrame {

	private JPanel contentPane;
	private JTable a;
	private JTable b;
	private DefaultTableModel model1;
	private DefaultTableModel model2 ;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int i = Integer.parseInt(JOptionPane.showInputDialog(null, "matris"));
					matrixInput frame = new matrixInput(i);
					frame.setVisible(true);
					frame.pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public matrixResult(Object[][] i, String[] name) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 169);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		model1 = new DefaultTableModel(i,name) ;
		panel_1.setLayout(new BorderLayout(0, 0));
		a = new JTable(model1);
		a.setColumnSelectionAllowed(true);
		a.setCellSelectionEnabled(true);
		JScrollPane scrollPane_3 = new JScrollPane(a);
		panel_1.add(scrollPane_3);
		
		
	
		
		
		
	
	
	}
	
	public void	showme()
	{
		JOptionPane.showOptionDialog(null, contentPane, "Sirena",
	            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
	            new String[] { "Aceptar", "Cancelar" }, "Aceptar");
	}

	public DefaultTableModel getModel1() {
		return model1;
	}

	public void setModel1(DefaultTableModel model1) {
		this.model1 = model1;
	}
	public JPanel getContentPanel()
	{
		return contentPane;
	}
}

