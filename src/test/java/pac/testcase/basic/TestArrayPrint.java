package pac.testcase.basic;

import java.util.ArrayList;
import java.util.List;

public class TestArrayPrint {

	static List<Integer> exist = new ArrayList<>();

	static Integer next(int val) {
		char[] arr = String.valueOf(val).toCharArray();
		int maxIndex = arr.length - 1;
		boolean checked = false;
		for (int i = maxIndex; i > 0; i--) {
			if (arr[maxIndex] > arr[i - 1]) {
				for (int j = maxIndex; j > i - 1; j--) {
					char t = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = t;
				}
				checked = true;
				break;
			}
		}
		if (!checked) {
			for (int i = 0; i < maxIndex; i++) {
				char t = arr[i];
				arr[i] = arr[i + 1];
				arr[i + 1] = t;
			}
		}
		int result = Integer.valueOf(new String(arr));
		return result;
	}

	static void printAll(int val) {
		System.out.println("begin with::" + val);
		int result = next(val);
		do {
			exist.add(result);
			System.out.println(result);
			result = next(result);
		} while (!exist.contains(result));

	}

	public static void main(String[] args) {
//		printAll(1234);
		System.out.println("------");
		char[] in = "1234".toCharArray();
		paixu(in, in.length, 0);
	}

	private static void paixu(char[] array, int len, int index) {
//		System.out.println("len:::"+len+"index:::"+index);
		if (len == index) {
//			char[] out = new char[len];
//			for (int i = 0; i < array.length; i++) {
//				out[i] = array[i];
//			}
			System.out.println(new String(array));
		} else {
			for (int i = index; i < len; i++) {
				swap(array, index, i);
				paixu(array, len, index + 1);
				swap(array, index, i);
			}
		}
	}

	static void swap(char[] a, int x, int y) {
		char temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}

}