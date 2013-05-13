package interfaces;

import java.util.ArrayList;

/**  
 * ArrayList in den Parametern immer als Erstes angeben. Elementar für den Master!
 *
 */
public interface ILecturer {
	
	public ArrayList <String >getMyLectures();
	public ArrayList <String > getAllStudentsOfLecture(String SelectedLecture);
	public double getLectureAverage(String SelectedLecture);
	public double getAllAverage(ArrayList<String > allMyLectures);
	public ArrayList <String > getAllFailedOrUnmarkedStudents(ArrayList <String > allMyLectures);
	

}
