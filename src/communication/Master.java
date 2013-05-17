package communication;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import roles.HeadOfDepartment;
import roles.Lecturer;
import roles.Professor;
import roles.Student;
import roles.User;

/**
 * Die Masterklasse dient zur Trennung zwischen der GUI und der restlichen
 * Codeschichten, sodass es f�r die jeweiligen Seite unwichtig ist, was auf der
 * anderen Seite passiert.<br>
 * Durch die klare Schnittstellendefinition kann �ber sehr wenige Methoden die
 * komplette Interaktion abgehandelt werden.<br>
 * Hierzu wird das Konzept der Polymorphie genutzt.
 */
public class Master {

	/*
	 * Erstellen und Handeln des DynamischenUsers
	 * ---------------------------------------
	 */

	private static User myDynamicUser = null;

	/**
	 * In der getUserClass() wird die aktuelle Klasse des Users als String
	 * zur�ck gegeben.
	 * 
	 * @return (String) Name des dynamischen Objekts
	 */
	public String getUserClass() {

		return myDynamicUser.getClass().toString();
	}

	/**
	 * Der User wird anhand des Login gesetzt.<br>
	 * �bergeben werden alle Parameter die, f�r die Auwsahl der Rolle und die
	 * Objekterzeugung, ben�tigt werden, sodass "myDynamicUser" richtig
	 * initialisiert wird. <br>
	 * User selbst ist eineabstrakte Klasse und dient hier der Polymorphie.<br>
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
			String firstname, String id, String course) {

		if (!isHead.equals("true")) {
			switch (role) {

			case 'S':
				myDynamicUser = new Student(name, firstname, id, course);
				break;
			case 'D':
				myDynamicUser = new Lecturer(name, firstname, id, course);
				break;
			case 'P':
				myDynamicUser = new Professor(name, firstname, id, course);
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
			myDynamicUser = new HeadOfDepartment(name, firstname, id, course);
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
	 * Es gibt vier Hauptmethoden f�r Methoden die double, String und ArrayList
	 * als R�ckgabewerte haben, wie auch f�r Voidaufrufe.<br>
	 * Durch die Interfaces wird definiert in welcher Art alle Objektmethoden
	 * aufgebaut und benannt sind.<br>
	 * Dadurch ist es m�glich die gew�nschte Methode zusammenzustellen und
	 * aufzurufen.<br>
	 * <b>Diese Definitionen einzuhalten ist essentiel f�r die
	 * Funktionsf�higkeit dieser Aufrufe.</b><br>
	 * </p>
	 * <br>
	 * <p>
	 * Die Funktion <b>getMyDouble</b> kann immer dann angewendet werden, wenn
	 * ein Doublewert erwartet wird.<br>
	 * Es muss der gew�nschte Methodenname ,sowie die erwarteten Parameter
	 * mitgegeben werden.<br>
	 * �ber IF-Else-Verzweigungen wird nun der Methodenaufruf realisiert.<br>
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
	 * Die Funktion <b>getMyStrings</b> kann immer dann angewendet werden,
	 * wenn ein R�ckgabewert vom Typ String erwartet wird.<br>
	 * Es muss der gew�nschte Methodenname ,sowie die erwarteten Parameter
	 * mitgegeben werden.<br>
	 * �ber IF-Else-Verzweigungen wird nun der Methodenaufruf realisiert.<br>
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
	 * Es muss der gew�nschte Methodenname ,sowie die erwarteten Parameter
	 * mitgegeben werden.<br>
	 * �ber IF-Else-Verzweigungen wird nun der Methodenaufruf realisiert.<br>
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
	 * Die Funktion <b>getMyTwoDimensionalArrayList</b> kann immer dann angewendet werden,
	 * wenn eine 2 Dimensionale ArrayList erwartet wird.<br>
	 * Es muss der gew�nschte Methodenname ,sowie die erwarteten Parameter
	 * mitgegeben werden.<br>
	 * �ber IF-Else-Verzweigungen wird nun der Methodenaufruf realisiert.<br>
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
	 * Die Funktion <b>voidCaller</b> kann immer dann angewendet werden wenn
	 * eine VoidFunktion aufgerufen wird.<br>
	 * Es muss der gew�nschte Methodenname ,sowie die erwarteten Parameter
	 * mitgegeben werden.<br>
	 * �ber IF-Else-Verzweigungen wird nun der Methodenaufruf realisiert.<br>
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
	 * �ffnet einen JOptionPane die auf einen Fehler hinweist.<br>
	 * Die Funktion wird in den Methoden des dynamischen Users in den
	 * Catch-Bl�cken aufgerufen und wei�t auf einen fehlerhaften Methodenaufruf
	 * hin.
	 */
	
	private static void ErroMessenger() {

		JOptionPane.showMessageDialog(null,
				"Aktion konnte nicht ausgef�hrt werden.",
				"Fehler beim Methodenaufruf", JOptionPane.ERROR_MESSAGE);

	}

}
