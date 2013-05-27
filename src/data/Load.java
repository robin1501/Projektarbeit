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
 * Grunds�zliche Load Klasse was hier drin passiert ob mit oder ohne Sortierung
 * muss von Robin und NIls gekl�rt werden. Au�erdem muss hier der Aufbau der
 * neunen CSV datei "NOTEN" gekl�rt werden.
 * 
 * 
 */
public class Load {

	private static String user;
	private static String pw;
	private static String line;

	static String[] column = new String[50];

	static ArrayList<String> data = new ArrayList<String>();

	static boolean loginAccepted = false;

	/*
	 * public Load(String user, char[] pswd) {
	 * 
	 * this.pswd = pswd; this.user = user;
	 * 
	 * }
	 */

	public static ArrayList<String> TryLoadUser(String userID, String pswd) { // throws
																				// FileNotFoundException
																				// {

		// wenn laden m�glich, dann bitte die ArrayList mit allen daten die man
		// f�r den Konstruktor einer rolle Braucht mitgeben und zwar in der
		// Richtigen Reihenfolge! SONST NULL
		// bitte den Rollen entnehmen !!! wichtig
		// DANIEl
		// string role, String isHead, String name,String firstname, String id,
		// String course

		/*
		 * falls der user nicht gefunden wird bitte Kriterium �ber pr�fen. und
		 * per Errormessage angeben also zb login fehlgeschlagen falscher oder
		 * nicht vorhandener benutzername und falsches passwort
		 */

		try {
			URL l = Load.class.getResource("stud_info.csv");
			File f = new File(l.toString());
			if(f.exists())
				new Object();

			FileReader fr = new FileReader(f);

			BufferedReader br = new BufferedReader(fr);

			try {

				while ((line = br.readLine()) != null && !loginAccepted) {
					column = line.split(";");

					if (column[3].equals(userID)) {
						if (column[4].equals(pswd)) {
							data.add(column[2]); // Rolle
							data.add(column[6]); // Head
							data.add(column[0]); // Nachname
							data.add(column[1]); // Vorname
							data.add(column[3]); // ID
							data.add(column[5]); // Studiengang

						} else {
							JOptionPane.showMessageDialog(null,
									"Falsches Passwort eingegeben",
									"Login Error", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Benutzername inkorrekt", "Login Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		} catch (FileNotFoundException e1) {
			// hier dann vlt ein file chooser hochkommen lassen der ihn das CS
			// teil scuhen l�sst ist ja eigentlich kein ding // GRU� Daniel
			// oder ne normale message box. einfach bsichen creativ sein es ist
			// dein part
			System.out.println("test");
		}

		return data;

	}
}
