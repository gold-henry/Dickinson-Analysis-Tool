package tool;

import java.util.ArrayList;

public class RelativePoemNums {

	public ArrayList<rel> relList = new ArrayList<rel>();
	
	public void constructRelList () {
		relList = new ArrayList<rel>();
		relList.add(new rel(712, 479)); // Relative Poem Numbers are currently hard-coded
	}
	
	public int getRelNum(int currColl, int currNum, int changeColl) {
		for (rel r : relList) {
			if (r.nums[currColl] == currNum) {
				return r.nums[changeColl];
			}
		}
		
		return currNum;
	}
	
	public int nameToNum (String name) {
		if (name.equals(Main.dataHandler.FV)) {
			System.out.println("nameToNum Output: " + 0);
			return 0;
		} else if (name.equals(Main.dataHandler.JP)) {
			System.out.println("nameToNum Output: " + 1);
			return 1;
		} else {
			System.out.println("nameToNum Output: " + (-1));
			return -1;
		}
		
	}
	
	public class rel {
		
		Integer[] nums = new Integer[2];
		
		public rel (int FV, int JP) {
			nums[0] = FV;
			nums[1] = JP;
		}
	}
	
}
