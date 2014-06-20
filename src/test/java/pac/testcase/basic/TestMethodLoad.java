package pac.testcase.basic;

public class TestMethodLoad {
	public static void go(float f){
		System.out.println("float");
	}
	
	public static void go(Integer i){
		System.out.println("Integer");
	}
	
	public static void go(int...i){
		System.out.println("int...");
	}
	public static void main(String[] args) {
		go(0);
	}
}