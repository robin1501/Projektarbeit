package interfaces;

import java.util.ArrayList;

/**  
 * Rechte für die eigene Notenansicht.<br>
 * Wird implementiert von der Rolle Student.<br>
 *<b> ArrayList in den Parametern immer als Erstes angeben. Elementar für den Master!</B>
 * 
 *
 */
public interface IShowMyMarks {	
	
	public ArrayList <ArrayList<String >> getMyMarks();
	public double getMyTotalAverage();
	

}
