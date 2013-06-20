package roles;

import java.util.ArrayList;

import communication.Master;
import data.Load;

import interfaces.IShowMyMarks;

/**
 * Ein Student kann seine persönlichen Daten anzeigen,seine Noten in den
 * unterschiedlichen Vorlesungen anzeigen. Noten die schlechter als 4,0 sind
 * werden farblich makiert. *
 * 
 */

public class Student extends User implements IShowMyMarks {

	/* Variablen -------------------------------------- */

	private String name;
	private String firstname;
	private String id;
	private String course;

	/* Konstruktor ------------------------------------- */

	public Student(String name, String firstname, String id, String course) {
		super();
		this.name = name;
		this.firstname = firstname;
		this.id = id;
		this.course = course;

	}

	/* getter und setter ----------------------------- */

	public String getFirstname() {
		return firstname;
	}

	public String getCourse() {
		return course;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	/* Implementationen aus Interfaces ---------------- */
	/*
	 * Kommentar von Daniel ---> bitte den Schnitt auf 2 nach kommastellen
	 * gerundet anzeigen!!!!
	 * 
	 * (non-Javadoc)
	 * 
	 * @see interfaces.IShowMyMarks#getMyMarks(java.lang.String)
	 */
	@Override
	public ArrayList<ArrayList<String>> getMyMarks() {
		// id bitte aus dem Objekt ziehen
				// sowas wie
				// "student id und vorlesung sollen alle seine noten und vorlesungen hoch geben "deutsch" "2"
				// usw
				ArrayList<ArrayList<String>> allMarks = Load.getMarks(id);
				
				return allMarks;
	}

	@Override
	public double getMyTotalAverage() {

		// durchschnitt aus den Noten berechene die in getMyMarks gezogen wurden
		ArrayList<ArrayList<String>> allMarks = Load.getMarks(id);
		double sumMarks = 0;
		int i = 0;
		while (i < allMarks.size()) {

			sumMarks = sumMarks
					+ Integer.parseInt(allMarks.get(i).get(1).toString());
			i++;
		}
		sumMarks = sumMarks / i;
		return sumMarks;
	}

	/* Methoden aus Oberklasse ------------------------------- */

	/* Eigene Methoden ------------------------------- */

}
