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
 * Die Masterklasse dient zur Trennung zwischen Gui und dem Rest des Codes. Über
 * den Master können dynamisch die spezifischen Methoden der Rollen aufgerufen
 * und weitergegeben werden.
 * 
 * 
 * 
 */
public class Master {

	/*
	 * Erstellen und Handeln des DynamischenUsers
	 * ---------------------------------------
	 */

	private static User myDynamicUser = null;

	public String getUserClass() {

		return myDynamicUser.getClass().toString();
	}

	public static void setMyDynamicUSer(char role, String isHead, String name,
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

	@SuppressWarnings("finally")
	public static double getMyDouble(String methodName,
			ArrayList<String> parameterArrayList, String parameterString) {

		double retValue = 0;

		try {
			if (parameterString == null) {
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

	@SuppressWarnings({ "finally", "unchecked" })
	public static ArrayList<String> getMyArrayList(String methodName,
			ArrayList<String> parameterArrayList, String parameterString) {

		ArrayList<String> retVal = new ArrayList<String>();

		try {

			if (parameterString == null && parameterArrayList == null) {

				retVal = (ArrayList<String>) myDynamicUser.getClass()
						.getMethod(methodName).invoke(myDynamicUser);

			} else {
				if (parameterArrayList == null) {

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

	private static void ErroMessenger() {

		JOptionPane.showMessageDialog(null,
				"Aktion konnte nicht ausgeführt werden.",
				"Fehler beim Methodenaufruf", JOptionPane.ERROR_MESSAGE);

	}

}
