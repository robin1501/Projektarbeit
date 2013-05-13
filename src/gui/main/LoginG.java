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
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import communication.InsertAndValidationChecks;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Login GUI
 *  * 
 *
 */
public class LoginG extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

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
		setBounds(100, 100, 377, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnCsv = new JRadioButton("CSV");
		rdbtnCsv.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnCsv.setBounds(57, 128, 58, 23);
		contentPane.add(rdbtnCsv);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Datenbank");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnNewRadioButton.setBounds(157, 128, 119, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId.setBounds(57, 37, 90, 23);
		contentPane.add(lblId);
		
		JLabel lblPasswort = new JLabel("Passwort");
		lblPasswort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPasswort.setBounds(57, 79, 90, 29);
		contentPane.add(lblPasswort);
		
		JButton btnLogin = new JButton("Login");
		
		//btnLogin.addActionListener(new ActionListener() {			

//			public void actionPerformed(ActionEvent e) {
//				String pswd = passwortField.getPasswort();
//				
//				InsertAndValidationChecks.Login();
//			}
//		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.setBounds(75, 172, 196, 39);
		contentPane.add(btnLogin);
		
		textField = new JTextField();
		textField.setBounds(157, 40, 114, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		 passwordField = new JPasswordField();
		passwordField.setBounds(157, 85, 114, 20);
		contentPane.add(passwordField);
	}
}
