package TestCases;

import java.util.ArrayList;

import data.Load;

public class robinTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayList<String> arr = new ArrayList<>();
		arr = Load.TryLoadUser("Uwe_Bein", "5rezudn");
		
		System.out.println(arr.isEmpty() + "");
	}

}
