package pac.testcase.ws.client;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pac.king.pojo.User;

public class TestCase {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
		MyCxfService client = (MyCxfService) context.getBean("MyCxfClient");
		Map<String,String> map = client.convertUserInfoToMap(new User("100001","King.","t;stmdtkg"));
		System.out.println(map.get("id"));
		System.out.println(map.get("name"));
		System.out.println(map.get("password"));
//		System.out.println(client.fetchUserByName("King"));;
	}
}
