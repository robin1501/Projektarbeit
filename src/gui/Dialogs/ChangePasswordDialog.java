package gui.Dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;

import communication.InsertAndValidationChecks;
import communication.Master;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * Dialog zur Passwortänderung. Dieser Dialog wird als PopUp aus dem Panel
 * "Pesönliche Daten" aufgerufen.<br>
 * Es bietet die Möglichkeit das Passwort abzuändern.Hierzu werden 2 Textboxen
 * abgefragt.<br>
 * Das Passwort <b>muss </b>aus Buchstaben und Zahlen keine Sonderzeichen<br>
 * <br>
 * Nach den InsertAndValidation Checks wird das neue Passwort direkt
 * abgespeichert,<br>
 * das Hauptfenster geschlossen und das Loginfenster aufgerufen.
 * 
 * 
 * 
 */
public class ChangePasswordDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField oldPasswordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChangePasswordDialog dialog = new ChangePasswordDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChangePasswordDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		setTitle("Passwort \u00E4ndern");
		setBounds(100, 100, 347, 268);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblPasswortndern = new JLabel("Passwort \u00E4ndern");
		lblPasswortndern.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPasswortndern.setBounds(10, 11, 148, 27);
		contentPanel.add(lblPasswortndern);

		JLabel lblAltesPasswort = new JLabel("Altes Passwort");
		lblAltesPasswort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAltesPasswort.setBounds(10, 49, 104, 27);
		contentPanel.add(lblAltesPasswort);

		JLabel lblNeuesPasswort = new JLabel("Neues Passwort");
		lblNeuesPasswort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNeuesPasswort.setBounds(10, 102, 104, 27);
		contentPanel.add(lblNeuesPasswort);

		JLabel lblNeuesPasswort_1 = new JLabel("Erneut eingeben");
		lblNeuesPasswort_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNeuesPasswort_1.setBounds(10, 127, 104, 27);
		contentPanel.add(lblNeuesPasswort_1);

		oldPasswordField = new JPasswordField();
		oldPasswordField.setBounds(152, 49, 104, 20);
		contentPanel.add(oldPasswordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(152, 107, 104, 20);
		contentPanel.add(passwordField_1);

		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(152, 132, 104, 20);
		contentPanel.add(passwordField_2);

		final JLabel lblErrorlabel = new JLabel("");
		lblErrorlabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblErrorlabel.setBounds(10, 159, 311, 27);
		lblErrorlabel.setForeground(Color.RED);

		contentPanel.add(lblErrorlabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					/**
					 * Abfrage und Erstellung eines neuen Passwort.
					 * 					   
					 *  
					 * */
					public void actionPerformed(ActionEvent e) {
						char[] oldPswd = oldPasswordField.getPassword();
						char[] newPswd1 = passwordField_1.getPassword();
						char[] newPswd2 = passwordField_2.getPassword();
						String oldpswd, pswd1, pswd2;

						// überprüfung ob alle Felder ausgefüllt sind.
						if (oldPswd.length > 0 && newPswd1.length > 0
								&& newPswd2.length > 0) {

							// Konvertierung---------------------------
							oldpswd = stringConverter(oldPswd);
							pswd1 = stringConverter(newPswd1);
							pswd2 = stringConverter(newPswd2);

							// Regel Check ---------------------------
							if (!InsertAndValidationChecks.RegelCheck(newPswd1)) {

								lblErrorlabel
										.setText("Passwortregeln wurden nicht eingehalten!");
							} else {

								// pswd 1 == pswd 2------------------------

								if (pswd1.equals(pswd2)) {

									Master.ChangePassword(pswd1);
									dispose();

								} else {
									lblErrorlabel
											.setText("Die neuen Passwörter stimmen nicht überein.");
								}
							}

						} else {

							lblErrorlabel
									.setText("Bitte alle Felder ausfüllen");
						}

					}

					/**
					 * CharArrays die beim Einlesen der Passwortfelder entstehen
					 * werden in Strings umgewandelt.
					 * 
					 */
					private String stringConverter(char[] charArray) {
						String t = "";

						for (char i : charArray) {

							t = t + Character.toString(i);

						}
						return t;
					}

				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Abbrechen");
				cancelButton.addActionListener(new ActionListener() {
					/**
					 * Bei Abbruch wird das Fenster geschlossen.
					 */
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}

			JButton btnPasswortRichtlinien = new JButton("Passwort-Regeln");
			btnPasswortRichtlinien.addActionListener(new ActionListener() {
				/**
				 * Gibt in einem MessageDialog die Regeln für die
				 * Passworterstellung an.
				 */
				public void actionPerformed(ActionEvent e) {

					JOptionPane
							.showMessageDialog(
									null,
									" 1. Buchstaben und Ziffern erlaubt \n 2. Keine Leerzeichen \n 3. Keine Sonderzeichen \n 4. Mindestens 5 Zeichen  \n 5. Maximal 15 Zeichen",
									"Passwort-Regeln",
									JOptionPane.PLAIN_MESSAGE);
				}
			});
			buttonPane.add(btnPasswortRichtlinien);
		}
	}
}
