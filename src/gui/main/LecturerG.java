package gui.main;

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
	private JTable table;
	private JPanel pOptionsLec = new JPanel();
	private JPanel pOptionsHead =  new JPanel();
	private JPanel pOptionsProf =  new JPanel();

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
		cbMyLectures.setEditable(true);
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

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(318, 163, 767, 263);
		contentPane.add(panel);
		panel.setLayout(null);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setBounds(10, 11, 747, 241);
		panel.add(table);

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
		btnAddNew.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddNew.setBounds(10, 24, 157, 23);
		pOptionsHead.add(btnAddNew);

		JButton btnAssign = new JButton("Nutzer&Vorlesung");
		btnAssign.setToolTipText("Nutzer,Dozenten und Vorlesungen zuweisen");
		btnAssign.setHorizontalAlignment(SwingConstants.LEFT);
		btnAssign.setEnabled(true);
		btnAssign.setBounds(10, 58, 157, 23);
		pOptionsHead.add(btnAssign);
		whoAmI();

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
