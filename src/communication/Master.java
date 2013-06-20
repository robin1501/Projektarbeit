package communication;

import gui.main.LecturerG;
import gui.main.LoginG;
import gui.main.StudentG;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import data.Save;

import roles.HeadOfDepartment;
import roles.Lecturer;
import roles.Professor;
import roles.Student;
import roles.User;

/**
 * Die Masterklasse dient zur Trennung zwischen der GUI und der restlichen
 * Codeschichten, sodass es für die jeweiligen Seite unwichtig ist, was auf der
 * anderen Seite passiert.<br>
 * Durch die klare Schnittstellendefinition kann über sehr wenige Methoden die
 * komplette Interaktion abgehandelt werden.<br>
 * Hierzu wird das Konzept der Polymorphie genutzt.
 */
public class Master {

	/*
	 * Erstellen und Handeln
	 *  des DynamischenUsers
	 */

	private static User myDynamicUser = null;
	private static String guiName = null;
	private static StudentG GUIS = null;
	private static LecturerG GUIL = null;	
	private static String whoAmI = null;
	/**
	 * Gibt whoAmI zurück.<br>
	 * Dies wird in der Lecturer GUI benötigt.
	 * @return whoAmI
	 */
	public static String getWhoAmI() {
		return whoAmI;
	}
	

	/**
	 * In der getUserClass() wird die aktuelle Klasse des Users als String
	 * zurück gegeben.
	 * 
	 * @return (String) Name des dynamischen Objekts
	 */
	public String getUserClass() {

		return myDynamicUser.getClass().toString();
	}

	/**
	 * Der User wird anhand des Login gesetzt.<br>
	 * Übergeben werden alle Parameter, die für die Auwsahl der Rolle und die
	 * Objekterzeugung benötigt werden, sodass "myDynamicUser" richtig
	 * initialisiert wird. <br>
	 * Hierbei wird auch die richtige GUI aufgerufen. User selbst ist eine
	 * abstrakte Klasse und dient hier der Polymorphie.<br>
	 * Bei fehlerhafter Zuweisung wird eine Message ausgegeben, die auf diesen
	 * Umstand hinweist.
	 * 
	 * @param role
	 * @param isHead
	 * @param name
	 * @param firstname
	 * @param id
	 * @param course
	 */
	public static void setMyDynamicUser(char role, String isHead, String name,
			String firstname, String id, String course)
			throws NullPointerException {

		if (!isHead.equals("true")) {
			switch (role) {

			case 'S':
				whoAmI = "S";
				myDynamicUser = new Student(name, firstname, id, course);
				guiName = "Student";
				GUIS = new StudentG();
				GUIS.setVisible(true);
				break;

			case 'D':
				whoAmI = "D";
				myDynamicUser = new Lecturer(name, firstname, id, course);
				guiName = "Lecturer";
				GUIL = new LecturerG();
				GUIL.setVisible(true);
				break;

			case 'P':
				whoAmI = "P";
				myDynamicUser = new Professor(name, firstname, id, course);
				guiName = "Professor";
				GUIL = new LecturerG();
				GUIL.setVisible(true);
				break;

			default:
				JOptionPane
						.showMessageDialog(
								null,
								"Fehler bei der Rollenvergabe. Wenden Sie sich an Ihren Studiengangsleiter",
								"Fehler bei der Rollenvergabe",
								JOptionPane.ERROR_MESSAGE);

			}

		} else {
			whoAmI = "H";
			myDynamicUser = new HeadOfDepartment(name, firstname, id, course);
			guiName = "HeadOfDepartment";
			GUIL = new LecturerG();
			GUIL.setVisible(true);
		}
	}

