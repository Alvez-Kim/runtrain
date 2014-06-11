package pac.testcase.jms;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsUtils;

public class SenderInSpring {
	public static void main(String[] args) throws JMSException {
//		new ReceiverInSpring().start();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		JmsTemplate template = (JmsTemplate)context.getBean("jmsTemplate");
		
		template.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				ActiveMQMapMessage msg = (ActiveMQMapMessage)session.createMapMessage();
//				TextMessage msg = session.createTextMessage("This is sparta");
				msg.setString("msg", "This is sparta!!");
				return msg;
			}
		});
		
		System.out.println("send executed!!");
		
		//블럭의 원인은 topic에 있지 않음을 알린다..
//		System.out.println(((TextMessage)template.receive((Destination )context.getBean("destination"))).getText());;
		
		//blocking..
//		System.out.println(template.receive().getStringProperty("msg"));
		
//		new ReceiverInSpring().start();
		
	}
}

class ReceiverInSpring extends Thread{
	private JmsTemplate template;
	@Override
	public void run() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		JmsTemplate template = (JmsTemplate)context.getBean("jmsTemplate");
		
		try {
			
			//这种获取方式只有轮询才能获得 无状态的topic 开销太大
			//轮询就失去意义了 不如queue
			System.out.println("received content:::"+((ActiveMQMapMessage)template.receive()).getString("msg"));
			
			
		} catch (JMSException e) {
			e.printStackTrace();
			JmsException je = JmsUtils.convertJmsAccessException(e);
		}
		System.out.println("receive complete!!");
	}
	public JmsTemplate getTemplate() {
		return template;
	}
	public void setTemplate(JmsTemplate template) {
		this.template = template;
	}
	
}