package pac.king.webservice.client;

import java.util.Arrays;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import org.apache.commons.httpclient.HttpClient;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pac.king.pojo.User;
import pac.king.webservice.MyRestService;
import pac.king.webservice.impl.MyRestServiceImpl;


public class MyRsClient {
	public static void main(String[] args) {
//		Client client  = ClientBuilder.newBuilder().newClient();
//		WebTarget target = client.target("http://localhost:8080/services");
//		target = target.path("rest");
//		
//		Invocation.Builder builder = target.request();
//		builder.async().get();
//		builder.get();
		
//		WebClient.client(client).accept(MediaType.APPLICATION_XML);
//		WebClient.create("").path(path, values);
		MyRestServiceImpl client = JAXRSClientFactory.create("http://localhost:8080/runtrain/services/rest", MyRestServiceImpl.class);
		
		User[] users = client.userInfos(10);
		for (User user : users) {
			System.out.println(user.getName());
		}
		JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();
		
		bean.setAddress("http://localhost:8080/runtrain/services/rest");
		bean.setResourceClass(MyRestServiceImpl.class);
		
		MyRestServiceImpl proxy = (MyRestServiceImpl)bean.create();
		 users = proxy.userInfos(10);
		for (User user : users) {
			System.out.println(user.getName());
		}
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
//		MyRestServiceImpl clientBean = (MyRestServiceImpl )context.getBean("restClient");
//		MyRestServiceImpl test = (MyRestServiceImpl)bean.create();
//		User[] users = test.userInfos();
		WebClient webClient = WebClient.create("http://localhost:8080/runtrain/services/rest");
		webClient .path("limitUsers").path(new Integer(10));
		webClient .type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML);
//		User[] res = webClient.get(User[].class);
//		System.out.println(res.length);
		Response response = webClient.post(null);
		User[] use = response.readEntity(User[].class);
		System.out.println(use.length);
		
		MyRestService c = JAXRSClientFactory.fromClient(WebClient.client(client), MyRestService.class);
		System.out.println(c.userInfos(10));
		
		
	}
}