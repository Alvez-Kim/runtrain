package pac.testcase.basic;

public class TestMethodLoad {

	public static void go(char c){
		System.out.println("char");
	}
	public static void go(byte b){
		System.out.println("byte");
	}
	
	public static void go(short s){
		System.out.println("short");
	}
	
	public static void go(Short s){
		System.out.println("S");
	}
	public static void go(Integer i){
		System.out.println("Integer");
	}
	public static void go(float f){
		System.out.println("float");
	}
	public static void go(int...i){
		System.out.println("int...");
	}
	
	public static void main(String[] args) {
		/*1.primitive type
		2.compatible primitive type
		3.wapper class
		4...*/
		go(98);
	}
}
