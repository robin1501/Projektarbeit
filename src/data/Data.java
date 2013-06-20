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

public class Data {

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

	public static void write(File file, ArrayList<ArrayList<String>> data) {
		try {
			String row;

			// Die Datei wird als erstes geleert
			FileWriter fDel = new FileWriter(file, true);

			fDel.write("");
			fDel.flush();
			fDel.close();

			// Nachdem die Datei geleert wurde, wird sie mit den neuen Werten
			// befüllt
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
