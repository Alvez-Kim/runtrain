package pac.testcase.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

public class MyMessageConverter implements MessageConverter{

	public Message toMessage(Object object, Session session)
			throws JMSException, MessageConversionException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object fromMessage(Message message) throws JMSException,
			MessageConversionException {
		// TODO Auto-generated method stub
		return null;
	}
	
}