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
	public static ArrayList<ArrayList<String>> read(String file) {

		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		ArrayList<String> colList;

		String line;
		String[] column;

		try {

			URL url = Load.class.getResource(file);
			if (url != null) {
				File f = new File(url.getPath().replace("/", "\\\\"));

				FileReader fr = new FileReader(f);

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

			} else {
				JOptionPane.showMessageDialog(null,
						"Login Daten nicht vorhanden", "Datenbankfehler",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Login Daten nicht vorhanden",
					"Datenbankfehler", JOptionPane.ERROR_MESSAGE);
		}

		return data;
	}

	public static void write(String file, ArrayList<ArrayList<String>> data) {
		try {
			String row;

			// Die Datei wird als erstes geleert
			FileWriter fDel = new FileWriter(file, false);
			BufferedWriter bDel = new BufferedWriter(fDel);

			bDel.write("");
			bDel.close();

			// Nachdem die Datei geleert wurde, wird sie mit den neuen Werten
			// befüllt
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < data.size(); i++) {
				row = "";
				for (int j = 0; j < data.size(); j++) {
					row = row + data.get(i).get(j) + ";";
				}

				bw.write(row + "\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
