package pac.testcase.basic;

public class TestOverride {
	public static void main(String[] args) {
		Human human = new Human();
		Animal animal = new Animal();
		Animal con = (Animal)human;
		System.out.println("animal::"+animal.a);
		System.out.println("animal::"+animal.b);
		System.out.println("human::"+human.a);
		System.out.println("human::"+human.b);
		System.out.println("--------------");
		System.out.println(con.a);
		System.out.println(con.b);
		
		human.printC();
		human.setSuperC(10);
		human.printC();
	}
}

class Animal{
	static int a = 1;
	int b = 2;
	
	int c = 3;
	/*protected void go() {
		System.out.println("animal go");
	}*/
	
	//sub-class can not access
	private void go() {
		System.out.println("animal go");
	}
	
	public static void main(String[] args) {
		Animal a = new Animal();
		a.go();
		Animal b = new Human();
		b.go();
		((Animal)b).go();
	}
}

class Human extends Animal{
	static int a = 3;
	int b = 4;

	public void  go() {
		System.out.println("Human go");
	}
	
	void setSuperC(int c){
		super.c = c;
	}
	
	public void printC(){
		System.out.println("Human::"+this.c);
		System.out.println("Animal::"+super.c);
	}
}