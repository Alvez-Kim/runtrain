package pac.king.webservice.impl;



import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pac.king.pojo.User;
import pac.king.webservice.MyCxfService;

public class MyCxfServiceImpl implements MyCxfService {

	public HashMap<String, String> convertUserInfoToMap(User user) {
		HashMap<String,String> result = new HashMap<String, String>();
		result.put("name", user.getName());
		result.put("id", user.getId());
		result.put("password", user.getPassword());
		return result;
	}
//	public String fetchUserByName(String name) {
//		
//		return "I;m "+name;
//	}


}
