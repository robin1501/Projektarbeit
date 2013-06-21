package gui.Dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import communication.InsertAndValidationChecks;
import communication.Master;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.Color;

/**
 * Standard Dialog zur Bestätigung von Usereingaben
 * 
 * 
 */
public class NewUserDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField id;
	private JTextField vorname;
	private JTextField name;
	private JComboBox comboBox_1;
	private JTextField pswdfeld1;
	private JTextField pswdfeld2;
	private JLabel lblStudiengangs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewUserDialog dialog = new NewUserDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewUserDialog() {

		setTitle("Neuer Benutzer");
		setResizable(false);
		setBounds(100, 100, 434, 304);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(22, 11, 122, 25);
		contentPanel.add(lblName);

		JLabel lblVorname = new JLabel("Vorname");
		lblVorname.setBounds(22, 47, 122, 19);
		contentPanel.add(lblVorname);

		JLabel lblStudiengang = new JLabel("Studiengang");
		lblStudiengang.setBounds(22, 173, 82, 22);
		contentPanel.add(lblStudiengang);

		JLabel lblUserid = new JLabel("User-ID");
		lblUserid.setBounds(22, 87, 122, 14);
		contentPanel.add(lblUserid);

		JLabel lblRolle = new JLabel("Rolle");
		lblRolle.setBounds(22, 206, 72, 14);
		contentPanel.add(lblRolle);

		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(171, 203, 168, 20);
		comboBox_1.addItem("Student");
		comboBox_1.addItem("Dozent");
		comboBox_1.addItem("Professor");
		comboBox_1.addItem("Studiengangsleiter");

		contentPanel.add(comboBox_1);

		id = new JTextField();
		id.setBounds(171, 84, 168, 20);
		contentPanel.add(id);
		id.setColumns(10);

		vorname = new JTextField();
		vorname.setBounds(171, 46, 168, 20);
		contentPanel.add(vorname);
		vorname.setColumns(10);

		name = new JTextField();
		name.setBounds(171, 13, 168, 20);
		contentPanel.add(name);
		name.setColumns(10);

		lblStudiengangs = new JLabel(Master.getMyStrings("getCourse"));
		 lblStudiengangs.setBounds(171, 177, 168, 14);
		 contentPanel.add(lblStudiengangs);

		JLabel lblMessenger = new JLabel("");
		lblMessenger.setForeground(Color.RED);
		lblMessenger.setBounds(10, 177, 265, 14);
		contentPanel.add(lblMessenger);

		JLabel lblPasswort = new JLabel("Passwort");
		lblPasswort.setBounds(22, 112, 122, 19);
		contentPanel.add(lblPasswort);

		pswdfeld1 = new JTextField();
		pswdfeld1.setBounds(171, 115, 168, 20);
		contentPanel.add(pswdfeld1);
		pswdfeld1.setColumns(10);

		JLabel lblErneutEingeben = new JLabel("Erneut eingeben");
		lblErneutEingeben.setBounds(21, 142, 123, 14);
		contentPanel.add(lblErneutEingeben);

		pswdfeld2 = new JTextField();
		pswdfeld2.setBounds(171, 146, 168, 20);
		contentPanel.add(pswdfeld2);
		pswdfeld2.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Speichern");
				okButton.addActionListener(new ActionListener() {

					/**
					 * Prüft und speichert die Angaben der Felder.<br>
					 * Anhand dieser Reihenfolge <br>
					 *  Nachname;Vorname;Rolle;ID;Passwort;Studiengang;Studiengangsleiter;Vorlesung
					 * 
					 */
					public void actionPerformed(ActionEvent e) {
						// Nachname;Vorname;Rolle;ID;Passwort;Studiengang;Studiengangsleiter;Vorlesung
						ArrayList<String> data = new ArrayList<String>();
						data.add(name.getText());
						data.add(vorname.getText());
						int selIndex = comboBox_1.getSelectedIndex();
						data.add(comboBox_1.getItemAt(selIndex).toString());

						if (InsertAndValidationChecks.IdCheck(id.getText())) {
							
							data.add(id.getText());
							
						} else {
							JOptionPane
									.showMessageDialog(
											null,
											" ID entspricht nicht den Richtlinien.\n Bitte Unterstrich überprüfen",
											"ID-Regeln",
											JOptionPane.PLAIN_MESSAGE);
						}
						if (pswdfeld1.getText() != null
								&& !pswdfeld1.getText().equals("")
								&& pswdfeld1.getText().equals(
										pswdfeld2.getText())) {

							if (InsertAndValidationChecks.RegelCheck(pswdfeld1
									.getText().toCharArray())) {
								
								data.add(pswdfeld1.getText());
								
							} else {
								JOptionPane
										.showMessageDialog(
												null,
												" Passwort entspricht nicht den Regeln. \n Bitte Regeln überprüfen",
												"Passwort-Regeln",
												JOptionPane.PLAIN_MESSAGE);

							}

						} else {
							JOptionPane
									.showMessageDialog(
											null,
											" Passwort ist entweder leer \n oder ist nicht Identisch. \n Bitte Überprüfen",
											"Passwort-Regeln",
											JOptionPane.PLAIN_MESSAGE);
						}
						
						data.add(Master.getMyStrings("getCourse"));
						if(comboBox_1.getItemAt(selIndex).toString().equals("Studiengangsleiter")){
							
							data.add("true");
						}else{
							
							data.add("false");
						}		
						
						// vorlesung wird nicht gesetzt
						data.add("");						
						Master.addUser(data);
						
						JOptionPane
						.showMessageDialog(
								null,
								" Nutzer wurde angelegt.",
								"Anlegen erfolgreich",
								JOptionPane.PLAIN_MESSAGE);
						
						setVisible(false);

					}
				});
				okButton.setHorizontalAlignment(SwingConstants.LEFT);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Abbrechen");
				cancelButton.addActionListener(new ActionListener() {

					/**
					 * Schließt das Fenster
					 */
					public void actionPerformed(ActionEvent e) {

						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
			}

			JButton btnRegel = new JButton("Passwortregeln");
			btnRegel.addActionListener(new ActionListener() {
				/**
				 * Hier werden die Passwortregeln aufgerufen.
				 */
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane
							.showMessageDialog(
									null,
									" 1. Buchstaben und Ziffern erlaubt \n 2. Keine Leerzeichen \n 3. Keine Sonderzeichen \n 4. Mindestens 5 Zeichen  \n 5. Maximal 15 Zeichen",
									"Passwort-Regeln",
									JOptionPane.PLAIN_MESSAGE);

				}
			});
			btnRegel.setHorizontalAlignment(SwingConstants.RIGHT);
			buttonPane.add(btnRegel);
		}
	}
}
