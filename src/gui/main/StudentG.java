package gui.main;

import gui.panels.PersonalDataP;
import interfaces.IDisposeMe;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JTable;

import communication.Master;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.ScrollPaneConstants;

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
	private JTable jtNotenAnzeige;
	private JLabel lblAverage;
	private ArrayList<ArrayList<String>> myMarks = null;
	private String columnNames[] = { "Vorlesung", "Note" };
	private String rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3"},
            { "Row2-Column1", "Row2-Column2", "Row2-Column3"} };;
	private JPanel tablePanel;

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
	 * 
	 * @param course
	 * @param id
	 * @param firstname
	 * @param name
	 */
	public StudentG() {
		setTitle("Student - Hauptansicht");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 909, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		PersonalDataP persoPanel = new PersonalDataP();
		persoPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		persoPanel.setBounds(21, 29, 307, 403);
		contentPane.add(persoPanel);

		JPanel Calls = new JPanel();
		Calls.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Calls.setBounds(338, 29, 544, 99);
		contentPane.add(Calls);
		Calls.setLayout(null);

		JButton btnNotenAnzeigenaktualisieren = new JButton(
				"Noten anzeigen/aktualisieren");
		btnNotenAnzeigenaktualisieren.setHorizontalAlignment(SwingConstants.LEFT);
		btnNotenAnzeigenaktualisieren.addActionListener(new ActionListener() {
			/**
			 * Ruft die Noten des Studenten ab und ruft die fillTable Methode.
			 */
			public void actionPerformed(ActionEvent e) {
				myMarks = Master.getMyTwoDimensionalArrayList("getMyMarks",
						null, null);
				fillTable();
				// updateTable();

			}

		});
		btnNotenAnzeigenaktualisieren.setBounds(10, 22, 201, 23);
		Calls.add(btnNotenAnzeigenaktualisieren);

		JButton btnNotenschnittAnzeigen = new JButton("Notenschnitt anzeigen");
		btnNotenschnittAnzeigen.setHorizontalAlignment(SwingConstants.LEFT);
		btnNotenschnittAnzeigen.addActionListener(new ActionListener() {
			/**
			 * Holt den Schnitt des jeweiligen Studenten und zeigt ihn im Label
			 * "lblAverage" an. Einfacher Aufruf mit dem Master.
			 */
			public void actionPerformed(ActionEvent arg0) {

				double average = Master.getMyDouble("getMyTotalAverage", null,
						null);
				lblAverage.setText(String.valueOf(average));
			}
		});
		btnNotenschnittAnzeigen.setBounds(10, 56, 201, 23);
		Calls.add(btnNotenschnittAnzeigen);

		lblAverage = new JLabel("");
		lblAverage.setBounds(231, 63, 56, 16);
		Calls.add(lblAverage);

		JButton btnNewButton = new JButton("Noten schlechter 4,0 makieren");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * Prüft ob die Noten bereits geholt wurden oder nicht. <br>
			 * Wenn myMarks gefüllt ist, gibt er die ArrayList an die fillTable
			 * Methode weiter.
			 */
			public void actionPerformed(ActionEvent e) {

				// Wenn die Noten bereits abgeholt wurden nicht nochmal neu
				// holen.
				if (myMarks != null) {
					fillTable();
				} else {
					myMarks = Master.getMyTwoDimensionalArrayList("getMyMarks",
							null, null);
					fillTable();
					markWorserThanFour();

				}
			}

		});
		btnNewButton.setBounds(221, 22, 228, 23);
		Calls.add(btnNewButton);

		tablePanel = new JPanel();
		tablePanel.setBorder(new TitledBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Noten\u00FCbersicht",
				TitledBorder.LEADING, TitledBorder.TOP, null, null),
				"Noten\u00FCbersicht", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		tablePanel.setBounds(338, 144, 545, 292);
		contentPane.add(tablePanel);
		tablePanel.setLayout(null);
		createandAddTable(rowData,columnNames);

	}

	protected void markWorserThanFour() {

		// jtNotenAnzeige.isCellEditable(arg0, arg1)
	}

	private void createandAddTable(String[][] rowData2, String[] columnNames2) {
		
		jtNotenAnzeige = new JTable(rowData2, columnNames2) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// set table column uneditable
				return false;
			}

		};
		jtNotenAnzeige.setRowSelectionAllowed(false);
		jtNotenAnzeige.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(jtNotenAnzeige);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 21, 525, 261);
		tablePanel.add(scrollPane);

	}

	protected void fillTable() {		

		int column = 2;
		int row = myMarks.size();

		rowData = new String[row][column];

		for (int i = 0; i < row; i++) {
			for (int k = 0; k < column; k++) {

				rowData[i][k] = myMarks.get(i).get(k);
			}
		}

		createandAddTable(rowData, columnNames);

	}

	@Override
	public void disposeMeFromExtern() {
		this.dispose();

	}
}
