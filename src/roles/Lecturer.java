package roles;

import java.io.File;
import java.util.ArrayList;

import data.Data;
import data.Load;

import interfaces.ILecturer;
/**
 * Die Lecturer Klasse beschreibt die M�glichkeiten eines Dozenten in der Studentenverwaltung.
 * Er kann: seine Vorlesungen anzeigen lassen, alle Studenten die eine seiner Vorlesungen besuchen anzeigen lassen,
 * den erzielten Durchschnitt, der Studenten, aus einer oder aller seiner Vorlesungen ausgeben lassen und alle 
 * Studenten, die durchgefallen bzw noch keine Note besitzen ausgeben lassen.* 
 *
 */
public class Lecturer extends User implements ILecturer {

	/* Variablen -------------------------------------- */

	private String name;
	private String firstname;
	private String id;
	private String course;
	public static File userFile = Data.fileReplacer("stud_info.csv");
	public static File markFile = Data.fileReplacer("mark_info.csv");

	/* Konstruktor ------------------------------------- */
	/**
	 * Im Konstruktor werden die �bergeben Spezifikationen �ber den aktuell eingeloggten User in die privaten-
	 * Klassenvariabeln gespeichert.
	 * @param name
	 * @param firstname
	 * @param id
	 * @param course
	 */
	public Lecturer(String name, String firstname, String id, String course) {
		super();
		this.name = name;
		this.firstname = firstname;
		this.id = id;
		this.course = course;

	}

	/* getter und setter ----------------------------- */
	/**
	 * Mit der getFirstname-getter-Methode wird der Vorname zur�ckgegeben 
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * Mit der getCourse-getter-Methode wird der Kurs zur�ckgegeben 
	 */
	public String getCourse() {
		return course;
	}
	/**
	 * Mit der getName-getter-Methode wird der Nachname zur�ckgegeben 
	 */
	public String getName() {
		return name;
	}
	/**
	 * Mit der getId-getter-Methode wird die UserID zur�ckgegeben 
	 */
	public String getId() {
		return id;
	}
	/**
	 * Mit der getMyLectures Methode wird lediglich die, in der Load Klasse beinhaltete getLectures Methode 
	 * aufgerufen, die die Vorlesungen des Dozenten oder Professors, dessen ID als �bergabeparameter mit �bergeben 
	 * wurde, zur�ckliefert.
	 * Diese werden dann als r�ckgabe dieser Methode zur�ckgegeben.
	 */
	@Override
	public ArrayList<String> getMyLectures() {
		ArrayList<String> Lectures = Load.getLectures(id);		
		return Lectures;
	}
	/**
	 * Die getAllStudentsOfLecture Methode geht mit einer for-Schleife alle Daten der mark_info Datei durch,
	 * unt filtert die Studenten und Noten raus, die die Vorlesung belegt haben, die der Methode �bergeben worden ist
	 * und gibt diese in zweidimensionalen ArrayList mit jeweils der UserID, der Vorlesung und der Note zur�ck.
	 */
	@Override
	public ArrayList<ArrayList<String>> getAllStudentsOfLecture(String SelectedLecture) {
		ArrayList<ArrayList<String>> data = Data.read(markFile);		
		ArrayList<String> Student;
		ArrayList<ArrayList<String>> Students = new ArrayList<ArrayList<String>>();
		for(int j=0;j<data.size();j++){
			if(data.get(j).get(2).toString().equals(SelectedLecture)){
				Student= new ArrayList<String>();
				Student.add(data.get(j).get(0).toString());
				Student.add(data.get(j).get(2).toString());
				Student.add(data.get(j).get(3).toString());
				Students.add(Student); 
			}
		}		
		return Students;
	}
	/**
	 * Die getLectureAverage Methode bekommt als �bergabeParameter eine Vorlesung, mit der sie die mark_info Datei
	 * filtert und die zugeh�rigen Noten, falls sie nicht "0" sind zusammenfasst. Dies geschieht durch for-
	 * Schleifendurchl�ufen bei dem jede gesetzte Note(!= 0) gleichzeitig eine Variable hochz�hlt die zur 
	 * schlussendlichen berechnung des Vorlesungsdurchschnitt dient, der am ende zur�ckgegeben werden soll. 
	 */
	@Override
	public double getLectureAverage(String SelectedLecture) {
		ArrayList<ArrayList<String>> data = Data.read(markFile);
		double sumMarks=0,average=0;
		int count=0;
		for(int i=0;i<data.size();i++){
			if(data.get(i).get(2).toString().equals(SelectedLecture) && data.get(i).get(1).toString().equals(course)){
				if(Double.parseDouble(data.get(i).get(3).toString())!=0){
					sumMarks= sumMarks + Double.parseDouble(data.get(i).get(3).toString());
					count ++;
				}
			}
		}
		average = sumMarks / count *100;
		average= Math.round(average);	
		average = average /100;
		return average;
	}
	/**
	 * Die getAllAverage Methode geht auf die gleiche Weise wie getLectureAverage vor, jedoch vergr��ert sich
	 * Laufzeitkomplexit�t um die Anzahl der Elemente in der ArrayList, die als Parameter �bergeben wurde, welche
	 * sich als verschachtelte for-Schleife abzeichnet.
	 * Anschlie�end wird der gerundete Durchschnitt zur�ckgegeben.
	 * 
	 */
	@Override
	public double getAllAverage(ArrayList<String> allMyLectures) {
		ArrayList<ArrayList<String>> data = Data.read(markFile);
		double sumMarks=0,average=0;
		int count=0;
		for(int i=0;i<data.size();i++){
			for(int j =0; j<allMyLectures.size();j++){
				if(data.get(i).get(2).toString().equals(allMyLectures.get(j).toString())){
					if(Double.parseDouble(data.get(i).get(3).toString())!=0){
						sumMarks= sumMarks + Double.parseDouble(data.get(i).get(3).toString());
						count ++;
					}
				}
			}
		}
		average = sumMarks / count *100;
		average= Math.round(average);	
		average = average /100;	
		return average;
	}
	/**
	 * Mit der getAllFailedOrUnmarkedStudents Methode werden mit einer for-Schleife alle Vorlesungen aus der 
	 * mark_info datei mit den Vorlesungen aus der �bergebenen Liste abgeglichen und alle Studenten mit 
	 * einer noch nicht gesetzten Note oder einer die schlechter als 4 ist, mit ihrer UserID, der entsprechenden vorlesung 
	 * und der Note in eine zweidimensionale ArrayList gespeichert, die schlussendlich zur�ckgegeben wird.
	 */
	@Override
	public ArrayList<ArrayList<String>> getAllFailedOrUnmarkedStudents(
			ArrayList<String> allMyLectures) {
		ArrayList<String> Student;
		ArrayList<ArrayList<String>> failStud = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> data = Data.read(markFile);
		for(int i=0;i<data.size();i++){
			for(int j =0; j<allMyLectures.size();j++){
				if(data.get(i).get(2).toString().equals(allMyLectures.get(j).toString())){
					if(Double.parseDouble(data.get(i).get(3).toString()) ==0 || Double.parseDouble(data.get(i).get(3).toString())>4)
					{
						Student = new ArrayList<String>();
						Student.add(data.get(i).get(0).toString());
						Student.add(data.get(i).get(2).toString());
						Student.add(data.get(i).get(3).toString());
						failStud.add(Student);						
					}
					
				}
			}
		}
		return failStud;
	}
	
	/* Implementationen aus Interfaces ---------------- */	
	
	/* Methoden aus Oberklasse ------------------------------- */
	/* Eigene Methoden ------------------------------- */
	
	
}
