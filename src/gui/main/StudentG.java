package gui.main;

import interfaces.IDisposeMe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/**
 * Hauptansicht für den Studenten.<br>
 * Die Rechte der Rolle sind den implementierten Interfaces zu entnehmen.<br>
 * Sie besteht aus den persönlichen Daten, der Ansicht seiner Noten und <br>
 * Makierung der Noten, in denen das Ziel von 4,0 nicht erreicht wurde.
 *
 *
 */
public class StudentG extends JFrame implements IDisposeMe {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentG frame = new StudentG();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param course 
	 * @param id 
	 * @param firstname 
	 * @param name 
	 */
	public StudentG() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	@Override
	public void disposeMeFromExtern() {
		this.dispose();
		
	}

	

}
