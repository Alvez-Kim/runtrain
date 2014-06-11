package pac.king.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.stereotype.Service;

@WebService(serviceName="testMyService")
public interface MyService {
	
	public String sayHiTo(String name);
}
