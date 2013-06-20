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
import javax.swing.UIManager;

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
	
	JScrollPane scrollPane = null;

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

		JComboBox cbMyLectures = new JComboBox();
		cbMyLectures.setBounds(10, 21, 180, 23);
		pOptionsLec.add(cbMyLectures);

		JButton btnschnitt = new JButton("Vorlesungsschnitt");
		btnschnitt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnschnitt.setHorizontalAlignment(SwingConstants.LEFT);
		btnschnitt.setBounds(10, 45, 180, 23);
		pOptionsLec.add(btnschnitt);

		JButton btngesamtschnitt = new JButton("Schnitt aller Vorlesungen");
		btngesamtschnitt.setHorizontalAlignment(SwingConstants.LEFT);
		btngesamtschnitt.setBounds(10, 75, 180, 23);
		pOptionsLec.add(btngesamtschnitt);

		JLabel lbllectureAverage = new JLabel("");
		lbllectureAverage.setBounds(200, 45, 72, 19);
		pOptionsLec.add(lbllectureAverage);

		JLabel lblAllLectureAverage = new JLabel("");
		lblAllLectureAverage.setBounds(200, 76, 72, 19);
		pOptionsLec.add(lblAllLectureAverage);

		JButton btnunasigned = new JButton("Ohne Note & Note > 4");
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

		
		///-----

		pOptionsProf.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Professoren-Funktionen",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pOptionsProf.setBounds(632, 11, 230, 143);

		pOptionsProf.setLayout(null);

		JButton btnAverageOfAllLecturesInMyCourse = new JButton(
				"Vorlesungsschnitt anzeigen");
		btnAverageOfAllLecturesInMyCourse
				.setHorizontalAlignment(SwingConstants.LEFT);
		btnAverageOfAllLecturesInMyCourse.setBounds(10, 87, 210, 23);
		pOptionsProf.add(btnAverageOfAllLecturesInMyCourse);

		JComboBox cbAllLecturesOfCourse = new JComboBox();
		cbAllLecturesOfCourse
				.setToolTipText("Alle Vorlesung Ihres Studiengangs");
		cbAllLecturesOfCourse.setBounds(10, 19, 210, 23);
		pOptionsProf.add(cbAllLecturesOfCourse);

		JButton btnNewButton = new JButton("Noten schlechter als 4");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton
				.setToolTipText("Zeigt alle Studenten in Ihrem Studiengang an, die das Notenziel nicht erreicht haben");
		btnNewButton.setBounds(10, 53, 210, 23);
		pOptionsProf.add(btnNewButton);

		JLabel lblAverage = new JLabel("");
		lblAverage.setBounds(20, 113, 85, 19);
		pOptionsProf.add(lblAverage);

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

		contentPane.add(pOptionsLec);
		contentPane.add(pOptionsProf);
		contentPane.add(pOptionsHead);

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
		panel_1.setBorder(new TitledBorder(null, "Speichern", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(880, 163, 205, 122);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnSave = new JButton("Speichern");
		btnSave.setHorizontalAlignment(SwingConstants.LEFT);
		btnSave.setBounds(10, 21, 152, 23);
		panel_1.add(btnSave);
		
		JButton btn_cancel = new JButton("\u00C4nderungen verwerfen");
		btn_cancel.setHorizontalAlignment(SwingConstants.LEFT);
		btn_cancel.setBounds(10, 55, 152, 23);
		panel_1.add(btn_cancel);

		createandAddTable(rowData, columnNames);
		// whoAmI();

	}

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

		// / scrollPane removen weil dann tabelle !
		if (scrollPane != null) {
			panel.remove(scrollPane);
		}

		scrollPane = new JScrollPane(jtAnzeige);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 11, 525, 261);
		panel.add(scrollPane);

	}

	private void whoAmI() {

		String whoAmI = "prof";
		if (whoAmI.equals("lect")) {

			contentPane.add(pOptionsLec);

		} else {
			if (whoAmI.equals("prof")) {

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

	@Override
	public void disposeMeFromExtern() {
		lookForChangesFirst();
		this.dispose();

	}

	@Override
	public void lookForChangesFirst() {
		// TODO Auoto-generated method stub

	}
}
