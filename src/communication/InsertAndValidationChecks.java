package communication;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import data.Load;

/**
 * Es werden Prüfungen zu den eingegebenen Werten durchgeführt, damit sicher
 * gestellt werden kann, <br>
 * dass von der GUI nur "Richtiges" (Richtlinien abhängig) in die unteren
 * Schichten und die CSV Datei gelangen kann.
 * 
 * 
 */
public class InsertAndValidationChecks {

	/**
	 * Hauptaufruf aus dem Master zum Login. <br>
	 * <br>
	 * Es wird auf die Ergebnisse der Checks gewartet und dann versucht den User
	 * zu laden. Gelingt dies, wird im Master der dynamischeUser gesetzt und die
	 * rollenspezifische GUI aufgerufen <br>
	 * Der Return werd wird auf true gesetzt<br>
	 * <br>
	 * Schlägt das Laden oder das Setzen im Master fehl, wird der User auf den
	 * möglichen Fehler hingewiesen. <br>
	 * Der Return werd wird auf false gesetzt <br>
	 * <br>
	 * Anhand des Return wird in de Login-Gui entschieden, ob die Gui
	 * geschlossen wird oder ob ein erneuter Login ermöglicht wird.
	 * 
	 * @param pswd
	 * @param user
	 * @return isValidData
	 */
	public static boolean Login(char[] pswd, String user) {

		boolean isSecureLoginInsert = checkforNotNUllorWhiteSpace(pswd, user);
		boolean numberInName = isNumberInsideString(user);
		boolean isValidData = false;

		if (isSecureLoginInsert && !numberInName) {

			ArrayList<String> userData = Load.TryLoadUser(user, pswd);

			/*
			 * ArrayList wird wie folgt hochgegeben --> string role, String
			 * isHead, String name,String firstname, String id, String course
			 * 
			 * erwartet = char role, String isHead, String name,String
			 * firstname, String id, String course
			 */

			if (userData != null) {

				try {

					Master.setMyDynamicUser(userData.get(0).charAt(0),
							userData.get(1), userData.get(2), userData.get(3),
							userData.get(4), userData.get(5));

					isValidData = true;

				} catch (NullPointerException ex) {

					isValidData = false;
				}

			}
		}
		return isValidData;

	}

	/**
	 * Überprüfung, ob in den Übergabewerten Werte gespeichert sind und diese keine Leerzeichen enthalten.<br>
	 * Auf Fehler wird durch eine ErrorMessage, die Fehlerabhängig aufgebaut wird, hingewiesen.
	 * @param pswd
	 * @param user
	 * @return Booleanwert für notNullorWhitespace
	 */
	private static boolean checkforNotNUllorWhiteSpace(char[] pswd, String user) {

		boolean userRetVal = false;
		boolean pswdRetVal = false;
		String pswdError = "";
		String userError = "";

		// User -------------------------------------
		
		if (noWhiteSpace(user) && !user.equals("")) {
			userRetVal = true;
		} else {
			
		
			userError = "Bitte User-Id eingeben \n ";			
			userRetVal = false;
		}

		// Passwort ----------------------------------
		
		if (pswd.length > 0 && noWhiteSpace(pswd)) {
			pswdRetVal = true;

		} else {
			
	
			pswdError = "Bitte Passwort eingeben\n";
			pswdRetVal = false;
		}
		
		// Entscheidung und ErrorMessageBuilder-------
		
		if (pswdRetVal && userRetVal) {

			return true;

		} else {

			JOptionPane.showMessageDialog(null,
					pswdError+userError + "\n (Keine Leerzeichen)",
					"Eingabefehler", JOptionPane.ERROR_MESSAGE);
			return false;
		}

	}
/**
 * Prüft ob Leerzeichen im zu überprüfenden Char-Array vorhanden sind.<br>
 * Es wird über eine foreach schleife durchlaufen.
 * 
 * @param checkArray
 * @return retval ob Leerzeichen vorhanden sind.
 */
	private static boolean noWhiteSpace(char[] checkArray) {

		boolean retVal = true;

		for (char i : checkArray) {

			if (i == ' ') {
				retVal = false;
			}
		}
		return retVal;
	}
	
	/**
	 * Prüft ob Leerzeichen im zu überprüfenden String vorhanden sind.<br>
	 * Der String wird in ein Char-Array umgewandelt. <br>
	 * Dieses wird an die Methode noWhiteSpace übergeben.<br>
	 * Der zurückgegebene Wert wird dann ebenfalls zurückgegeben.
	 * @param checkArray
	 * @return Funktionswert von noWhiteSpace(char [] checkArray)
	 */
	private static boolean noWhiteSpace(String checkString) {
		
		char[] charArray = checkString.toCharArray();

		return noWhiteSpace(charArray);
	}
/**
 * Überprüfung auf Ziffern im übergebenen String <br>
 * Der String wird zuerst in ein Char-Array umgewandelt, sodass die Zeichen einzeln betrachtet werden können. <br><br>
 * 
 * Nun wird diese Char-Array durchlaufen.<br>
 *  Jedes Einzelzeichen wird versucht in ein Integer zu parsen. Gelingt dies wird sofort "true" zurückgegeben.<br>
 *  Schlägt es aber fehl, wird im Catch-Block die retVal auf false gesetzt.
 * 
 * @param checkString
 * @return Value zur Fragestellung "isNumberInsideString"
 */
	private static boolean isNumberInsideString(String checkString) {

		boolean retVal = false;
		char[] charArray = checkString.toCharArray();

		for (char i : charArray) {

			String t = null;
			t = Character.toString(i);

			try {
				Integer.parseInt(t);
				return true;

			} catch (NumberFormatException nfe) {

				retVal = false;
			}

		}
		return retVal;

	}

}
