package gui.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

import communication.Master;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Hier werden die Studenten ausgewählt und einem Studiengang zugewiesen.<br>
 * Dies geschieht über ein JTable mit CheckBoxen.<br>
 * 
 * 
 */
public class AssignToG extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel tablePanel;
	private JTable jtAnzeige;
	private String columnNames[] = { "User-ID", "Checkbox" };
	private String rowData[][] = { { "", "" } };
	private JButton btnZuweisen;
	private JScrollPane scrollPane = null;
	private JComboBox cbLectures;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignToG frame = new AssignToG();
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
	public AssignToG() {
		setResizable(false);
		setTitle("Studenten zuweisen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 566, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Vorlesung");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(20, 22, 98, 29);
		contentPane.add(lblNewLabel);

		cbLectures = new JComboBox();
		cbLectures.setBounds(146, 22, 196, 27);
		ArrayList<String> allLetures = Master.getMyArrayList(
				"getAllCourseLectures", null, null);

		for (String i : allLetures) {
			cbLectures.addItem(i);
		}
		contentPane.add(cbLectures);

		tablePanel = new JPanel();
		tablePanel.setBorder(new TitledBorder(null, "Studenten ausw\u00E4hlen",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tablePanel.setBounds(10, 68, 530, 233);
		contentPane.add(tablePanel);
		tablePanel.setLayout(null);

		btnZuweisen = new JButton("Zuweisen");
		btnZuweisen.addActionListener(new ActionListener() {
			/**
			 * Gibt die ausgewählte Vorlesung und die, mit den Checkboxen makierten, <br>
			 * Studenten zum Speichern an die Master-Klasse weiter.<br>
			 */
			
			public void actionPerformed(ActionEvent arg0) {
				// Vorlesung übernehmen
				int selIndex = cbLectures.getSelectedIndex();
				String selLect = cbLectures.getItemAt(selIndex).toString();
				// Studenten übergeben
				ArrayList <String> selStudis = new ArrayList <String> ();
				
				// Save-Funktion aufrufen
				
				Master.saveNewAssignments(selLect,selStudis );
				
				
				
				
			}
		});
		btnZuweisen.setBounds(392, 22, 131, 29);
		contentPane.add(btnZuweisen);
		fillTable();
		createAndAddTable();

	}

	private void fillTable() {

		ArrayList<String> allStudentsOfCourse = Master.getMyArrayList(
				"getAllStudentsOfCourse", null, null);
		int column = 2;
		int row = allStudentsOfCourse.size();

		rowData = new String[row][column];

		for (int i = 0; i < row; i++) {

			rowData[i][0] = allStudentsOfCourse.get(i);	
			

		}
	}

	private void createAndAddTable() {
		jtAnzeige = new JTable(rowData, columnNames) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// set table column uneditable
				return true;
			}

		};

//		// -----------
		jtAnzeige.getColumn("Checkbox").setCellRenderer(
				jtAnzeige.getDefaultRenderer(Boolean.class));
		jtAnzeige.getColumn("Checkbox").setCellEditor(
				jtAnzeige.getDefaultEditor(Boolean.class));
//		// ---------------------

		jtAnzeige.setRowSelectionAllowed(false);
		jtAnzeige.getTableHeader().setReorderingAllowed(false);

		// / scrollPane removen weil dann tabelle !
		if (scrollPane != null) {
			tablePanel.remove(scrollPane);
		}

		scrollPane = new JScrollPane(jtAnzeige);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 21, 525, 261);
		tablePanel.add(scrollPane);

	}

}
