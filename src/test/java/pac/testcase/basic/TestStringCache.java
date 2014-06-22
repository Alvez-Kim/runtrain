package pac.testcase.basic;

import java.util.Collections;

public class TestStringCache {
	public static void main(String[] args) {
		String _ = "_";
		final String str_ = "str"+_;
//		final String str_ = "str_";
		String a = str_ + "a";
		
		String b = a;
		String c = "str_a";
		
		System.out.println("a==c:::"+(a==c));
		
	}
	
}
