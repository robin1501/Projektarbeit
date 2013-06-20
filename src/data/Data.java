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
 * Die Klasse dient dazu alle Dateibezogenen Daten abzuarbeiten.<br>
 * Hierzu geh�ren:<br>
 * - Korrekte Aufl�sung des Dateinamens<br>
 * - Lesen der Datei<br>
 * - Schreiben der Datei<br>
 * 
 *
 */

public class Data {

	/**
	 * Da es Probleme mit der Dateinamensaufl�ung gab wurde die Methode fileReplacer erstellt.<br>
	 * Sie durchsucht den Dateipfad nach Schr�gstrichen und der Zeichenfolge "%20" und ersetzt <br>
	 * diese durch einen Backslash bzw. ein Leerzeichen. Der korrekte Dateipfad wird dann in einer<br>
	 * File Variablen gespeichert und diese wird dann als R�ckgabewert zur�ckgegeben.
	 * @param file
	 * @return File
	 */
	public static File fileReplacer(String file) {
		File f = null;
		URL url = Data.class.getResource(file);

		if (url != null) {
			f = new File(url.getPath().replace("/", "\\\\").replace("%20", " "));
		} else {
			JOptionPane.showMessageDialog(null, "Login Daten nicht vorhanden",
					"Datenbankfehler", JOptionPane.ERROR_MESSAGE);
		}
		return f;
	}

	/**
	 * Die Methode read liest alle Daten aus der �bergebenen Datei und gibt diese in einer<br>
	 * 2 dimensionalen Arraylist zur�ck.	
	 * @param file
	 * @return ArrayList<ArrayList<String>>
	 */
	public static ArrayList<ArrayList<String>> read(File file) {

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
						colList.add(column[i]);
					}

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

	/**
	 * Die Methode write bekommt Daten in einer 2 dimensionalen Arraylist �bergeben und tr�gt<br>
	 * diese in die ebenfalls �bergebene Datei ein. Hierzu wird die bereits bestehende Datei<br>
	 * gel�scht und dann die Daten Zeile f�r Zeile eingetragen.
	 * @param file
	 * @param data
	 */
	public static void write(File file, ArrayList<ArrayList<String>> data) {
		try {
			String row;

			// Die Datei wird als erstes geleert
			FileWriter fDel = new FileWriter(file, true);

			fDel.write("");
			fDel.flush();
			fDel.close();

			// Nachdem die Datei geleert wurde, wird sie mit den neuen Werten
			// bef�llt
			FileWriter fw = new FileWriter(file, false);

			for (int i = 0; i < data.size(); i++) {
				row = "";
				for (int j = 0; j < data.get(i).size(); j++) {
					row = row + data.get(i).get(j) + ";";
				}

				fw.write(row + "\n");
			}

			fw.flush();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
