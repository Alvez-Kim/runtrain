package pac.king.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class MyServiceEndpoint {
	
	@WebMethod
	public String talkToMe(String sth){
		return "My heart is like stallion "+sth;
	}
}
