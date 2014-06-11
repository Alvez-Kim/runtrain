package pac.king.webservice;

import java.util.Map;

import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.phase.Phase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pac.king.pojo.User;
import pac.king.webservice.impl.MyCxfServiceImpl;
import pac.king.webservice.interceptor.MyInterceptor;

public class TestCase {
	public static void main(String[] args) {
		
		
		JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
		factory.setServiceClass(MyCxfServiceImpl.class);
		factory.setAddress("http://localhost:8080/runtrain/services/MyCxfService");
		factory.create();
		
//		factory.create().getEndpoint().getInInterceptors().add(new MyInterceptor(Phase.USER_LOGICAL));
		
		/*
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
		MyCxfService client = (MyCxfService) context.getBean("MyCxfClient");
		Map<String,String> map = client.convertUserInfoToMap(new User("100001","King.","t;stmdtkg"));
		System.out.println(map.get("id"));
		System.out.println(map.get("name"));
		System.out.println(map.get("password"));
//		System.out.println(client.fetchUserByName("King"));;
 */
		
	}
}
