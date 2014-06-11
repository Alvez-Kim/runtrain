package pac.testcase.jms;

import java.util.HashMap;

public class CustomedListener {
	void processHandle(HashMap<String,String> map){
		System.out.println("handle executed!!");
		System.out.println("msg:::"+map.get("msg"));
	}
}