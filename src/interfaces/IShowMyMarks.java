package interfaces;
/**  
 * ArrayList in den Parametern immer als Erstes angeben. Elementar f�r den Master!
 *
 */
import java.util.ArrayList;


public interface IShowMyMarks {	
	
	public ArrayList <String > getMyMarks(String vorlesung);
	public double getMyTotalAverage(ArrayList<String > allMarks);
	

}
