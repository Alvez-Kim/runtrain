package pac.testcase.ds;

public class Permutation {
	public static void main(String[] args) {
		// printAll(1234);
		System.out.println("------");
		char[] in = "12".toCharArray();
		permute(in, 0);

		// int[] a = { 2, 4, 7, 9 };
		// int[] b = new int[4];
		// for (int i = 0; i < a.length; i++) {
		// b[0] = a[i];
		// for (int j = 0; j < a.length; j++) {
		// b[1] = a[j];
		// for (int k = 0; k < a.length; k++) {
		// b[2] = a[k];
		// for (int m = 0; m < a.length; m++) {
		// b[3] = a[m];
		//
		// if (i != j && i != k && i != m && j != k && j != m
		// & k != m) {
		// for (int p = 0; p < b.length; p++) {
		// System.out.print(b[p]);
		// }
		// System.out.println();
		// }
		//
		// }
		// }
		//
		// }
		//
		// }
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
