package gui.Dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import communication.Master;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewLectureDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox cbStaff;
	private JTextField txtFieldLecture;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewLectureDialog dialog = new NewLectureDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewLectureDialog() {
		setTitle("Neue Vorlesung");
		setResizable(false);
		setBounds(100, 100, 345, 186);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 444, 1);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JButton okButton = new JButton("Zuweisen");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});

		JLabel lblNewLabel = new JLabel("Vorlesung");
		lblNewLabel.setBounds(10, 24, 104, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Dozent zuweisen");
		lblNewLabel_1.setBounds(10, 60, 104, 14);
		getContentPane().add(lblNewLabel_1);
		
	
		

		cbStaff = new JComboBox();
		cbStaff.setBounds(136, 57, 165, 20);
		ArrayList <String> staff = Master.getMyArrayList("getAllStaffOfCourse", null, null);
		
		for(String i : staff){
			cbStaff.addItem(i); 
		}
		getContentPane().add(cbStaff);

		JButton btnZuweisen = new JButton("Zuweisen");
		btnZuweisen.setBounds(10, 95, 104, 23);
		btnZuweisen.addActionListener(new ActionListener() {
			/**
			 * Gibt die Daten an den Master weiter. <br>
			 * Die Daten bekommt man aus den beiden Comboboxen.
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				
				int selIndeStaff =	cbStaff.getSelectedIndex();	
				Master.addLectureToStaff(txtFieldLecture.getText(), cbStaff.getItemAt(selIndeStaff).toString());
				setVisible(false);
			}
		});
		getContentPane().add(btnZuweisen);

		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setBounds(10, 124, 104, 23);
		btnAbbrechen.addActionListener(new ActionListener() {
			/**
			 * Schlie�t den Dialog.
			 */
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
			}
		});
		getContentPane().add(btnAbbrechen);
		
		txtFieldLecture = new JTextField();
		txtFieldLecture.setBounds(136, 21, 165, 20);
		getContentPane().add(txtFieldLecture);
		txtFieldLecture.setColumns(10);
	}
}
