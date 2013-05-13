package gui.panels;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import communication.Master;

import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class PersonalDataP extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PersonalDataP() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		
		JButton btnNewButton = new JButton("Passwort \u00E4ndern");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(25, 277, 205, 42);
		add(btnNewButton);
		
		JLabel lblPersnlicheDaten = new JLabel("Pers\u00F6nliche Daten");
		lblPersnlicheDaten.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblPersnlicheDaten.setBounds(10, 11, 345, 42);
		add(lblPersnlicheDaten);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(10, 64, 85, 20);
		add(lblName);
		
		JLabel lblVorname = new JLabel("Vorname");
		lblVorname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVorname.setBounds(10, 108, 85, 14);
		add(lblVorname);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId.setBounds(10, 144, 85, 14);
		add(lblId);
		
		JLabel lblStudiengang = new JLabel("Studiengang");
		lblStudiengang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudiengang.setBounds(10, 169, 85, 35);
		add(lblStudiengang);
		
		JLabel lblRolle = new JLabel("Rolle");
		lblRolle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRolle.setBounds(10, 215, 85, 14);
		add(lblRolle);
		
		JLabel lblMyname = new JLabel("myName");
		lblMyname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMyname.setBounds(169, 69, 143, 20);
		add(lblMyname);
		
		JLabel lblMyfirstname = new JLabel("myFirstname");
		lblMyfirstname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMyfirstname.setBounds(169, 110, 143, 20);
		add(lblMyfirstname);
		
		JLabel lblMyid = new JLabel("myID");
		lblMyid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMyid.setBounds(169, 146, 143, 20);
		add(lblMyid);
		
		JLabel lblMycourse = new JLabel("myCourse");
		lblMycourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMycourse.setBounds(169, 181, 143, 23);
		add(lblMycourse);
		
		JLabel lblNewLabel = new JLabel("myRole");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(169, 217, 143, 23);
		add(lblNewLabel);

	}
}
