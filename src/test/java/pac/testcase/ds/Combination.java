package pac.testcase.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Combination {
	public static void main(String[] args) {
		char[]a = "1234".toCharArray();
		
		
//		printCombo(a,1);
		list.size();
		for (String c : list) {
//			System.out.println(c);
//			printPermut(c.toCharArray(), 0);
		}
		
		combo(a,0,new ArrayList<String>());
	}
	
	static void printPermut(char[]a,int index){
		if(a.length==index){
			System.out.println(a);
		}else{
			for (int i = index; i < a.length; i++) {
				swap(a,index,i);
				printPermut(a, index+1);
				swap(a,index,i);
			}
		}
	}
	
	static List<String> list = new ArrayList<>();
	static void printCombo(char[]a,int index){
		if(index<a.length){
			String base = "";
			for (int i = 0; i < index; i++) {
				base+=a[i];
			}
			for (int i = index; i < a.length; i++) {
				list.add(base+a[i]);
			}
			
		}
	}
	
	static List<String> combo(char[]a,int index,List<String> list){
		if(a.length!=index+1){
			List<String> ele = new ArrayList<>();
			if(index==0){
				for (int i = 0; i < a.length; i++) {
					ele.add(a[i]+"");
					System.out.println(list.get(i)+""+a[i]);
				}
			}
			list = combo(a, index+1, list);
			for (int i = index; i < list.size(); i++) {
				for (int j = i; j < a.length; j++) {
					if(!list.get(i).equals(a[j]+""))
						ele.add(list.get(i)+""+a[j]);
						System.out.println(list.get(i)+""+a[j]);
				}
			}
			return ele;
		}
		return new ArrayList<>();
	}
	
	static void swap(char[]a,int m,int n){
		char tmp = a[m];
		a[m] = a[n];
		a[n]  = tmp;
	}
}
