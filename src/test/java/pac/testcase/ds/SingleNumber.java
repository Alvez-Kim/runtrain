package pac.testcase.ds;

public class SingleNumber {
	public static void main(String[] args) {
		int[] a = { 16806, 9374, 12354, 11422, 17798, 8856, 17862, 1310, 1674,
				1380, 16430, 11998, 15828, 11336, 8416, 16288, 16278, 5804,
				566, 19108, 1762, 3388, 10550, 17616, 12744, 7182, 10186,
				18620, 4284, 1934, 15856, 8834, 16744, 17732, 15624, 13342,
				10850, 9838, 7948, 11716, 15126, 5110, 7270, 5174, 10368, 3780,
				12216, 9384, 9138, 8132, 450, 3980, 7804, 6584, 4442, 17530,
				1796, 11872, 8310, 5760, 13462, 9344, 16306, 3018, 19720, 5762 };
		int[] b = {1,1,2,2,3,4,5};
		System.out.println(singleNum(b));
		
		
	}

	public static int singleNumber(int[] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				if (i != j && a[i] == a[j]) {
					break;
				}

				if (j == a.length - 1) {
					return a[i];
				}
			}
		}
		return 0;
	}
	
	/*
	 * There is a way to use constant space, yes. While counting the objects and
	 * then looking for which occurs only once does work, you can do something
	 * similar with a set. In that solution, you add the object if it is not yet
	 * in the set, and remove it if it is. Then, the set is left with only the
	 * element that occurred once at the end.
	 * 
	 * Improving further on that process, since it is add/remove, a function
	 * that counters its effects if run a second time will allow you to find
	 * which element doesn't happen twice. The easiest one to use is exclusive
	 * or, ^. If you store the values in an int, and xor in each element as it
	 * occurs, at the end, only the singleton remains in the int.
	 * 
	 * Therefore, the constant space algorithm is as follows:
	 */	
	public static int singleNum(int[] a){
		int num=0;
		for(int x : a) num ^= x;
		return num;
	}
}
