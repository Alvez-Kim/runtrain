package pac.testcase.ds;

public class Permutation {
	public static void main(String[] args) {
		char[] in = "123".toCharArray();
		permute(in, 0);
	}

	static int times = 0;

	private static void permute(char[] array, int index) {
		++times;
		int len = array.length;
		if (len == index) {
			System.out.println(new String(array) + " " + times);
		} else {

			for (int i = index; i < len; i++) {
				if (swapCheck(array, index, i)) {
					swap(array, index, i);
					permute(array, index + 1);
					swap(array, index, i);
				}
			}
		}
	}

	static void swap(char[] a, int x, int y) {
		char temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}

	static boolean swapCheck(char[] a, int x, int y) {
		for (int i = x; i < y; i++) {
			if (a[i] == a[y])
				return false;
		}
		return true;
	}
}
