package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import communication.Master;

/**
 * 
 * * Grundsäzliche Save Klasse. Es werden ArrayLists heurntergeben die dann je
 * nach art in die CSV gespeichert werden sollen. ZB. Einzelne Teile abändern --
 * überschreiben Neue Erstellen * etc hier bitte mit den oberen schichten reden
 * was nach unten kommen kann aus meiner sicht Noten (double) --> vermutlich in
 * einer arraylist mit den dazugehörigen namen (müssen wir nochmal im detail
 * sprechen) komplette neue User (arrayLists)
 * 
 * 
 */
public class Save2 {

	public static File userFile = fileReplacer("stud_info.csv");
	public static File markFile = fileReplacer("mark_info.csv");

	public static File fileReplacer(String file) {
		File f = null;
		URL url = Load.class.getResource(file);

		if (url != null) {
			f = new File(url.getPath().replace("/", "\\\\").replace("%20", " "));
		} else {
			JOptionPane.showMessageDialog(null, "Login Daten nicht vorhanden",
					"Datenbankfehler", JOptionPane.ERROR_MESSAGE);
		}
		return f;
	}

	public static boolean ChangePassword(String pswd, String userId) {

		ArrayList<ArrayList<String>> data = new ArrayList<>();
		data = readFile(userFile);

		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).get(3).equals(userId)) {
				data.get(i).set(4, pswd);
			}
		}

		writeFile(userFile, data);

		return true;
	}

	// Ein neuer User wird angelegt
	// übergeben werden die einzelnen Spaltendaten
	public static boolean addUser(String[] userData) {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		ArrayList<String> newUser = new ArrayList<String>();

		data = readFile(userFile);
		for (int i = 0; i < userData.length; i++) {
			newUser.add(userData[i]);
		}
		data.add(newUser);

		writeFile(userFile, data);

		return true;
	}

	// Eine Vorlesung wird einem User zugeordnet
	// Übergeben wird die UserID, der dazugehörige Kurs und die jeweilige Vorlesung
	public static void assignLectureTo(String id, boolean isLecturer,
			String course, String lecture) {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		ArrayList<String> colData = new ArrayList<String>();
		File f;

		if (isLecturer) {
			f = userFile;
			data = readFile(f);

			for (int i = 0; i < data.size(); i++) {
				if (data.get(i).get(3).equals(id)) {
					if (data.get(i).get(7) != "") {
						data.get(i).set(7, data.get(i).get(7) + "," + lecture);
					} else {
						data.get(i).set(7, lecture);
					}
				}
			}
		} else {
			f = markFile;
			data = readFile(f);

			colData.add(id);
			colData.add(course);
			colData.add(lecture);
			colData.add("0");

			data.add(colData);
		}

		writeFile(f, data);
	}

	// Speicherung neuer Noten für Studenten
	// Die Daten UserID, Vorlesung und Note müssen in einer 2 dimensionalen
	// ArrayList übergeben werden
	public static void saveMarks(ArrayList<ArrayList<String>> newMarks) {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		boolean userFound = false;

		data = readFile(markFile);

		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < newMarks.size() && !userFound; j++) {
				if (data.get(i).get(0).equals(newMarks.get(j).get(0))
						&& data.get(i).get(2).equals(newMarks.get(j).get(1))) {
					data.get(i).set(3, newMarks.get(j).get(2));
					userFound = true;
				}
			}
			userFound = false;
		}

		writeFile(markFile, data);

	}

	public static ArrayList<ArrayList<String>> readFile(File file) {

		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		ArrayList<String> colList;

		String line;
		String[] column;

		try {

			FileReader fr = new FileReader(file);

			BufferedReader br = new BufferedReader(fr);

			try {

				while ((line = br.readLine()) != null) {
					colList = new ArrayList<String>();
					column = line.split(";");

					for (int i = 0; i < column.length; i++) {
						System.out.print(column[i] + ";");
						colList.add(column[i]);
					}
					System.out.println("");

					data.add(colList);
				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Login Daten nicht vorhanden",
					"Datenbankfehler", JOptionPane.ERROR_MESSAGE);
		}

		return data;
	}

	private static void writeFile(File file, ArrayList<ArrayList<String>> data) {

	}

}
