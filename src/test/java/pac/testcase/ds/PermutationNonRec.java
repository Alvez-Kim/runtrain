package pac.testcase.ds;

import java.util.Arrays;

public class PermutationNonRec {

	/*
	 * int[] a = { 2, 4, 7, 9 }; int[] b = new int[4]; for (int i = 0; i <
	 * a.length; i++) { b[0] = a[i]; for (int j = 0; j < a.length; j++) { b[1] =
	 * a[j]; for (int k = 0; k < a.length; k++) { b[2] = a[k]; for (int m = 0; m
	 * < a.length; m++) { b[3] = a[m];
	 * 
	 * if (i != j && i != k && i != m && j != k && j != m & k != m) { for (int p
	 * = 0; p < b.length; p++) { System.out.print(b[p]); } System.out.println();
	 * }
	 * 
	 * } } }
	 * 
	 * }
	 */

	static void printPermute() {
		char[] a = { '3', '2', '1', '4' };
		// char[] b = Arrays.copyOf(a, a.length);
		// Arrays.sort(b);
		// String max = new StringBuilder(new String(a)).reverse().toString();
		// String val = null;
		// while(max!=val){
		// for (int i = a.length-1; i > 0; i--) {
		// if(a[i-1]<a[i]){
		// int index = findBigger(a,i);
		// if(index!=0){
		// swap(a,index,i-1);
		// reverse(a,index);
		// val = String.valueOf(a);
		// System.out.println(val);
		// }else continue;
		// }
		// }
		// }
		Arrays.sort(a);
		for (int i = a.length - 1, j = 0; i > 0; i--, j++) {
			swap(a, i, i - 1);
			if (i == 1) {
				swap(a, a.length - 1, a.length - 2);
				i = a.length - 1;
			}
			System.out.println(a);
		}
	}

	static void swap(char[] a, int m, int n) {
		char tmp = a[m];
		a[m] = a[n];
		a[n] = tmp;
	}

	static int findBigger(char[] a, int m) {
		int index = m + 1;
		for (int i = m; i < a.length && index < a.length; i++) {
			if (a[index] > a[i])
				index = i;
		}

		return index < a.length && a[index] > a[m] ? index : 0;
	}

	static void reverse(char[] a, int m) {
		char[] b = Arrays.copyOfRange(a, m, a.length);
		b = new StringBuilder(new String(b)).reverse().toString().toCharArray();
		for (int i = 0; i < b.length; i++, m++) {
			a[m] = b[i];
		}
	}

	public static int t;// 组合个数

	public static void main(String[] args) {
		String str = "123";
		char[] c = str.toCharArray();
		println(c);
		t++;
		allCombString(c, 0);
		System.out.println(t);
	}

	public static void allCombString(char[] c, int s) {
		int l = c.length;
		if (l - s == 2) {
			char temp = c[l - 1];
			c[l - 1] = c[l - 2];
			c[l - 2] = temp;
			println(c);
			t++;
		} else {
			for (int i = s; i < l; i++) {
				moveToHead(c, i, s);
				char ct[] = new char[l];
				System.arraycopy(c, 0, ct, 0, l);// 保持其他元素位置不变
				allCombString(ct, s + 1);
			}
		}
	}

	public static void moveToHead(char[] c, int id, int s) {
		if (id > s && id < c.length) {
			char temp = c[id];
			for (int i = id; i > s; i--) {
				c[i] = c[i - 1];
			}
			c[s] = temp;
			println(c);
			t++;
		}
	}

	public static void println(char[] c) {
		System.out.println(new String(c));
	}

}