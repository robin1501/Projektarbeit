package gui.main;

import gui.Dialogs.NewLectureDialog;
import gui.Dialogs.NewUserDialog;
import gui.panels.PersonalDataP;
import interfaces.IDisposeMe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.UIManager;

import communication.Master;

/**
 * Die GUI für die Lehrkräfte, die sich je nach Berechtigung und Rolle aufbauen.<br>
 * Sie bietet die Möglichkeit sich Noten, Durchschnitte, gefährdete Studenten
 * anzeigen zu lassen.<br>
 * Desweitere ist es z.B. als "HeadofDepartment" möglich neue Nutzer und
 * Vorlesungen zu erstellen<br>
 * und den Vorlesungen die Studenten, die daran teilnehmen sollen zuzuweisen.
 * 
 * 
 * 
 */
public class LecturerG extends JFrame implements IDisposeMe {

	/**
	 * 
	 */
	private static final long serialVersionUID = 165886283281206589L;
	private JPanel contentPane;
	private JPanel pOptionsLec = new JPanel();
	private JPanel pOptionsHead = new JPanel();
	private JPanel pOptionsProf = new JPanel();
	private String columnNames[] = { "User-ID", "Vorlesung", "Note" };
	private String rowData[][] = { { "", "", "" } };;
	private JPanel panel;
	private JTable jtAnzeige;
	private JComboBox cbMyLectures;
	private JComboBox cbAllLecturesOfCourse;
	private JLabel lbllectureAverage;
	private JLabel lblAllLectureAverage;
	private JLabel lblAverageprof;
	private boolean changes = false;
	private JScrollPane scrollPane = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LecturerG frame = new LecturerG();
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
	public LecturerG() {
		setResizable(false);
		setTitle("Lehrk\u00F6rper-Hauptansicht");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1103, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		PersonalDataP persoPanel = new PersonalDataP();
		persoPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		persoPanel.setBounds(10, 11, 298, 417);
		contentPane.add(persoPanel);

		pOptionsLec.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Dozenten-Funktionen",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pOptionsLec.setBounds(318, 11, 304, 143);

		pOptionsLec.setLayout(null);

		cbMyLectures = new JComboBox();
		ArrayList<String> myLectures = Master.getMyArrayList("getMyLectures",
				null, null);

		for (String i : myLectures) {
			cbMyLectures.addItem(i);
		}

		cbMyLectures.setBounds(10, 21, 180, 23);
		pOptionsLec.add(cbMyLectures);

		JButton btnschnitt = new JButton("Vorlesungsschnitt");
		btnschnitt.addActionListener(new ActionListener() {

			/**
			 * Hier wird der Notenschnitt für die, in der Combobox angezeigte
			 * Vorlesung,Vorlesung angezeigt.
			 * 
			 */
			public void actionPerformed(ActionEvent arg0) {

				int selIndex = cbMyLectures.getSelectedIndex();
				String SelectedLecture = (String) cbMyLectures
						.getItemAt(selIndex);
				double average = Master.getMyDouble("getLectureAverage", null,
						SelectedLecture);

				lbllectureAverage.setText(String.valueOf(average));
			}
		});
		btnschnitt.setHorizontalAlignment(SwingConstants.LEFT);
		btnschnitt.setBounds(10, 45, 180, 23);
		pOptionsLec.add(btnschnitt);

		JButton btngesamtschnitt = new JButton("Schnitt aller Vorlesungen");
		btngesamtschnitt.addActionListener(new ActionListener() {
			/**
			 * Hier wird der Schnitt aller Vorlesungen des jeweiligen
			 * Lehrkörpers angezeigt
			 */
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> myLectures = Master.getMyArrayList(
						"getMyLectures", null, null);
				double average = Master.getMyDouble("getAllAverage",
						myLectures, null);

				lblAllLectureAverage.setText(String.valueOf(average));
			}
		});
		btngesamtschnitt.setHorizontalAlignment(SwingConstants.LEFT);
		btngesamtschnitt.setBounds(10, 75, 180, 23);
		pOptionsLec.add(btngesamtschnitt);

