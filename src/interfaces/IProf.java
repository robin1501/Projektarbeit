package interfaces;

import java.util.ArrayList;
/**  
 * ArrayList in den Parametern immer als Erstes angeben. Elementar für den Master!
 *
 */
public interface IProf {


	public double totalAverageOfAllLecturesInMyCourse(ArrayList <String > allCourseLectures);
	public ArrayList <String > AllFailedStudentsOfCourse(ArrayList <String > allCourseLectures);
	public ArrayList <String > getAllCourseLectures(String myCourse);
	
}
