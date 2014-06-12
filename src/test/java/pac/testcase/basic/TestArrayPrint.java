package pac.testcase.basic;

public class TestArrayPrint {
	public static void main(String[] args) {
		Integer result = next(123);
		System.out.println(result);
		do{
			result = next(result);
			System.out.println(result);
		}while(result!=null);
		int a= 3;
		int b = 6;
		swap(a,b);
		System.out.println(a);
	}
	static Integer next(int val){
		char[] arr = String.valueOf(val).toCharArray();
		int maxIndex = arr.length-1;
		for (int i = maxIndex; i > 0; i--) {
			if(arr[maxIndex]>arr[i-1]){
				char a = arr[maxIndex];
				arr[maxIndex] = arr[i-1];
				arr[i-1] = a;
				break;
			}
		}
		int result =  Integer.valueOf(new String(arr));
		if(val==result) return null;
		return result;
	}
	
	static void swap(int a,int b){
		if(a!=b){
			a=a^b;
			b=a^b;
			a=a^b;
		}
	}
}