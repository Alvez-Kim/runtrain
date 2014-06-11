package pac.testcase.jms;

import org.springframework.stereotype.Service;

@Service
public class JmsRmiServiceImpl implements JmsRmiService {
	
	public void hehe() {
		// TODO Auto-generated method stub
		
	}

	public String doServe(String content) {
		System.out.println(content.concat(" has been requested!!"));
		return "your message::".concat(content).concat(":::length:")+content.length();
	}
}
