package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * 
 * Grunds‰zliche Load Klasse was hier drin passiert ob mit oder ohne Sortierung
 * muss von Robin und NIls gekl‰rt werden. Auﬂerdem muss hier der Aufbau der
 * neunen CSV datei "NOTEN" gekl‰rt werden.
 * 
 * 
 */
public class Load {

	public static File userFile = Data.fileReplacer("stud_info.csv");
	public static File markFile = Data.fileReplacer("mark_info.csv");

	public static ArrayList<String> TryLoadUser(String userID, String pswd) {
		
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		ArrayList<String> userData = new ArrayList<String>();

		boolean pswdChecked = false;
		boolean loginAccepted = false;

		data = Data.read(userFile);

		for (int i = 1; i < data.size() && !loginAccepted && !pswdChecked; i++) {
			if (data.get(i).get(3).equals(userID)) {
				if (data.get(i).get(4).equals(pswd)) {
					userData.add(data.get(i).get(2)); // Rolle
					userData.add(data.get(i).get(6)); // Head
					userData.add(data.get(i).get(0)); // Nachname
					userData.add(data.get(i).get(1)); // Vorname
					userData.add(data.get(i).get(3)); // ID
					userData.add(data.get(i).get(5)); // Studiengang

					loginAccepted = true;

				} else {
					JOptionPane.showMessageDialog(null,
							"Falsches Passwort eingegeben", "Login Error",
							JOptionPane.ERROR_MESSAGE);
					return null;
				}

				pswdChecked = true;

			}
		}
		
		if(!loginAccepted && !pswdChecked)
		{
				JOptionPane.showMessageDialog(null,
						"Falschen Benutzernamen eingegeben", "Login Error",
						JOptionPane.ERROR_MESSAGE);
				return null;
		}

		return userData;

	}

	
	//Die Funktion gibt die jeweiligen Vorlesungen und dazugehˆrigen Noten des Studenten zur¸ck
	public static ArrayList<ArrayList<String>> getMarks(String userID) {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> allMarks = new ArrayList<ArrayList<String>>();
		ArrayList<String> courseMark; 

		data = Data.read(markFile);
		
		for (int i = 0; i < data.size(); i++) {
			courseMark = new ArrayList<String>();
			
			if(data.get(i).get(0).equals(userID))
			{
				courseMark.add(data.get(i).get(2)); //Vorlesung
				courseMark.add(data.get(i).get(3)); //Note
			}
			
			allMarks.add(courseMark);
		}
		
		return allMarks;
	}

	public static ArrayList<String> getLectures(String userID) {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		ArrayList<String> lectures = new ArrayList<String>();
		
		String[] lectArr;
		
		boolean userFound = false;
		
		data = Data.read(userFile);
		
		for (int i = 0; i < data.size() && !userFound; i++) {
			if (data.get(i).get(3).equals(userID)) {
				lectArr = data.get(i).get(7).split(",");
				
				for (int j = 0; j < lectArr.length; j++) {
					lectures.add(lectArr[j]);
				}
				
				userFound = true;
			}
		}
		
		return lectures;
	}
}
