package pac.testcase.ws.client;

import java.util.HashMap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import pac.king.pojo.User;
import pac.king.webservice.utils.MyMapAdapter;

@WebService
public interface MyCxfService {

	@WebMethod
	@XmlJavaTypeAdapter(MyMapAdapter.class)
	public @WebResult HashMap<String,String> convertUserInfoToMap(@WebParam User user);
}
