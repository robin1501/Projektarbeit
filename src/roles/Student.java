package roles;

import java.util.ArrayList;
import interfaces.IShowMyMarks;

/** 
 * Ein Student kann seine pers�nlichen Daten anzeigen,seine Noten in den unterschiedlichen Vorlesungen anzeigen.
 * Noten die schlechter als 4,0 sind werden farblich makiert. * 
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

	@Override
	public ArrayList <ArrayList<String>> getMyMarks(String vorlesung) {
		// id bitte aus dem Objekt ziehen 
		// sowas wie "student id und vorlesung sollen alle seine noten und vorlesungen hoch geben "deutsch" "2" usw
		
		
		return null;
	}

	@Override
	public double getMyTotalAverage() {
		//durchschnitt aus den Noten berechene die in getMyMarks gezogen wurden 
		return 0;
	}

	/* Methoden aus Oberklasse ------------------------------- */
	
	/* Eigene Methoden ------------------------------- */

}
