package communication;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import data.Load;

/**
 * Es werden Pr�fungen zu den eingegebenen Werten durchgef�hrt, damit sicher
 * gestellt werden kann, <br>
 * dass von der GUI nur "Richtiges" (Richtlinien abh�ngig) in die unteren
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
	 * Der R�ckgabewert wird auf true gesetzt<br>
	 * <br>
	 * Schl�gt das Laden oder das Setzen im Master fehl, wird der User auf den
	 * m�glichen Fehler hingewiesen. <br>
	 * Der R�ckgabewert wird auf false gesetzt <br>
	 * <br>
	 * Anhand des Return wird in de Login-Gui entschieden, ob die Gui
	 * geschlossen oder ob ein erneuter Login erm�glicht wird.
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

			ArrayList<String> userData = Load.TryLoadUser(user, charArrayToString(pswd));

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
 * Methode zur Umwandlung von CharArrays in Strings. 
 * 
 * @param pswd
 * @return zu String konvertiertes CharArray
 */
	private static String charArrayToString(char[] pswd) {
		
		String retVal = "";
		for(char i :pswd){
			retVal = retVal + Character.toString(i);
		}
		return retVal;
	}

	/**
	 * �berpr�fung, ob in den �bergabewerten Werte gespeichert sind und diese
	 * keine Leerzeichen enthalten.<br>
	 * Auf Fehler wird durch eine ErrorMessage, die Fehlerabh�ngig aufgebaut
	 * wird, hingewiesen.
	 * 
	 * @param pswd
	 * @param user
	 * @return Booleanwert f�r notNullorWhitespace
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

			JOptionPane.showMessageDialog(null, pswdError + userError
					+ "\n (Keine Leerzeichen)", "Eingabefehler",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

	}

	/**
	 * Pr�ft, ob Leerzeichen im zu �berpr�fenden Char-Array vorhanden sind.<br>
	 * Es wird mit einer foreach schleife durchlaufen.
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
	 * Pr�ft, ob Leerzeichen im zu �berpr�fenden String vorhanden sind.<br>
	 * Der String wird in ein Char-Array umgewandelt. <br>
	 * Dieses wird an die Methode noWhiteSpace �bergeben.<br>
	 * Der zur�ckgegebene Wert wird dann ebenfalls zur�ckgegeben.
	 * 
	 * @param checkArray
	 * @return Funktionswert von noWhiteSpace(char [] checkArray)
	 */
	private static boolean noWhiteSpace(String checkString) {

		char[] charArray = checkString.toCharArray();

		return noWhiteSpace(charArray);
	}

	/**
	 * �berpr�fung auf Ziffern im �bergebenen String. <br>
	 * Der String wird zuerst in ein Char-Array umgewandelt, sodass die Zeichen
	 * einzeln betrachtet werden k�nnen. <br>
	 * <br>
	 * 
	 * Nun wird dieses Char-Array durchlaufen.<br>
	 * Jedes Einzelzeichen wird versucht in einen Integerwert umzuwandeln. Gelingt dies
	 * wird sofort "true" zur�ckgegeben.<br>
	 * Schl�gt es aber fehl, wird im Catch-Block die retVal auf false gesetzt.
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
/**
 * �berpr�fung der Passwortregeln im Dialog "ChangePasswordDialog". <br>
 * Es wird auf Leerzeichen und die L�nge des neuen Passwortes getestet.<br>
 * Die L�nge des neuen Passwortes soll zwischen 5 und 15 zeichen liegen.<br>
 * 
 * @param newPswd1
 * @return isRegelKonform 
 */
	public static boolean RegelCheck(char[] newPswd1) {
		boolean isRegelKonform = false;

		if (noWhiteSpace(newPswd1) && newPswd1.length >= 5
				&& newPswd1.length <= 15) {
			isRegelKonform = true;

		}

		return isRegelKonform;

	}

}
