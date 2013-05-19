package gui.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import communication.InsertAndValidationChecks;
import communication.Master;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;

/**
 * Startfenster zur Eingabe der Login-Daten. Nach den InserAndValidationChecks
 * wird der dynamische User im Master gesetz <br>
 * und die GUI mit den entsprechenden Rechten aufgebaut. Nach einer
 * Passwortänderung wird wieder das Loginfenster aufgerufen,<br>
 * sodass ein erneutes Login möglich ist.
 * 
 */
public class LoginG extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5207612747990419028L;
	private JPanel contentPane;
	public JTextField textField;
	public JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginG frame = new LoginG();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginG() {

		setTitle("Login zur Studentenverwaltung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 331, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(36, 49, 90, 23);
		contentPane.add(lblId);

		JLabel lblPasswort = new JLabel("Passwort");
		lblPasswort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPasswort.setBounds(36, 81, 80, 20);
		contentPane.add(lblPasswort);

		JButton btnLogin = new JButton("Login");
		btnLogin.setToolTipText("Click to Login");

		btnLogin.addActionListener(new ActionListener() {

			/**
			 * Der ActionListener der auf dem Login-Button liegt, ließt das
			 * Textfeld und das Passwortfeld aus <br>
			 * und gibt sie zurweiteren verarbeitung in den Master weiter.<br>
			 * <br>
			 * Bei positiven Login wird das Loginfenster geschlossen. <br>
			 * Ist das nicht der Fall wird das Passwortfeld gelehrt, sodass eine
			 * erneute Eingabe möglich ist.<br>
			 * <br>
			 * Auf mögliche fehlerhafte Eingaben wie Ziffern in der ID, falsches
			 * Passwort, Leer- und Sonderzeichen <br>
			 * wird in den InsertAndValidation-Klasse, sowie der Load-Klasse
			 * gesondert hingewiesen
			 */

			public void actionPerformed(ActionEvent arg0) {

				char[] pswd = passwordField.getPassword();
				String user = textField.getText();

				if (Master.Login(pswd, user)) {

					dispose();

				} else {

					passwordField.setText("");
				}

			}
		});

		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.setBounds(36, 127, 80, 22);
		contentPane.add(btnLogin);

		textField = new JTextField();
		textField.setBounds(148, 52, 114, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(149, 82, 112, 20);
		contentPane.add(passwordField);

		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLogin.setBounds(39, 14, 66, 21);
		contentPane.add(lblLogin);
		setLocationRelativeTo(null);
	}
}
