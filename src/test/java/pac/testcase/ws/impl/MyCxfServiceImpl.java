package pac.testcase.ws.impl;

import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import pac.testcase.ws.MyCxfService;

public class MyCxfServiceImpl implements MyCxfService {

	public String saySth(String content) {
		return "I say "+content;
	}

	public static void main(String[] args) {
		JaxWsServerFactoryBean server = new JaxWsServerFactoryBean();
		server.setServiceClass(MyCxfServiceImpl.class);
		server.setAddress("http://localhost:8686/ws/service");
		server.create();
		
		invokeService();
		
		
	}
	
	static void invokeService(){

		JaxWsProxyFactoryBean client = new JaxWsProxyFactoryBean();
		client.setServiceClass(MyCxfService.class);
		client.setAddress("http://localhost:8686/ws/service");
		MyCxfService service = (MyCxfService)client.create();
		System.out.println(service.saySth("yoyoyo"));		
	}
}
