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

public class LecturerG extends JFrame implements IDisposeMe {

	/**
	 * 
	 */
	private static final long serialVersionUID = 165886283281206589L;
	private JPanel contentPane;
	private JTable table;

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
		setBounds(100, 100, 1043, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		PersonalDataP persoPanel = new PersonalDataP();
		persoPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		persoPanel.setBounds(10, 11, 298, 417);
		contentPane.add(persoPanel);
		
		JPanel pOptionsLec = new JPanel();
		pOptionsLec.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pOptionsLec.setBounds(318, 11, 340, 143);
		contentPane.add(pOptionsLec);
		pOptionsLec.setLayout(null);
		
		JComboBox cbMyLectures = new JComboBox();
		cbMyLectures.setEditable(true);
		cbMyLectures.setBounds(10, 7, 180, 20);
		pOptionsLec.add(cbMyLectures);
		
		JButton btnschnitt = new JButton("Vorlesungsschnitt");
		btnschnitt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnschnitt.setHorizontalAlignment(SwingConstants.LEFT);
		btnschnitt.setBounds(10, 38, 180, 23);
		pOptionsLec.add(btnschnitt);
		
		JButton btngesamtschnitt = new JButton("Schnitt aller Vorlesungen");
		btngesamtschnitt.setHorizontalAlignment(SwingConstants.LEFT);
		btngesamtschnitt.setBounds(10, 72, 180, 23);
		pOptionsLec.add(btngesamtschnitt);
		
		JLabel lbllectureAverage = new JLabel("");
		lbllectureAverage.setBounds(200, 42, 104, 19);
		pOptionsLec.add(lbllectureAverage);
		
		JLabel lblAllLectureAverage = new JLabel("");
		lblAllLectureAverage.setBounds(200, 76, 104, 19);
		pOptionsLec.add(lblAllLectureAverage);
		
		JButton btnunasigned = new JButton("Ohne Note & Note > 4");
		btnunasigned.setToolTipText("Zeigt alle Ihre Studenten die das Notenziel nicht erreicht haben oder f\u00FCr die noch keine Note abgegeben wurde.");
		btnunasigned.setHorizontalAlignment(SwingConstants.LEFT);
		btnunasigned.setBounds(10, 106, 180, 23);
		pOptionsLec.add(btnunasigned);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(318, 163, 699, 263);
		contentPane.add(panel);
		panel.setLayout(null);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setBounds(10, 11, 679, 241);
		panel.add(table);
		
		JPanel pOptionsProf = new JPanel();
		pOptionsProf.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pOptionsProf.setBounds(668, 11, 230, 143);
		contentPane.add(pOptionsProf);
		pOptionsProf.setLayout(null);
		
		JButton btnAverageOfAllLecturesInMyCourse = new JButton("Vorlesungsschnitt anzeigen");
		btnAverageOfAllLecturesInMyCourse.setHorizontalAlignment(SwingConstants.LEFT);
		btnAverageOfAllLecturesInMyCourse.setBounds(10, 42, 210, 23);
		pOptionsProf.add(btnAverageOfAllLecturesInMyCourse);
		
		JComboBox cbAllLecturesOfCourse = new JComboBox();
		cbAllLecturesOfCourse.setToolTipText("Alle Vorlesung Ihres Studiengangs");
		cbAllLecturesOfCourse.setBounds(10, 11, 210, 20);
		pOptionsProf.add(cbAllLecturesOfCourse);
		
		JButton btnNewButton = new JButton("Noten schlechter als 4");
		btnNewButton.setToolTipText("Zeigt alle Studenten in Ihrem Studiengang an, die das Notenziel nicht erreicht haben");
		btnNewButton.setBounds(10, 109, 210, 23);
		pOptionsProf.add(btnNewButton);
		
		JLabel lblAverage = new JLabel("");
		lblAverage.setBounds(10, 76, 85, 19);
		pOptionsProf.add(lblAverage);
		
		JPanel pOptionsHead = new JPanel();
		pOptionsHead.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pOptionsHead.setBounds(908, 11, 109, 143);
		contentPane.add(pOptionsHead);
		pOptionsHead.setLayout(null);
	}

	@Override
	public void disposeMeFromExtern() {
		this.dispose();
		
	}

	@Override
	public void lookForChangesFirst() {
		// TODO Auto-generated method stub
		
	}
}
