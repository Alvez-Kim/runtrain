package pac.king.endpoint;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import pac.king.service.MyService;
import pac.king.webservice.TestClientService;

@Service
@WebService(serviceName="testMyService")
@SOAPBinding(style=Style.RPC)
public class MyServiceEndpoint extends SpringBeanAutowiringSupport{
	@Autowired
	MyService myService;
	
	@WebMethod
	public void sayHiFarAway(String name){
		List<String> resultyList = new ArrayList<String>();
		System.out.println(myService.sayHiTo(name));;
//		return (String[])resultyList.toArray();
	}
	
	public static void main(String[] args) throws MalformedURLException {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
		Endpoint.publish("http://localhost:8080/myservices", (MyServiceEndpoint)context.getBean(MyServiceEndpoint.class));
		
		URL url = new URL("http://localhost:8080/myservices?wsdl");//wsdl 地址
		QName qname = new QName("http://endpoint.king.pac/","testMyService");  //其中第一个参数为：namespaceURI 第二个为servername
		javax.xml.ws.Service service = javax.xml.ws.Service.create(url, new QName("http://endpoint.king.pac/","testMyService"));
		QName q = new QName("http://endpoint.king.pac/","MyServiceEndpointPort");
		MyClientService client = service.getPort(q,MyClientService.class);
//		TestClientService client = service.getPort(q,TestClientService.class);
		client.sayHiFarAway("King");
		Iterator<QName> itr = service.getPorts();
		while(itr.hasNext()){
			QName qName= itr.next();
			System.out.println(qName.getNamespaceURI());
			System.out.println(qName.getLocalPart());
			System.out.println(qName.getPrefix());
			System.out.println(qName.toString());
		}
		
//		MyClientService client = (MyClientService)context.getBean("clientSide");
//		System.out.println(client.sayHiFarAway("King"));
	}
}