		lbllectureAverage = new JLabel("");
		lbllectureAverage.setBounds(200, 45, 72, 19);
		pOptionsLec.add(lbllectureAverage);

		lblAllLectureAverage = new JLabel("");
		lblAllLectureAverage.setBounds(200, 76, 72, 19);
		pOptionsLec.add(lblAllLectureAverage);

		JButton btnunasigned = new JButton("Ohne Note & Note > 4");
		btnunasigned.addActionListener(new ActionListener() {

			/**
			 * Hier werden alle Studenten in de Tabelle angeziegt zu denen noch
			 * keine Noten eingetragen wurden, /n oder bei denen die Noten
			 * schlechter als 4 sind.
			 */
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> myLectures = Master.getMyArrayList(
						"getMyLectures", null, null);
				ArrayList<ArrayList<String>> getAllFailedOrUnmarkedStudents = Master
						.getMyTwoDimensionalArrayList(
								"getAllFailedOrUnmarkedStudents", myLectures,
								null);
				fillTable(getAllFailedOrUnmarkedStudents);

			}

		});
		btnunasigned
				.setToolTipText("Zeigt alle Ihre Studenten die das Notenziel nicht erreicht haben oder f\u00FCr die noch keine Note abgegeben wurde.");
		btnunasigned.setHorizontalAlignment(SwingConstants.LEFT);
		btnunasigned.setBounds(10, 109, 180, 23);
		pOptionsLec.add(btnunasigned);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(318, 163, 544, 263);
		contentPane.add(panel);
		panel.setLayout(null);

		pOptionsProf.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Professoren-Funktionen",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pOptionsProf.setBounds(632, 11, 230, 143);

		pOptionsProf.setLayout(null);

		JButton btnAverageOfAllLecturesInMyCourse = new JButton(
				"Vorlesungsschnitt anzeigen");
		btnAverageOfAllLecturesInMyCourse
				.addActionListener(new ActionListener() {
					/**
					 * Zeigt den Notenschnitt der Vorlesung an, die ausgewählt
					 * wurde.
					 */
					public void actionPerformed(ActionEvent e) {

						int selIndex = cbAllLecturesOfCourse.getSelectedIndex();
						String SelectedLecture = (String) cbAllLecturesOfCourse
								.getItemAt(selIndex);
						double average = Master.getMyDouble(
								"getLectureAverage", null, SelectedLecture);

						lblAverageprof.setText(String.valueOf(average));

					}
				});
		btnAverageOfAllLecturesInMyCourse
				.setHorizontalAlignment(SwingConstants.LEFT);
		btnAverageOfAllLecturesInMyCourse.setBounds(10, 87, 210, 23);
		pOptionsProf.add(btnAverageOfAllLecturesInMyCourse);

		cbAllLecturesOfCourse = new JComboBox();
		fillsecondBox();

		cbAllLecturesOfCourse
				.setToolTipText("Alle Vorlesung Ihres Studiengangs");
		cbAllLecturesOfCourse.setBounds(10, 19, 210, 23);
		pOptionsProf.add(cbAllLecturesOfCourse);

		JButton btnschlechteralsVier = new JButton("Noten schlechter als 4");
		btnschlechteralsVier.addActionListener(new ActionListener() {

			/**
			 * Zeigt alle Noten im Studiengang an die schlechter als 4 sind.
			 */

			public void actionPerformed(ActionEvent e) {
				ArrayList<String> AllLecturesOfCourse = Master.getMyArrayList(
						"getAllCourseLectures", null, null);
				ArrayList<ArrayList<String>> AllFailedStudentsOfCourse = Master
						.getMyTwoDimensionalArrayList(
								"AllFailedStudentsOfCourse",
								AllLecturesOfCourse, null);
				fillTable(AllFailedStudentsOfCourse);

			}
		});
		btnschlechteralsVier.setHorizontalAlignment(SwingConstants.LEFT);
		btnschlechteralsVier
				.setToolTipText("Zeigt alle Studenten in Ihrem Studiengang an, die das Notenziel nicht erreicht haben");
		btnschlechteralsVier.setBounds(10, 53, 210, 23);
		pOptionsProf.add(btnschlechteralsVier);

		lblAverageprof = new JLabel("");
		lblAverageprof.setBounds(20, 113, 85, 19);
		pOptionsProf.add(lblAverageprof);

		pOptionsHead.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"),
				"Studiengangsleiter-Funktionen", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pOptionsHead.setBounds(880, 11, 205, 143);

		pOptionsHead.setLayout(null);

		JButton btnAddNew = new JButton("Neuer Nutzer");
		btnAddNew.setToolTipText("Neue Benutzer werden erstellt");
		btnAddNew.addActionListener(new ActionListener() {
			/**
			 * Öffnet den Dialog, um neue Benutzer anzulegen.
			 */
			public void actionPerformed(ActionEvent arg0) {

				NewUserDialog myNewUser = new NewUserDialog();
				myNewUser.setVisible(true);
			}
		});
		btnAddNew.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddNew.setBounds(10, 24, 157, 23);
		pOptionsHead.add(btnAddNew);

		JButton btnStudentenassign = new JButton("Zuweisungen");
		btnStudentenassign.addActionListener(new ActionListener() {
			/**
			 * Öffnet die Gui um Vorlesungen die Studenten, die daran Teilnehmen
			 * zuzuweisen.
			 */
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnStudentenassign
				.setToolTipText("Studenten,Dozenten und Vorlesungen zuweisen");
		btnStudentenassign.setHorizontalAlignment(SwingConstants.LEFT);
		btnStudentenassign.setEnabled(true);
		btnStudentenassign.setBounds(10, 89, 157, 23);
		pOptionsHead.add(btnStudentenassign);

		JButton btnNewButton_1 = new JButton("Anzeigen");
		btnNewButton_1.addActionListener(new ActionListener() {
			/**
			 * Die Tabelle wird mit den Werten der ausgewählten Vorlesung
			 * gefüllt. Die Vorlesung wird der Combobox entnommen.
			 */
			public void actionPerformed(ActionEvent arg0) {

				int selIndex = cbMyLectures.getSelectedIndex();
				String SelectedLecture = (String) cbMyLectures
						.getItemAt(selIndex);
				ArrayList<ArrayList<String>> getAllStudentsOfLecture = Master
						.getMyTwoDimensionalArrayList(
								"getAllStudentsOfLecture", null,
								SelectedLecture);
				fillTable(getAllStudentsOfLecture);

			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setBounds(200, 21, 89, 23);
		pOptionsLec.add(btnNewButton_1);

		JButton btnNeuVorlesung = new JButton("Neue Vorlesung");
		btnNeuVorlesung
				.setToolTipText("Neue Vorlesung erstellen und einer Lehrkraft zuweisen");
		btnNeuVorlesung.addActionListener(new ActionListener() {
			/**
			 * Öffnet den Dialog, um neue Vorlesungen zu erstellen und einem
			 * Dozenten zuzuweisen.
			 */
			public void actionPerformed(ActionEvent e) {
				NewLectureDialog myNewLecture = new NewLectureDialog();
				myNewLecture.setVisible(true);
			}
		});
		btnNeuVorlesung.setHorizontalAlignment(SwingConstants.LEFT);
		btnNeuVorlesung.setBounds(10, 55, 157, 23);
		pOptionsHead.add(btnNeuVorlesung);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "\u00C4nderungen",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(880, 163, 205, 122);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton btnSave = new JButton("Speichern");
		btnSave.addActionListener(new ActionListener() {
			/**
			 * Hier wird die Methode lookForChangesFirst aufgerufen,<br>
			 * Sie ermöglicht es zu überprüfen ob etwas geändert wurde. Ist dies
			 * der Fall, <br>
			 * werden die angezeigten Werte gespeichert.
			 */
			public void actionPerformed(ActionEvent e) {
				lookForChangesFirst();

			}
		});
		btnSave.setHorizontalAlignment(SwingConstants.LEFT);
		btnSave.setBounds(10, 21, 152, 23);
		panel_1.add(btnSave);

		JButton btn_cancel = new JButton("Verwerfen");
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changes = false;
				// Zurücksetzen auf Default werte
				String columnNames[] = { "User-ID", "Vorlesung", "Note" };
				String rowData[][] = { { "", "", "" } };
				;
				createandAddTable(rowData, columnNames);

			}
		});
		btn_cancel.setHorizontalAlignment(SwingConstants.LEFT);
		btn_cancel.setBounds(10, 55, 152, 23);
		panel_1.add(btn_cancel);

		createandAddTable(rowData, columnNames);
		whoAmI();
		

	}
/**
 * Füllt die Combobox für die Proffesoren seperat, da sonst Fehler beim Aufruf entstehen.
 * 
 */
	private void fillsecondBox() {
		
		if (!Master.getWhoAmI().equals("D")) {
			ArrayList<String> AllLecturesOfCourse = Master.getMyArrayList(
					"getAllCourseLectures", null, null);

			for (String i : AllLecturesOfCourse) {
				cbAllLecturesOfCourse.addItem(i);
			}
		}

	}

	/**
	 * Hier wird die 2 Dimensionale ArrayList in ein 2 Dimensionales Array
	 * umgewandelt und sie dann an die Methode <br>
	 * createandAddTable übergeben.
	 * 
	 * @param inserts
	 */
	protected void fillTable(ArrayList<ArrayList<String>> inserts) {
		int column = 3;
		int row = inserts.size();

		rowData = new String[row][column];

		for (int i = 0; i < row; i++) {
			for (int k = 0; k < column; k++) {

				rowData[i][k] = inserts.get(i).get(k);
			}
		}
		createandAddTable(rowData, columnNames);

	}

	/**
	 * In der Funktion createandAddTable wird die eigentliche Tabelle erstellt.<br>
	 * Sie wird erstellt und dann der GUI hinzugefügt.
	 * 
	 * 
	 * @param rowData2
	 * @param columnNames2
	 */
	private void createandAddTable(String[][] rowData2, String[] columnNames2) {

		jtAnzeige = new JTable(rowData2, columnNames2) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// set table column uneditable
				return false;
			}

		};
		jtAnzeige.setRowSelectionAllowed(false);
		jtAnzeige.setBounds(10, 11, 747, 241);

		jtAnzeige.setRowSelectionAllowed(false);
		jtAnzeige.getTableHeader().setReorderingAllowed(false);

		if (scrollPane != null) {
			panel.remove(scrollPane);
		}

		scrollPane = new JScrollPane(jtAnzeige);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 11, 525, 261);
		panel.add(scrollPane);

	}

	/**
	 * Hier wird überpüft mit welchen Rechten diese GUI gestartet wird. <br>
	 * Je nach Recht werden unterschiedliche Panels hinzugefügt.<br>
	 * 
	 */
	private void whoAmI() {
		String iAm = Master.getWhoAmI();

		if (iAm.equals("D")) {

			contentPane.add(pOptionsLec);

		} else {
			if (iAm.equals("P")) {

				contentPane.add(pOptionsLec);
				contentPane.add(pOptionsProf);

			}

			else {
				contentPane.add(pOptionsLec);
				contentPane.add(pOptionsProf);
				contentPane.add(pOptionsHead);
			}
		}

	}

	/**
	 * Um die GUI von externen GUI's zu disposen, wird hier die Funktion
	 * disposeMeFromExtern aufgerufen. <br>
	 * Sie ruft die Methode lookforChangesFirst auf und schließt erst danach das
	 * Fenster.<br>
	 * Diese Methode wird zum Beispiel von "ChangePasswordDialog" aufgerufen,
	 * nachdem das Passwort geändert wurde.
	 * 
	 */
	@Override
	public void disposeMeFromExtern() {
		lookForChangesFirst();
		this.dispose();

	}

	/**
	 * Hier wird überprüft ob Änderungen statt gefunden haben.<br>
	 * Ist dies der Fall wird hier gespeichert andernfalls nicht.
	 */
	@Override
	public void lookForChangesFirst() {

		if (changes == true) {
			getCurrentTableValues();

		}

	}

	/**
	 * Die Methode getCurrentTableValues ließt die aktuellen Werte der Tabelle <br>
	 * ein und gibt sie über den Master an die Save Klasse weiter.
	 * 
	 */
	private void getCurrentTableValues() {

		

	}
}
