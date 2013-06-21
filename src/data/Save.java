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
	
	public static File userFile = Data.fileReplacer("stud_info.csv");
	public static File markFile = Data.fileReplacer("mark_info.csv");

	/**
	 * Die Methode bekommt eine User-ID und ein ihr zuzuweisendes Passwort übergeben. Der User<br>
	 * wird in der CSV-Datei "stud_info" gesucht und dass neue Passwort in die dazugehörige<br>
	 * Spalte eingefügt. Daraufhin wird die Datei neu geschrieben und abgespeichert. 
	 * @param pswd
	 * @param userId
	 * @return boolean
	 */
	public static void ChangePassword(String pswd, String userId) {

		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		data = Data.read(userFile);

		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).get(3).equals(userId)) {
				data.get(i).set(4, pswd);
			}
		}
		
		Data.write(userFile, data);

	}

	/**
	 * Die Methode bekommt alle relevanten Daten zu einem neuen User in einer ArrayList mit<br>
	 * folgendem Aufbau übergeben:<br>
	 * - Nachname<br>
	 * - Vorname<br>
	 * - Rolle<br>
	 * - ID<br>
	 * - Passwort<br>
	 * - Studiengang<br>
	 * - Studiengangsleiter<br>
	 * - Vorlesung<br><br>
	 * 
	 * Der neue User wird dann mit den übergeben Daten an das Ende der Datei eingefügt.
	 * @param userData
	 */
	public static void addUser(ArrayList<String> userData) {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

		data = Data.read(userFile);
		data.add(userData);

		Data.write(userFile, data);
	}

	/**
	 * Die Methode dient zur Abspeicherung geänderter Noten. Hierzu wird eine 2-dimensionale<br>
	 * ArrayList mit folgenden Daten übergeben:<br>
	 * - User<br>
	 * - Vorlesung<br>
	 * - Note<br><br>
	 * Die jeweiligen User werden mit Ihren dazugehörigen Vorlesung in der CSV-Datei "mark_info"<br>
	 * gesucht und die neue Note eingetragen.<br>
	 * Wenn alle User abgearbeitet wurden, wird die CSV-Datei mit den neuen Noten abgespeichert.
	 * @param newMarks
	 */
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

	/**
	 * Die Methode weist einen Dozenten/Professor einer Vorlesung zu. Hierzu muss geprüft werden<br>
	 * ob der jeweilige Dozent/Professor bereits einer Vorlesung zugewiesen ist. Sollte dies der<br>
	 * Fall sein wird hinter die letzte Vorlesung erst ein Komma und dann die neue Vorlesung<br>
	 * eingefügt. Anonsten wird die neue Vorlesung ohne weiteren Zusatz in das Feld eingetragen.<br>
	 * Die Zuweisung wird dann in die CSV-Datei "stud_info" eingetragen.
	 * @param lect
	 * @param staff
	 */
	public static void AssignLectureToStaff(String lect, String staff) {
		ArrayList<ArrayList<String>> data = Data.read(userFile);
		boolean userFound = false;
		
		for(int i =0;i<data.size() && !userFound ; i++){
			if(data.get(i).get(3).toString().equals(staff)){
				String lec=data.get(i).get(7).toString();
				if(lec == ""){
					data.get(i).set(7, lect);
				}else{
					lec= data.get(i).get(7).toString()+","+lect;
					data.get(i).set(7, lec);
				}
				userFound=true;
			}
		}
		Data.write(userFile, data);
		
	}

	/**
	 * Die Methode bekommt eine Liste von Studenten übergeben und weist diese einer Vorlesung zu.<br>
	 * Die Studenten werden mit ihrem Studiengang und der jeweiligen Vorlesung in die CSV-Datei "mark_info"<br>
	 * eingetragen. Die Note zu der Vorlesung wird hierbei mit 0 vorbelegt und kann dann im Nachhinein von<br>
	 * einem Dozenten eingetragen werden.
	 * @param selLect
	 * @param selStudis
	 */
	public static void saveAssignmentsStudisToLecture(String selLect,
			ArrayList<String> selStudis) {
		ArrayList<ArrayList<String>> data = Data.read(markFile);
		ArrayList<ArrayList<String>> data2 = Data.read(userFile);
		boolean userFound= false;
		ArrayList<String> Stud;
		try{
			for(int i =0; i<selStudis.size();i++){
				for(int j=0; j<data2.size() && userFound ==false;j++){
					if(selStudis.get(i).toString().equals(data2.get(i).get(3).toString())){
						
						userFound=true;
					}
				}
				Stud=new ArrayList<String>();
				Stud.add(data2.get(i).get(3).toString());
				Stud.add(data2.get(i).get(5).toString());
				Stud.add(selLect);
				Stud.add("0");	
				data.add(Stud);
				userFound =  false;
			}
			Data.write(markFile, data);
		}catch(Exception ex){
			
		}
		
		
		
		
	}

}
