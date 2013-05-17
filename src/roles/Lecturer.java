package roles;

import java.util.ArrayList;

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

	/* Konstruktor ------------------------------------- */

	public Lecturer(String name, String firstname, String id, String course) {
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

	@Override
	public ArrayList<String> getMyLectures() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ArrayList<String>> getAllStudentsOfLecture(String SelectedLecture) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getLectureAverage(String SelectedLecture) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAllAverage(ArrayList<String> allMyLectures) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<ArrayList<String>> getAllFailedOrUnmarkedStudents(
			ArrayList<String> allMyLectures) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* Implementationen aus Interfaces ---------------- */	
	
	/* Methoden aus Oberklasse ------------------------------- */
	/* Eigene Methoden ------------------------------- */
	
	
}
