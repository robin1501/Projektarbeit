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
	public static File userFile = Data.fileReplacer("stud_info.csv");
	public static File markFile = Data.fileReplacer("mark_info.csv");

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
		ArrayList<String> Lectures = Load.getLectures(id);		
		return Lectures;
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
				if(Integer.parseInt(data.get(i).get(2).toString())!=0){
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
					if(Integer.parseInt(data.get(i).get(2).toString())!=0){
						sumMarks= sumMarks + Integer.parseInt(data.get(i).get(3).toString());
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
					if(Integer.parseInt(data.get(i).get(2).toString())!=0 || Integer.parseInt(data.get(i).get(2).toString())!=0)
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
}