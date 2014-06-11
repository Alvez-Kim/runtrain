package pac.testcase.ws.client;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;

public class MyInterceptor extends AbstractPhaseInterceptor<Message> {

	public MyInterceptor(String phase) {
		super(phase);
		// TODO Auto-generated constructor stub
	}

	public void handleMessage(Message m) throws Fault {
		m.put("operator", "King.");
		System.out.println("King operating..");
	}


}
