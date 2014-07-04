package pac.testcase.basic;

import java.math.BigInteger;
import java.util.Arrays;

public class TestCloneable implements Cloneable{
	String name;
	final String[] skill = {"a","b","c"};

	public TestCloneable() {
		super();
	}

	public TestCloneable(String name) {
		this.name = name;
	}
	
	public final void gogo(){}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		TestCloneable result  = (TestCloneable) super.clone();
		//The final field TestCloneable.skill cannot be assigned
		//result.skill = result.skill.clone();
		return super.clone();
	}

}

class SubClass extends TestCloneable{

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}

 class Test{
	public static void main(String[] args) {
		try {
			String[] a = {"a","b","c"};
			TestCloneable t1 = new TestCloneable("King.");
			TestCloneable t2 = (TestCloneable) t1.clone();
			System.out.println(t1==t2);
			t1.skill[0] = "d";
			System.out.println(Arrays.toString(t1.skill));
			System.out.println(Arrays.toString(t2.skill));
			System.out.println(t2.getClass());
			
			SubClass sub = new SubClass();
			TestCloneable sub2 = (TestCloneable) sub.clone();
			System.out.println(sub2.getClass());
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}
