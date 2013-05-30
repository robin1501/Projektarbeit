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
 * Grundsäzliche Load Klasse was hier drin passiert ob mit oder ohne Sortierung
 * muss von Robin und NIls geklärt werden. Außerdem muss hier der Aufbau der
 * neunen CSV datei "NOTEN" geklärt werden.
 * 
 * 
 */
public class Load {

	private static String user;
	private static String pw;
	private static String line;

	static String[] column = new String[50];

	static ArrayList<String> data = new ArrayList<String>();

	static boolean pswdChecked = false;
	static boolean loginAccepted = false;

	public static ArrayList<String> TryLoadUser(String userID, String pswd) {

		// wenn laden möglich, dann bitte die ArrayList mit allen daten die man
		// für den Konstruktor einer rolle Braucht mitgeben und zwar in der
		// Richtigen Reihenfolge! SONST NULL
		// bitte den Rollen entnehmen !!! wichtig
		// DANIEl
		// string role, String isHead, String name,String firstname, String id,
		// String course

		/*
		 * falls der user nicht gefunden wird bitte Kriterium über prüfen. und
		 * per Errormessage angeben also zb login fehlgeschlagen falscher oder
		 * nicht vorhandener benutzername und falsches passwort
		 */

		try {
			// Dateipfad muss mit 2 Backslash angegeben werden, da sonst die
			// Datei nicht gefunden wird
			
			URL url = Load.class.getResource("stud_info.csv");
			if (url != null) {
				File tst = new File (url.getPath());
				if(tst.exists()){
					System.out.println("test");
				}
				File f = new File(url.getPath().replace("/", "\\\\").replace("%20", " "));
				if(f.exists()){
					System.out.println("test2");
				}
				FileReader fr = new FileReader(f);

				BufferedReader br = new BufferedReader(fr);

				try {
					// Erste Zeile muss übersprungen werden da sie die
					// Spaltennamen enthält
					br.readLine();

					while ((line = br.readLine()) != null && !loginAccepted && !pswdChecked) {
						column = line.split(";");

						if (column[3].equals(userID)) {
							if (column[4].equals(pswd)) {
								data.add(column[2]); // Rolle
								data.add(column[6]); // Head
								data.add(column[0]); // Nachname
								data.add(column[1]); // Vorname
								data.add(column[3]); // ID
								data.add(column[5]); // Studiengang

								loginAccepted = true;

							} else {
								JOptionPane.showMessageDialog(null,
										"Falsches Passwort eingegeben",
										"Login Error",
										JOptionPane.ERROR_MESSAGE);
								return null;
							}
							
							pswdChecked = true;
							
						}

					}


				} catch (IOException e) {

					e.printStackTrace();

				}

				if (!loginAccepted && !pswdChecked) {
					JOptionPane.showMessageDialog(null,
							"Benutzername nicht vorhanden", "Login Error",
							JOptionPane.ERROR_MESSAGE);
					return null;
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "Login Daten nicht vorhanden",
						"Datenbankfehler", JOptionPane.ERROR_MESSAGE);
			}
				

		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Login Daten nicht vorhanden",
					"Datenbankfehler", JOptionPane.ERROR_MESSAGE);
		}
		
		return data;

	}
}
