package pac.king.webservice;

import javax.jws.WebService;

@WebService
public interface TestClientService {
	public String sayHiFarAway(String name);	
}
