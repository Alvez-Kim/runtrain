package pac.testcase.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import pac.king.pojo.User;

public class TestHashCode {
	public static void main(String[] args) {
		User u = new User("0001", "Jin", "t;stmdtkg");
		Map<User, String> map = new HashMap<User, String>();
		map.put(u, "123456789");
		;
		for (Map.Entry<User, String> e : map.entrySet()) {
			System.out.println(e.getKey().hashCode());
		}
		u.setName("King");
		System.out.println(u.hashCode());
		System.out.println(map.get(u));

		Map<User, String> testMap = new HashMap<User, String>();

		User p1 = new User();
		p1.setName("Jakie");
		p1.setId("1");

		User p2 = new User();
		p2.setName("Jerry");
		p2.setId("2");
		User p3 = new User();
		p3.setName("Torres");
		p3.setId("3");
		System.out.println(p1 + ";hashcode:" + p1.hashCode() + "\n");
		System.out.println(p2 + ";hashcode:" + p2.hashCode() + "\n");
		System.out.println(p3 + ";hashcode:" + p3.hashCode() + "\n");

		System.out.println("************************");
		System.out.println("putting object into map");
		testMap.put(p1, "p1");
		testMap.put(p2, "p2");
		testMap.put(p3, "p3");

		System.out.println("************************");
		p2.setName("Jerry is now kelly");

		System.out.println("P2 hashcode after update:");
		System.out.println(p2 + ";hashcode:" + p2.hashCode() + "\n");

		System.out.println("**************************");
		System.out.println("Hash Code of elements in HashMap");
		for (Entry<User, String> entry : testMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue() + ":"
					+ entry.getKey().hashCode());
			System.out.println();
			if (entry.getKey().getName().equals("Jakie")) {
				System.out.println("Jakie in map is the original jakie "
						+ (entry.getKey() == p1));
			} else if (entry.getKey().getName().equals("Jerry is now kelly")) {
				System.out
						.println("Jerry is now kelly in map is the original Jerry "
								+ (entry.getKey() == p2));
			}
		}

		System.out.println("**********************");
		String p = testMap.get(p2);
		System.out.println("Final Result:" + p);
	}
}
