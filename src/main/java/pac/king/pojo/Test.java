package pac.king.pojo;

public class Test {
	User u;
	public static void main(String[] args) {
		Test t = new Test();
		t.u = new Student();
		t.u.setName("King");
		System.out.println(t.u.getName());
		
	}

}
