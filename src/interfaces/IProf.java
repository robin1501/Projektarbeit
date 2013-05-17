package interfaces;

import java.util.ArrayList;
/**
* Rechte für Gesamtübersicht an Noten eines Studiengangs.<br>
* Wird implementiert von der Rolle Professor und HeadOfDepartment.<br>
*<b> ArrayList in den Parametern immer als Erstes angeben. Elementar für den Master!</B>
*/

public interface IProf {


	public double totalAverageOfAllLecturesInMyCourse(ArrayList <String > allCourseLectures);
	public ArrayList <ArrayList <String >> AllFailedStudentsOfCourse(ArrayList <String > allCourseLectures);
	public ArrayList <String > getAllCourseLectures(String myCourse);
	
}
