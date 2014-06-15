package pac.testcase.ds;

public class PermutationNonRec {

	public static void main(String[] args) {
		System.out.println("------");

		int[] a = { 2, 4, 7, 9 };
		int[] b = new int[4];
		for (int i = 0; i < a.length; i++) {
			b[0] = a[i];
			for (int j = 0; j < a.length; j++) {
				b[1] = a[j];
				for (int k = 0; k < a.length; k++) {
					b[2] = a[k];
					for (int m = 0; m < a.length; m++) {
						b[3] = a[m];

						if (i != j && i != k && i != m && j != k && j != m
								& k != m) {
							for (int p = 0; p < b.length; p++) {
								System.out.print(b[p]);
							}
							System.out.println();
						}

					}
				}
			}

		}
	}


}