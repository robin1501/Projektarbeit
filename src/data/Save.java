package data;
/**
 * 
 *  * Grundsäzliche Save Klasse. 
 *  Es werden ArrayLists heurntergeben die dann je nach art in die CSV gespeichert werden sollen. 
 *  ZB.
 *  Einzelne Teile abändern -- überschreiben
 *  Neue Erstellen *  
 * etc 
 * hier bitte mit den oberen schichten reden was nach unten kommen kann
 * aus meiner sicht
 * Noten (double) --> vermutlich in einer arraylist mit den dazugehörigen namen (müssen wir nochmal im detail sprechen)
 * komplette neue User (arrayLists)
 * 
 * 
 */
public class Save {

	public static boolean ChangePassword(String pswd1, String USerId) {
		 
		return true;
	}
	
	//Ein neuer User wird angelegt
	//übergeben werden die einzelnen Spaltendaten
	public static boolean addUser(String[] userData){
		return true;
	}
	
	//Eine Vorlesung wird einem User zugeordnet
	//Übergeben wird die UserID und die jeweilige Vorlesung
	public static boolean assignLectureTo(String id, String lecture){
		return true;
	}
	
	//Speicherung neuer Noten für Studenten
	public static boolean saveMarks(){
		return true;
	}
	
}
