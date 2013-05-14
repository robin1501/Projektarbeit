package interfaces;

import java.util.ArrayList;

/**  
 * Rechte f�r die eigene Notenansicht.<br>
 * Wird implementiert von der Rolle Student.<br>
 *<b> ArrayList in den Parametern immer als Erstes angeben. Elementar f�r den Master!</B>
 * 
 *
 */
public interface IShowMyMarks {	
	
	public ArrayList <String > getMyMarks(String vorlesung);
	public double getMyTotalAverage(ArrayList<String > allMarks);
	

}
