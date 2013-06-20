package gui.Dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;

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
		setBounds(100, 100, 291, 263);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(22, 11, 91, 25);
		contentPanel.add(lblName);
		
		JLabel lblVorname = new JLabel("Vorname");
		lblVorname.setBounds(22, 47, 91, 19);
		contentPanel.add(lblVorname);
		
		JLabel lblStudiengang = new JLabel("Studiengang");
		lblStudiengang.setBounds(22, 112, 91, 29);
		contentPanel.add(lblStudiengang);
		
		JLabel lblUserid = new JLabel("User-ID");
		lblUserid.setBounds(22, 87, 91, 14);
		contentPanel.add(lblUserid);
		
		JLabel lblRolle = new JLabel("Rolle");
		lblRolle.setBounds(22, 152, 91, 14);
		contentPanel.add(lblRolle);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(107, 152, 122, 20);
		comboBox_1.addItem("Student");
		comboBox_1.addItem("Dozent");
		comboBox_1.addItem("Professor");
		comboBox_1.addItem("Studiengangsleiter");
		
		contentPanel.add(comboBox_1);
		
		id = new JTextField();
		id.setBounds(107, 84, 122, 20);
		contentPanel.add(id);
		id.setColumns(10);
		
		vorname = new JTextField();
		vorname.setBounds(107, 46, 122, 20);
		contentPanel.add(vorname);
		vorname.setColumns(10);
		
		name = new JTextField();
		name.setBounds(105, 13, 124, 20);
		contentPanel.add(name);
		name.setColumns(10);
		
		JLabel lblStudiengangs = new JLabel(Master.getMyStrings("getCourse"));
		lblStudiengangs.setBounds(107, 119, 122, 14);
		contentPanel.add(lblStudiengangs);
		
		JLabel lblMessenger = new JLabel("");
		lblMessenger.setForeground(Color.RED);
		lblMessenger.setBounds(10, 177, 265, 14);
		contentPanel.add(lblMessenger);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Speichern");
				okButton.addActionListener(new ActionListener() {
					
					/**
					 * Prüft und speichert die Angaben der Felder.
					 * 
					 */
					public void actionPerformed(ActionEvent e) {
						
						
						
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
					public void actionPerformed(ActionEvent e) {
						
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
