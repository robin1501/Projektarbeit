package communication;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import data.Load;

/**
 * Es werden in erster Linie semantische und syntaktische Prüfungen
 * durchgeführt,damit sicher gestellt werden kann, <br>
 * dass von der GUI nur "Richtiges" in die unteren Schichten und die CSV Datei
 * gelangen kann.
 * 
 * 
 */
public class InsertAndValidationChecks {

	public static void Login(char[] pswd, String user) {
		boolean isSecureLoginInsert = checkforNotNUllorWhiteSpace(pswd, user);
		boolean NumberInName = NumberInsideString(user);

		if (isSecureLoginInsert && !NumberInName) {
			ArrayList<String> userData = Load.TryLoadUser(user, pswd);
			
			// ArrayList wird wie folgt hochgegeben -->String name, String
			//string role, String isHead, String name,String firstname, String id, String course
			// erwartet = char role, String isHead, String name,String firstname, String id, String course
			
		//	Master.setMyDynamicUser(userData.get(0).charAt(0), userData.get(1), userData.get(2), userData.get(3), userData.get(4), userData.get(5));
			

		}

	}

	private static boolean checkforNotNUllorWhiteSpace(char[] pswd, String user) {

		boolean retVal = false;

		// User abhandeln
		if ( noWhiteSpace(user) && !user.equals("")) {
			retVal = true;
		} else {
			// Fehlermeldung

			JOptionPane.showMessageDialog(null,
					"Bitte UserId ausfüllen (Keine Leerzeichen)", "ID-Fehler",
					JOptionPane.ERROR_MESSAGE);
			retVal = false;
		}
		
		// Passwort abhandeln
		if (pswd.length > 0 && noWhiteSpace(pswd)) {
			retVal = true;

		} else {
			// Fehlermeldung

			JOptionPane.showMessageDialog(null,
					"Bitte Passwort eingeben (Keine Leerzeichen)", "Passwortfehler",
					JOptionPane.ERROR_MESSAGE);
			retVal = false;
		}
		return retVal;
	}

	private static boolean noWhiteSpace(char[] checkArray) {

		boolean retVal = true;

		for (char i : checkArray) {

			if (i == ' ') {
				retVal = false;
			}
		}
		return retVal;
	}

	private static boolean noWhiteSpace(String checkString) {

		boolean retVal = true;
		char[] charArray = checkString.toCharArray();

		for (char i : charArray) {

			if (i == ' ') {
				retVal = false;
			}
		}
		return retVal;
	}

	private static boolean NumberInsideString(String checkString) {

		boolean retVal = true;
		char[] charArray = checkString.toCharArray();

		for (char i : charArray) {

			String t = null;
			t = Character.toString(i);

			try {
				Integer.parseInt(t);
				retVal = true;

			} catch (NumberFormatException nfe) {
				
				JOptionPane.showMessageDialog(null,
						"Fehlerhafte UserId (Keine Ziffern erlaubt)", "ID-Fehler",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}

		}

		return retVal;
	}

}