	/*
	 * Methoden aufrufe
	 * -----------------------------------------------------------------
	 */
	/**
	 * <p>
	 * <b>Es folgen nun die Methoden der Masterklasse, die die Schnittstellen
	 * der Klasse bilden.</b><br>
	 * Es gibt vier Hauptmethoden für die Methoden, welche die double, String und ArrayList
	 * als Rückgabewerte haben, wie auch für Voidaufrufe.<br>
	 * Durch die Interfaces wird definiert in welcher Art alle Objektmethoden
	 * aufgebaut und benannt sind.<br>
	 * Dadurch ist es möglich die gewünschte Methode zusammenzustellen und
	 * aufzurufen.<br>
	 * <b>Diese Definitionen einzuhalten ist essentiel für die
	 * Funktionsfähigkeit dieser Aufrufe.</b><br>
	 * </p>
	 * <br>
	 * <p>
	 * Die Funktion <b>getMyDouble</b> kann immer dann angewendet werden, wenn
	 * ein Doublewert erwartet wird.<br>
	 * Es muss der gewünschte Methodenname, sowie die erwarteten Parameter
	 * mitgegeben werden.<br>
	 * Über IF-Else-Verzweigungen wird nun der Methodenaufruf realisiert.<br>
	 * Bei fehlerhaftem Aufruf wird per MessageBox auf die Fehlerursache
	 * hingewiesen.
	 * 
	 * </p>
	 * 
	 * @param methodName
	 * @param parameterArrayList
	 * @param parameterString
	 * @return double
	 */
	@SuppressWarnings("finally")
	public static double getMyDouble(String methodName,
			ArrayList<String> parameterArrayList, String parameterString) {

		double retValue = 0;

		try {
			if (parameterString == null && parameterArrayList == null) {

				retValue = (double) myDynamicUser.getClass()
						.getMethod(methodName).invoke(myDynamicUser);

			} else if (parameterString == null && parameterArrayList != null) {

				retValue = (double) myDynamicUser.getClass()
						.getMethod(methodName, ArrayList.class)
						.invoke(myDynamicUser, parameterArrayList);
			} else {

				retValue = (double) myDynamicUser.getClass()
						.getMethod(methodName, String.class)
						.invoke(myDynamicUser, parameterString);
			}

		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {

			ErroMessenger();

		} finally {

			return retValue;
		}

	}

	/**
	 * Die Funktion <b>getMyStrings</b> kann immer dann angewendet werden, wenn
	 * ein Rückgabewert vom Typ String erwartet wird.<br>
	 * Es muss der gewünschte Methodenname ,sowie die erwarteten Parameter
	 * mitgegeben werden.<br>
	 * Über IF-Else-Verzweigungen wird nun der Methodenaufruf realisiert.<br>
	 * Bei fehlerhaftem Aufruf wird per MessageBox auf die Fehlerursache
	 * hingewiesen.
	 * 
	 * @param methodName
	 * @param parameterArrayList
	 * @param parameterString
	 * @return String
	 */

	public static String getMyStrings(String methodName) {
		String retVal = null;

		try {
			retVal = (String) myDynamicUser.getClass().getMethod(methodName)
					.invoke(myDynamicUser);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {

			ErroMessenger();
		}

		return retVal;
	}

	/**
	 * Die Funktion <b>getMyArrayList</b> kann immer dann angewendet werden,
	 * wenn eine ArrayList erwartet wird.<br>
	 * Es muss der gewünschte Methodenname ,sowie die erwarteten Parameter
	 * mitgegeben werden.<br>
	 * Über IF-Else-Verzweigungen wird nun der Methodenaufruf realisiert.<br>
	 * Bei fehlerhaftem Aufruf wird per MessageBox auf die Fehlerursache
	 * hingewiesen.
	 * 
	 * @param methodName
	 * @param parameterArrayList
	 * @param parameterString
	 * @return ArrayList
	 */
	@SuppressWarnings({ "finally", "unchecked" })
	public static ArrayList<String> getMyArrayList(String methodName,
			ArrayList<String> parameterArrayList, String parameterString) {

		ArrayList<String> retVal = new ArrayList<String>();

		try {

			if (parameterString == null && parameterArrayList == null) {

				retVal = (ArrayList<String>) myDynamicUser.getClass()
						.getMethod(methodName).invoke(myDynamicUser);

			} else {
				if (parameterArrayList == null && parameterString != null) {

					retVal = (ArrayList<String>) myDynamicUser.getClass()
							.getMethod(methodName, String.class)
							.invoke(myDynamicUser, parameterString);
				} else {
					retVal = (ArrayList<String>) myDynamicUser.getClass()
							.getMethod(methodName, ArrayList.class)
							.invoke(myDynamicUser, parameterArrayList);
				}

			}

		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {

			ErroMessenger();

		} finally {
			return retVal;
		}
	}

	/**
	 * Die Funktion <b>getMyTwoDimensionalArrayList</b> kann immer dann
	 * angewendet werden, wenn eine 2 Dimensionale ArrayList erwartet wird.<br>
	 * Es muss der gewünschte Methodenname ,sowie die erwarteten Parameter
	 * mitgegeben werden.<br>
	 * Über IF-Else-Verzweigungen wird nun der Methodenaufruf realisiert.<br>
	 * Bei fehlerhaftem Aufruf wird per MessageBox auf die Fehlerursache
	 * hingewiesen.
	 * 
	 * @param methodName
	 * @param parameterArrayList
	 * @param parameterString
	 * @return 2 Dimensionale ArrayList
	 */
	@SuppressWarnings({ "finally", "unchecked" })
	public static ArrayList<ArrayList<String>> getMyTwoDimensionalArrayList(
			String methodName, ArrayList<String> parameterArrayList,
			String parameterString) {

		ArrayList<ArrayList<String>> retVal = new ArrayList<ArrayList<String>>();

		try {

			if (parameterString == null && parameterArrayList == null) {

				retVal = (ArrayList<ArrayList<String>>) myDynamicUser
						.getClass().getMethod(methodName).invoke(myDynamicUser);

			} else {
				if (parameterArrayList == null && parameterString != null) {

					retVal = (ArrayList<ArrayList<String>>) myDynamicUser
							.getClass().getMethod(methodName, String.class)
							.invoke(myDynamicUser, parameterString);
				} else {
					retVal = (ArrayList<ArrayList<String>>) myDynamicUser
							.getClass().getMethod(methodName, ArrayList.class)
							.invoke(myDynamicUser, parameterArrayList);
				}

			}

		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {

			ErroMessenger();

		} finally {
			return retVal;
		}
	}

	/**
	 * Die Funktion <b>voidCaller</b> kann immer dann angewendet werden, wenn
	 * eine VoidFunktion aufgerufen wird.<br>
	 * Es muss der gewünschte Methodenname ,sowie die erwarteten Parameter
	 * mitgegeben werden.<br>
	 * Über IF-Else-Verzweigungen wird nun der Methodenaufruf realisiert.<br>
	 * Bei fehlerhaftem Aufruf wird per MessageBox auf die Fehlerursache
	 * hingewiesen.
	 * 
	 * @param methodName
	 * @param parameterString
	 * 
	 */
	public static void voidCaller(String methodName, String parameterString) {

		try {

			if (parameterString == null) {
				myDynamicUser.getClass().getMethod(methodName)
						.invoke(myDynamicUser);

			} else {

				myDynamicUser.getClass().getMethod(methodName, String.class)
						.invoke(myDynamicUser, parameterString);
			}

		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {

			ErroMessenger();

		}
	}

	/**
	 * Öffnet einen JOptionPane die auf einen Fehler hinweist.<br>
	 * Die Funktion wird in den Methoden des dynamischen Users in den
	 * Catch-Blöcken aufgerufen und weißt auf einen fehlerhaften Methodenaufruf
	 * hin.
	 */

	private static void ErroMessenger() {

		JOptionPane.showMessageDialog(null,
				"Aktion konnte nicht ausgeführt werden.",
				"Fehler beim Methodenaufruf", JOptionPane.ERROR_MESSAGE);

	}

	/**
	 * Weiterreichen der Loginanfrage an die InsertAndValidation-Klasse.<br>
	 * Rückgabe des Wertes für die LoginGui.<br>
	 * Genaue Beschreibung jeweils in der Klasse und der Gui selbst.
	 * 
	 * 
	 * @param pswd
	 * @param user
	 * @return Funktionswert des InsertAndValidationChecks
	 */
	public static boolean Login(char[] pswd, String user) {

		return InsertAndValidationChecks.Login(pswd, user);

	}

	/**
	 * Aufruf der ChangePasswort-Funktion in der Save-Klasse.<br>
	 * ChangePasswort wird vom ChangePasswortDialog aufgerufen, der über das
	 * PersonalDataP Panel aufgerufen wird.<br>
	 * Es bekommt das, bereits auf Regelkonformität geprüfte, Passwort übergeben
	 * und gibt es zusammen mit der User-Id,<br>
	 * die hier aus der Klasse gezogen wird,an die Save-Klasse weiter.<br>
	 * <br>
	 * Ist die Speicherung erfolgreich, wird das aktuelle Fenster(Ermittlung
	 * über die User-Klasse) geschlossen und ein neues Loginfenster aufgerufen. <br>
	 * Das jede HauptGUI einen Disposeaufruf hat, wird über das
	 * Interface "IDisposeMe" sichergestellt.
	 * 
	 * @param pswd
	 */
	public static void ChangePassword(String pswd) {

		if (Save.ChangePassword(pswd, getMyStrings("getId"))) {

			JOptionPane
					.showMessageDialog(
							null,
							"Passwortänderung erfolgreich.\n Das Fenster wird sich automatisch schließen, sodass Sie sich neu einloggen können.\n Ihre Änderungen werden gespeichert.",
							"Passwortänderung", JOptionPane.INFORMATION_MESSAGE);

			disposeGUI();

		}

	}

	/**
	 * Es wird ein neues Login Fenster geöffnet.<br>
	 * Dies geschieht beim Abmeldevorgang sowie bei erfolgreicher
	 * Passwortänderung. <br>
	 * 
	 */
	private static void openNewLogin() {
		LoginG neuesLogin = new LoginG();
		neuesLogin.setVisible(true);

	}

	/**
	 * Disposen der aktuellen HauptGui.<br>
	 * Das Disposing hat GUI-Intern das Speichern der jeweiligen Vorgänge zur
	 * Folge.<br>
	 * Danach wird ein neues LogIn Fenster über die Funktion "openNewLogin"
	 * geöffnet.<br>
	 */
	public static void disposeGUI() {

		if (GUIS != null) {
			GUIS.disposeMeFromExtern();
		} else {
			GUIL.disposeMeFromExtern();
		}
		openNewLogin();
	}

}