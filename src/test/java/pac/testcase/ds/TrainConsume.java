package pac.testcase.ds;

public class TrainConsume {
	/*
	 * 火车载量为1000t 1公里消耗1t煤 两地距离为3000
	 */
	public static void main(String[] args) {

		int sum = 3000;
		int load_num = 1000;
		int result = 0;
		int time = sum / load_num - 1;
		for (int i = time; i > 0; --i) {
			result += load_num / ((i * 2) + 1);
		}
		System.out.println(result);
	}
}
