package interfaces;
/**
* Rechte f�r die Erstellung neuer Benutzer, Vorlesungen und Zuweisung selbiger.<br>
* Wird implementiert von der Rolle HeadOfDepartment.<br>
*<b> ArrayList in den Parametern immer als Erstes angeben. Elementar f�r den Master!</B>
*/
public interface IHead {
 public void createNewLecture();
 public void assignLectureTo(String nameOfPerson);
 public void createNewMembers();
}
