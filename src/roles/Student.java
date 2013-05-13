package roles;

import java.util.ArrayList;
import interfaces.IShowMyMarks;

/** 
 * Ein Student kann seine persönlichen Daten anzeigen,seine Noten in den unterschiedlichen Vorlesungen anzeigen.
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
	public ArrayList<String> getMyMarks(String vorlesung) {
		// id bitte aus dem Objekt ziehen 
		return null;
	}

	@Override
	public double getMyTotalAverage(ArrayList <String> allMarks) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* Methoden aus Oberklasse ------------------------------- */
	
	/* Eigene Methoden ------------------------------- */

}
