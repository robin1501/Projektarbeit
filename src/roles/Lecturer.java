package roles;

import java.io.File;
import java.util.ArrayList;

import data.Data;
import data.Load;

import interfaces.ILecturer;
/**
 * 
 * 
 *
 */
public class Lecturer extends User implements ILecturer {

	/* Variablen -------------------------------------- */

	private String name;
	private String firstname;
	private String id;
	private String course;
	/**
	 * 
	 */
	public static File userFile = Data.fileReplacer("stud_info.csv");
	public static File markFile = Data.fileReplacer("mark_info.csv");

	/* Konstruktor ------------------------------------- */
	/**
	 * Im Konstruktor werden die übergeben Spezifikationen über den aktuell eingeloggten User in die privaten-
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
	/**
	 * Mit der getMyLectures Methode wird lediglich die, in der Load Klasse beinhaltete getLectures Methode 
	 * aufgerufen, die die Vorlesungen des Dozenten oder Professors, dessen ID als übergabeparameter mit übergeben 
	 * wurde, zurückliefert.
	 * Diese werden dann als rückgabe dieser Methode zurückgegeben.
	 */
	@Override
	public ArrayList<String> getMyLectures() {
		ArrayList<String> Lectures = Load.getLectures(id);		
		return Lectures;
	}
	/**
	 * Die getAllStudentsOfLecture Methode geht mit einer for-Schleife alle Daten der mark_info Datei durch,
	 * unt filtert die Studenten und Noten raus, die die Vorlesung belegt haben, die der Methode übergeben worden ist
	 * und gibt diese in zweidimensionalen ArrayList mit jeweils der UserID, der Vorlesung und der Note zurück.
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
	 * Die getLectureAverage Methode bekommt als übergabeParameter eine Vorlesung, mit der sie die mark_info Datei
	 * filtert und die zugehörigen Noten, falls sie nicht "0" sind zusammenfasst. Dies geschieht durch for-
	 * Schleifendurchläufen bei dem jede gesetzte Note(!= 0) gleichzeitig eine Variable hochzählt die zur 
	 * schlussendlichen berechnung des Vorlesungsdurchschnitt dient, der am ende zurückgegeben werden soll. 
	 */
	@Override
	public double getLectureAverage(String SelectedLecture) {
		ArrayList<ArrayList<String>> data = Data.read(markFile);
		double sumMarks=0,average=0;
		int count=0;
		for(int i=0;i<data.size();i++){
			if(data.get(i).get(2).toString().equals(SelectedLecture)){
				if(Double.parseDouble(data.get(i).get(3).toString())!=0){
					sumMarks= sumMarks + Double.parseDouble(data.get(i).get(3).toString());
					count ++;
				}
			}
		}
		average = sumMarks / count;
		average= (Math.round(average*100))/100;	
		return average;
	}
	/**
	 * Die getAllAverage Methode geht auf die gleiche Weise wie getLectureAverage vor, jedoch vergrößert sich
	 * Laufzeitkomplexität 
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
		average = sumMarks / count;
		average= (Math.round(average*100))/100;		
		return average;
	}

	@Override
	public ArrayList<ArrayList<String>> getAllFailedOrUnmarkedStudents(
			ArrayList<String> allMyLectures) {
		ArrayList<String> stud;
		ArrayList<ArrayList<String>> failStud = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> data = Data.read(markFile);
		for(int i=0;i<data.size();i++){
			for(int j =0; j<allMyLectures.size();j++){
				if(data.get(i).get(2).toString().equals(allMyLectures.get(j).toString())){
					if(Double.parseDouble(data.get(i).get(3).toString()) ==0 || Double.parseDouble(data.get(i).get(3).toString())>4)
					{
						stud = new ArrayList<String>();
						stud.add(data.get(i).get(0).toString());
						stud.add(data.get(i).get(2).toString());
						stud.add(data.get(i).get(3).toString());
						failStud.add(stud);
						
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
