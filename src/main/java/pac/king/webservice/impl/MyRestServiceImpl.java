package pac.king.webservice.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.UUID;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;

//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.methods.GetMethod;
//import org.apache.commons.httpclient.params.HttpClientParams;
//import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
//import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import pac.king.pojo.User;
import pac.king.webservice.MyRestService;


public class MyRestServiceImpl implements MyRestService{

	@WebMethod
	public User[] userInfos(int count) {
		System.out.println("count="+count);
		
		User[] myInfos = new User[count];
		for (int i = 0; i < count; i++) {
			myInfos[i] = new User(i+1+"","King."+UUID.randomUUID(),"t;stmdtkg");
		}
		return myInfos;
	}
	
	public static void main(String[] args) throws  IOException {
		/*
		MyRestServiceImpl test = new MyRestServiceImpl();
//		ApplicationContext context=  new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
		Endpoint.publish("http://localhost:8080/testin", test);
		
//		URL wsdlUrl = new URL("http://localhost:8080/testin?wsdl");
		javax.xml.ws.Service service = javax.xml.ws.Service.create(
				new QName("http://webservice.king.pac/","MyRestService"));
		
		JaxWsProxyFactoryBean client = new JaxWsProxyFactoryBean();
		client.setAddress("http://localhost:8080/testin/myRestService");
		client.setServiceClass(MyRestService.class);
		MyRestService c = (MyRestService)client.create();
		System.out.println(c.userInfo().getName()+"+++++");;
		
		Iterator<QName> itr = service.getPorts();
		while(itr.hasNext()){
			QName qName= itr.next();
			System.out.println(qName.getNamespaceURI());
			System.out.println(qName.getLocalPart());
			System.out.println(qName.getPrefix());
			System.out.println(qName.toString());
//			service.getPort(qName,MyRestServiceImpl.class);
		}
		
		JAXRSServerFactoryBean rsFactory = new JAXRSServerFactoryBean();
		rsFactory.setAddress("http://localhost:8888/myRest");
		rsFactory.setResourceClasses(MyRestServiceImpl.class);
		rsFactory.create();
		*/
//		rsFactory.setServiceBean(MyRestServiceImpl.class);
		
//		System.out.println(client.userInfo().getName());
		
//		JAXRSClientFactoryBean client = new JAXRSClientFactoryBean();
//		client.setAddress("http://localhost:8888/rsservice");
//		client.setResourceClass(MyRestServiceImpl.class);
//		((MyRestServiceImpl)client.create()).userInfo();
//		System.out.println(((MyRestService)client.create()).userInfo().getName());;
//		HttpClient client = new HttpClient();
//		HttpClientParams param = new HttpClientParams();
//		param.setParameter("user", "00");
//		client.setParams(param);
//		System.out.println("response status:::"+client.executeMethod(new GetMethod("http://localhost:8080/runtrain/services/test/users")));
		
		System.out.println(Math.round(Math.random()*10));
		System.out.println(UUID.randomUUID());
	}
}
