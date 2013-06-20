package interfaces;

import java.util.ArrayList;
/**
* Rechte f�r Gesamt�bersicht an Noten eines Studiengangs.<br>
* Wird implementiert von der Rolle Professor und HeadOfDepartment.<br>
*<b> ArrayList in den Parametern immer als Erstes angeben. Elementar f�r den Master!</B>
*/

public interface IProf {
	
	public ArrayList <ArrayList <String >> AllFailedStudentsOfCourse();
	public ArrayList <String > getAllCourseLectures();
	
	
}
