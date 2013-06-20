package interfaces;

import java.util.ArrayList;

/**
* Rechte für die Erstellung neuer Benutzer, Vorlesungen und Zuweisung selbiger.<br>
* Wird implementiert von der Rolle HeadOfDepartment.<br>
*<b> ArrayList in den Parametern immer als Erstes angeben. Elementar für den Master!</B>
*/
public interface IHead {
 public ArrayList <String> getAllStaffOfCourse();
}
