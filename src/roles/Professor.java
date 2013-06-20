package roles;

import java.io.File;
import java.util.ArrayList;

import data.Data;
import data.Load;
import interfaces.ILecturer;
import interfaces.IProf;

public class Professor extends User implements ILecturer,IProf {

	/* Variablen -------------------------------------- */
	
	private String name;
	private String firstname;
	private String id;
	private String course;
	public static File userFile = Data.fileReplacer("stud_info.csv");
	public static File markFile = Data.fileReplacer("mark_info.csv");	

	/* Konstruktor ------------------------------------- */
	
	public Professor(String name, String firstname, String id, String course) {
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
	/* Immer dran denken was du in der Klasse schon hast als suchparameter m�sste eigentlich passen*/


	@Override
	public ArrayList<ArrayList<String>> AllFailedStudentsOfCourse() {
		ArrayList<String> stud = new ArrayList<String>();
		ArrayList<ArrayList<String>> failStud = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> data = Data.read(markFile);
		for(int i=0;i<data.size();i++){
			if(data.get(i).get(1).toString().equals(course)){
				if(Double.parseDouble(data.get(i).get(2).toString())>4)
				{
					stud.add(data.get(i).get(0).toString());
					stud.add(data.get(i).get(2).toString());
					stud.add(data.get(i).get(3).toString());
					failStud.add(stud);
					stud = null;
				}
					
			}
			
		}
		return failStud;
	}

	@Override
	public ArrayList<String> getAllCourseLectures() {
		ArrayList<ArrayList<String>> data = Data.read(markFile);
		ArrayList<String>Lectures = new ArrayList<String>();
		for(int i =0;i<data.size();i++){
			if(data.get(i).get(1).toString().equals(course)){
				boolean alreadyexist = false;
				for(int j = 0; j<Lectures.size();j++){
					if(data.get(i).get(1).toString().equals(Lectures.get(j).toString()))
						alreadyexist = true;
				}
				if(alreadyexist == false)
				Lectures.add(data.get(i).get(3).toString());				
			}
		}
		return Lectures;
	}

	@Override
	public ArrayList<String> getMyLectures() {
		ArrayList<String> myLectures= Load.getLectures(id);		
		return myLectures;
	}

	@Override
	public ArrayList<ArrayList<String>> getAllStudentsOfLecture(String SelectedLecture) {
		ArrayList<ArrayList<String>> data = Data.read(userFile);
		ArrayList<String> Student= new ArrayList<String>();
		ArrayList<ArrayList<String>> Students = new ArrayList<ArrayList<String>>();
		for(int j=0;j<data.size();j++){
			if(data.get(j).get(5).equals(SelectedLecture)){
				for(int i=0;i<3;i++){
					Student.add(data.get(j).get(i));
				}Students.add(Student);
			}
		}		
		return Students;
	}

	@Override
	public double getLectureAverage(String SelectedLecture) {
		ArrayList<ArrayList<String>> data = Data.read(markFile);
		double sumMarks=0,average=0;
		int count=0;
		for(int i=0;i<data.size();i++){
			if(data.get(i).get(2).toString().equals(SelectedLecture)){
				if(Double.parseDouble(data.get(i).get(2).toString())!=0){
					sumMarks= sumMarks + Integer.parseInt(data.get(i).get(3).toString());
					count ++;
				}
			}
		}
		average = sumMarks / count;
		return average;
	}

	@Override
	public double getAllAverage(ArrayList<String> allMyLectures) {
		ArrayList<ArrayList<String>> data = Data.read(markFile);
		double sumMarks=0,average=0;
		int count=0;
		for(int i=0;i<data.size();i++){
			for(int j =0; j<allMyLectures.size();j++){
				if(data.get(i).get(2).toString().equals(allMyLectures.get(j).toString())){
					if(Double.parseDouble(data.get(i).get(2).toString())!=0){
						sumMarks= sumMarks + Double.parseDouble(data.get(i).get(3).toString());
						count ++;
					}
				}
			}
		}
		average = sumMarks / count;
		return average;
	}

	@Override
	public ArrayList<ArrayList<String>> getAllFailedOrUnmarkedStudents(
			ArrayList<String> allMyLectures) {
		ArrayList<String> stud = new ArrayList<String>();
		ArrayList<ArrayList<String>> failStud = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> data = Data.read(markFile);
		for(int i=0;i<data.size();i++){
			for(int j =0; j<allMyLectures.size();j++){
				if(data.get(i).get(2).toString().equals(allMyLectures.get(j).toString())){
					if(Double.parseDouble(data.get(i).get(2).toString())!=0 || Double.parseDouble(data.get(i).get(2).toString())>4)
					{
						stud.add(data.get(i).get(0).toString());
						stud.add(data.get(i).get(2).toString());
						stud.add(data.get(i).get(3).toString());
						failStud.add(stud);
						stud = null;
					}
					
				}
			}
		}
		return failStud;
	}

	@Override
	public double AverageOfAllLecturesInMyCourse() {
		ArrayList<ArrayList<String>> data = Data.read(markFile);
		double sumMarks=0,average=0;
		int count=0;
		for(int i=0;i<data.size();i++){
			if(data.get(i).get(2).toString().equals(course)){
				if(Double.parseDouble(data.get(i).get(2).toString())!=0){
					sumMarks= sumMarks + Double.parseDouble(data.get(i).get(3).toString());
					count ++;
				}
			}
			
		}
		average = sumMarks / count;
		return average;
	}

	

	/* Methoden aus Oberklasse ------------------------------- */
	/* Eigene Methoden ------------------------------- */

}
