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
	/**
	 * Im Konstruktor werden die übergeben Spezifikationen über den aktuell eingeloggten User in die privaten-
	 * Klassenvariabeln gespeichert.
	 * @param name
	 * @param firstname
	 * @param id
	 * @param course
	 */
	public Student(String name, String firstname, String id, String course) {
		super();
		this.name = name;
		this.firstname = firstname;
		this.id = id;
		this.course = course;

	}

	/* getter und setter ----------------------------- */
/**
 * Mit der getFirstname-getter-Methode wird der Vorname zurückgegeben 
 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * Mit der getCourse-getter-Methode wird der Kurs zurückgegeben 
	 */
	public String getCourse() {
		return course;
	}
	/**
	 * Mit der getName-getter-Methode wird der Nachname zurückgegeben 
	 */
	public String getName() {
		return name;
	}
	/**
	 * Mit der getId-getter-Methode wird die UserID zurückgegeben 
	 */
	public String getId() {
		return id;
	}

	/* Implementationen aus Interfaces ---------------- */
	/** 
	 * In der Methode geMyMarks() wird die Load Methode getMarks(UserID) aufgerufen, die eine Liste mit den 
	 * gesammten Noten des entsprechenden Users zurückgibt.
	 * Daraufhin wird mit einer for-Schleife geprüft ob eine dieser Noten, "0"(nicht gesetzt) ist.
	 * Fallst dies vorkommt, wird diese durch den String "Keine Note gesetzt" ersetzt um die Anzeige 
	 * ansprechender zu gestallten.
	 */
	@Override
	public ArrayList<ArrayList<String>> getMyMarks() {
		// id bitte aus dem Objekt ziehen
		// sowas wie
		// "student id und vorlesung sollen alle seine noten und vorlesungen hoch geben "deutsch" "2"
		// usw
		ArrayList<ArrayList<String>> allMarks = Load.getMarks(id);
		for(int i =0;i<allMarks.size();i++){
			if(Double.parseDouble(allMarks.get(i).get(1).toString())==0){
				allMarks.get(i).set(1,"Keine Note gesetzt");
			}
		}
		return allMarks;
	}
	/**
	 * In der Methode getMyTotalAverage() werden alle Noten, wie auch in geMyMarks() geladen, die daraufhin 
	 * in einer for-Schleife auf noch nicht gestzte Noten ("0") geprüft werden, um nur wirkliche Noten 
	 * für die Durchschnittsberechnung zu benutzen, gleichzeitig wird eine Countervariable verwaltet, die nur
	 * bei relevanten Noten hochzählt.
	 * nach beendigung der for-Schleife wird der aufsummierte Wert der relevanten Noten durch die Countervariable
	 * geteilt und es wird der daraus resultierende Durchschnitt zurückgegeben.
	 * 
	 */
	@Override
	public double getMyTotalAverage() {
		
		// durchschnitt aus den Noten berechene die in getMyMarks gezogen wurden
		ArrayList<ArrayList<String>> allMarks = Load.getMarks(id);
		double sumMarks = 0;
		int	i=0,count =0;
		while (i < allMarks.size()) {
			if(Double.parseDouble(allMarks.get(i).get(1).toString())!= 0){
				sumMarks = sumMarks + Double.parseDouble(allMarks.get(i).get(1).toString());
				count++;
			}
			i++;
		}
		sumMarks = sumMarks / count *100;		
		sumMarks = Math.round(sumMarks);
		sumMarks = sumMarks /100;
		return sumMarks;
	}

	/* Methoden aus Oberklasse ------------------------------- */

	/* Eigene Methoden ------------------------------- */

}
