package interfaces;

import java.util.ArrayList;

/**
* Rechte für die Erstellung der Noten und Ansicht der Durchschnitte des eigenen Kurses.<br>
* Wird implementiert von der Rolle Lecturer,Professor und HeadOfDepartment.<br>
*<b> ArrayList in den Parametern immer als Erstes angeben. Elementar für den Master!</B>
*/
public interface ILecturer {
	
	public ArrayList <String >getMyLectures();
	public ArrayList <ArrayList <String >> getAllStudentsOfLecture(String SelectedLecture);
	public double getLectureAverage(String SelectedLecture);
	public double getAllAverage(ArrayList<String > allMyLectures);
	public ArrayList <ArrayList <String >> getAllFailedOrUnmarkedStudents(ArrayList <String > allMyLectures);
	

}
