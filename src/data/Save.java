package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * 
 * * Grunds�zliche Save Klasse. Es werden ArrayLists heurntergeben die dann je
 * nach art in die CSV gespeichert werden sollen. ZB. Einzelne Teile ab�ndern --
 * �berschreiben Neue Erstellen * etc hier bitte mit den oberen schichten reden
 * was nach unten kommen kann aus meiner sicht Noten (double) --> vermutlich in
 * einer arraylist mit den dazugeh�rigen namen (m�ssen wir nochmal im detail
 * sprechen) komplette neue User (arrayLists)
 * 
 * 
 */
public class Save {
	
	public static File userFile = Data.fileReplacer("stud_info.csv");
	public static File markFile = Data.fileReplacer("mark_info.csv");

	public static boolean ChangePassword(String pswd, String userId) {

		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		data = Data.read(userFile);

		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).get(3).equals(userId)) {
				data.get(i).set(4, pswd);
			}
		}
		
		Data.write(userFile, data);

		return true;
	}

	// Ein neuer User wird angelegt
	// �bergeben werden die einzelnen Spaltendaten
	public static boolean addUser(String[] userData) {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		ArrayList<String> newUser = new ArrayList<String>();

		data = Data.read(userFile);
		for (int i = 0; i < userData.length; i++) {
			newUser.add(userData[i]);
		}
		data.add(newUser);

		Data.write(userFile, data);

		return true;
	}

	// Eine Vorlesung wird einem User zugeordnet
	// �bergeben wird die UserID, der dazugeh�rige Kurs und die jeweilige Vorlesung
	public static void assignLectureTo(String id, boolean isLecturer,
			String course, String lecture) {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		ArrayList<String> colData = new ArrayList<String>();
		File f;

		if (isLecturer) {
			f = userFile;
			data = Data.read(f);

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
			data = Data.read(f);

			colData.add(id);
			colData.add(course);
			colData.add(lecture);
			colData.add("0");

			data.add(colData);
		}

		Data.write(f, data);
	}

	// Speicherung neuer Noten f�r Studenten
	// Die Daten UserID, Vorlesung und Note m�ssen in einer 2 dimensionalen
	// ArrayList �bergeben werden
	public static void saveMarks(ArrayList<ArrayList<String>> newMarks) {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		boolean userFound = false;

		data = Data.read(markFile);

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

		Data.write(markFile, data);

	}	

}
