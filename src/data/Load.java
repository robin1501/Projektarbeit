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
 * Die Klasse lädt wichtige Daten aus der Benutzer und Noten Datei. Die gezogenen Daten werden<br>
 * an die GUI Klassen übergeben und auf der Maske ausgegeben. <br>
 * Durch die Klasse werden die eingebenen Passwörter geprüft und Noten bzw. Vorlesungen zu einem<br>
 * User geladen.
 * 
 *
 */
public class Load {

	public static File userFile = Data.fileReplacer("stud_info.csv");
	public static File markFile = Data.fileReplacer("mark_info.csv");
	
	/**
	 * Die Methode bekommt die im Loginfenster übergebenen User-ID und das dazugehörige<br>
	 * Passwort übergeben. Sie vergleicht daraufhin ob die eingebenen Daten 1:1 mit den Daten<br>
	 * aus der CSV-Datei "stud_info" übereinstimmen. Führt diese Prüfung zum Erfolg wird zu<br>
	 * diesem User eine ArrayList mit folgenden Daten zurückgegeben:<br>
	 * - Benutzerrolle<br>
	 * - Ob der User ein Studiengangsleiter ist<br>
	 * - Nachname<br>
	 * - Vorname<br>
	 * - User-ID<br>
	 * - Studiengang<br><br>
	 * Sollte entweder eine falsche User-ID oder ein falsches Passwort eingegeben werden, wird<br>
	 * eine entsprechende Meldung ausgegeben und der Login verweigert. 
	 * @param userID
	 * @param pswd
	 * @return ArrayList&lt;String&gt;
	 */
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

	
	/**
	 * Die Methode wird nur von Studenten angesprochen und sucht sich, zu einer übergebenen<br>
	 * User-ID, aus der CSV-Datei "mark_info" die dazugehörigen Daten zusammen. Diese werden<br>
	 * in folgender Reihenfolge in eine 2 dimensionale ArrayList gespeichert und zurückgegeben:<br>
	 * - Vorlesung<br>
	 * - Note
	 * @param userID
	 * @return ArrayList&lt;ArrayList&lt;String&gt;&gt;
	 */
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
				allMarks.add(courseMark);
			}		
			
		}
		
		return allMarks;
	}

	/**
	 * Die Methode wird nur von Dozenten und Professoren aufgerufen und sucht sich, zu der<br>
	 * übergebenen User-ID, die dazugehörigen Vorlesungen. Diese sind in der CSV-Datei "stud_info"<br>
	 * in der Spalte "Vorlesungen" durch ein Komma separiert gespeichert. Die Daten werden daher<br>
	 * ausgelesen und nach Kommas aufgeteilt. Jeder daraus resultierende Datensatz wird dann in einer<br>
	 * ArrayList gespeichert zurückgegeben.
	 * @param userID
	 * @return ArrayList&lt;String&gt;
	 */
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
