package gui.main;

import gui.panels.PersonalDataP;
import interfaces.IDisposeMe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
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
		setTitle("Student - Hauptansicht");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 909, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		PersonalDataP persoPanel = new PersonalDataP();
		persoPanel.setBounds(24, 29, 284, 421);
		contentPane.add(persoPanel);
		
		JPanel Calls = new JPanel();
		Calls.setBorder(new LineBorder(new Color(0, 0, 0)));
		Calls.setBounds(339, 29, 544, 104);
		contentPane.add(Calls);
	}

	@Override
	public void disposeMeFromExtern() {
		this.dispose();
		
	}
}
