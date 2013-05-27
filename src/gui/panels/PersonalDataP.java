package gui.panels;

import gui.Dialogs.ChangePasswordDialog;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;

import communication.Master;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Panel für die persönlichen Daten der jeweiligen Benutzer.<br>
 * Es wird von den Hauptseiten eingebunden.<br>
 * Das Panel wird direkt über den Master befülltt.<br>
 * Dies macht den Programmieraufwand also nur einmal nötig.<br>
 * 
 */
public class PersonalDataP extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PersonalDataP() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		
		
		JButton btnNewButton = new JButton("Passwort \u00E4ndern");
		btnNewButton.addActionListener(new ActionListener() {
		/**
		 * Öffnet den Dialog zur Passwortänderung.
		 * 
		 */
			
			public void actionPerformed(ActionEvent arg0) {
				ChangePasswordDialog myNewPasswort = new ChangePasswordDialog();
				myNewPasswort.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(10, 298, 157, 35);
		add(btnNewButton);
		
		JLabel lblPersnlicheDaten = new JLabel("Pers\u00F6nliche Daten");
		lblPersnlicheDaten.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblPersnlicheDaten.setBounds(63, 25, 175, 42);
		add(lblPersnlicheDaten);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(10, 88, 85, 20);
		add(lblName);
		
		JLabel lblVorname = new JLabel("Vorname");
		lblVorname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVorname.setBounds(10, 130, 85, 14);
		add(lblVorname);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId.setBounds(10, 166, 85, 14);
		add(lblId);
		
		JLabel lblStudiengang = new JLabel("Studiengang");
		lblStudiengang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudiengang.setBounds(10, 191, 85, 35);
		add(lblStudiengang);
		
		JLabel lblMyname = new JLabel(Master.getMyStrings("getName"));
		lblMyname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMyname.setBounds(169, 88, 143, 20);
		add(lblMyname);
		
		JLabel lblMyfirstname = new JLabel(Master.getMyStrings("getFirstname"));
		lblMyfirstname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMyfirstname.setBounds(169, 127, 143, 20);
		add(lblMyfirstname);
		
		JLabel lblMyid = new JLabel(Master.getMyStrings("getId"));
		lblMyid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMyid.setBounds(169, 163, 143, 20);
		add(lblMyid);
		
		JLabel lblMycourse = new JLabel(Master.getMyStrings("getCourse"));
		lblMycourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMycourse.setBounds(169, 197, 143, 23);
		add(lblMycourse);
		
		JButton btnNewButton_1 = new JButton("Abmelden");
		btnNewButton_1.addActionListener(new ActionListener() {
			/**
			 * Abmelde Vorgang wird eingeleitet über den Aufruf DisposeGUI() in der MasterKlasse.<br>
			 * Der Aufruf hat das Schließen der HauptGui zur Folge. <br>
			 * Danach wird ein Neues LogIn Fenster geöffnet. 
			 * 
			 */
			
			public void actionPerformed(ActionEvent arg0) {
				Master.disposeGUI();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBounds(10, 351, 157, 33);
		add(btnNewButton_1);
		
		JLabel lblFunktionen = new JLabel("Funktionen:");
		lblFunktionen.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFunktionen.setBounds(10, 264, 143, 14);
		add(lblFunktionen);

	}
}
