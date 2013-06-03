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
 * * Grundsäzliche Save Klasse. Es werden ArrayLists heurntergeben die dann je
 * nach art in die CSV gespeichert werden sollen. ZB. Einzelne Teile abändern --
 * überschreiben Neue Erstellen * etc hier bitte mit den oberen schichten reden
 * was nach unten kommen kann aus meiner sicht Noten (double) --> vermutlich in
 * einer arraylist mit den dazugehörigen namen (müssen wir nochmal im detail
 * sprechen) komplette neue User (arrayLists)
 * 
 * 
 */
public class Save {

	public static boolean ChangePassword(String pswd, String userId) {

		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		data = Data.read("stud_info.csv");

		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).get(3).equals(userId)) {
				data.get(i).set(4, pswd);
			}
		}
		
		Data.write("stud_info.csv", data);

		return true;
	}

	// Ein neuer User wird angelegt
	// übergeben werden die einzelnen Spaltendaten
	public static boolean addUser(String[] userData) {
		return true;
	}

	// Eine Vorlesung wird einem User zugeordnet
	// Übergeben wird die UserID und die jeweilige Vorlesung
	public static boolean assignLectureTo(String id, String lecture) {
		return true;
	}

	// Speicherung neuer Noten für Studenten
	public static boolean saveMarks() {
		return true;
	}	

}
