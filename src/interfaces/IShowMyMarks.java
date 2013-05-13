package interfaces;
/**  
 * ArrayList in den Parametern immer als Erstes angeben. Elementar für den Master!
 *
 */
import java.util.ArrayList;


public interface IShowMyMarks {	
	
	public ArrayList <String > getMyMarks(String vorlesung);
	public double getMyTotalAverage(ArrayList<String > allMarks);
	

}
