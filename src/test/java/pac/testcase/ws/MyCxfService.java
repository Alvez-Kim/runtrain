package pac.testcase.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface MyCxfService {
	@WebMethod
	public @WebResult String saySth(@WebParam String content);
}
