package pac.king.webservice.interceptor;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.SoapActionInInterceptor;
import org.apache.cxf.interceptor.Fault;

public class MyInterceptor extends SoapActionInInterceptor {

	@Override
	public void handleMessage(SoapMessage m) throws Fault {
		System.out.println(m.get(m.PROTOCOL_HEADERS));
		System.out.println(m.get(m.REQUEST_URL));		
	}


}
