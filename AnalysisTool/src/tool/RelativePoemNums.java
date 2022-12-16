package tool;

import java.util.ArrayList;

public class RelativePoemNums {

	public ArrayList<rel> relList = new ArrayList<rel>();
	
	public void constructRelList () {
		relList = new ArrayList<rel>();
		relList.add(new rel(479, 712, 102)); // Relative Poem Numbers are currently hard-coded
	}
	
	public int getRelNum(int currColl, int currNum, int changeColl) {
		for (rel r : relList) {
			if (r.nums[currColl] == currNum) {
				System.out.println("Should be 102: " + r.nums[changeColl]);
				return r.nums[changeColl];
			}
		}
		System.out.println("returning default");
		return currNum;
	}
	
	public int nameToNum (String name) {
		if (name.equals(Main.dataHandler.FV)) {
			System.out.println("nameToNum Output: " + 0);
			return 0;
		} else if (name.equals(Main.dataHandler.JP)) {
			System.out.println("nameToNum Output: " + 1);
			return 1;
		} else if (name.equals(Main.dataHandler.P)) {
			System.out.println("nameToNum Output: " + 2);
			return 2;
		} else {
			System.out.println("nameToNum Output: " + (-1));
			return -1;
		}
		
	}
	
	public class rel {
		
		Integer[] nums = new Integer[3];
		
		public rel (int FV, int JP, int P) {
			nums[0] = FV;
			nums[1] = JP;
			nums[2] = P;
		}
	}
	
}
