package pac.testcase.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;

public class Sender {

	public static void main(String[] args) throws JMSException, InterruptedException {
		new Receiver().start();
		// meaningless
		ConnectionFactory connectionFactory = new PooledConnectionFactory(
				new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
						ActiveMQConnection.DEFAULT_PASSWORD,
						"tcp://localhost:61616"));

		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(Boolean.TRUE,
				Session.AUTO_ACKNOWLEDGE);

		/*
		 TopicSession ts = (TopicSession )session;
		ts.createPublisher(session.createTopic("this is sparta!!")).publish(ts.createTextMessage("topic sparta!!")); 
		 */
		
		Destination destination = session.createTopic("this is sparta!!");

		MessageProducer producer = session.createProducer(destination);

		// 食之无味 delivery mode 默认是persistent
		// producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		TextMessage message0 = session.createTextMessage("这是斯巴达!!!");
		TextMessage message1 = session.createTextMessage("这也是斯巴达!!!");
		TextMessage message2 = session.createTextMessage("这些都是斯巴达!!!");

		producer.send(message0);
		producer.send(message1);
		producer.send(message2);

		session.commit();
		System.out.println("send complete!!");
		
	}
}

class Receiver extends Thread{
	public void run() {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
		
		Connection connection;
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			
			Session session = connection.createSession(Boolean.FALSE,
					Session.AUTO_ACKNOWLEDGE);
			
			Destination destination = session.createTopic("this is sparta!!");
			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(new MessageListener() {
				public void onMessage(Message message) {
					try {
						System.out.println("listener catched:::"+((TextMessage)message).getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
			
			//javax.jms.IllegalStateException
			//Cannot synchronously receive a message when a MessageListener is set
			//consumer.receive(10000);
			//not a transacted session
//			session.commit();
			
//			System.out.println(((TextMessage) consumer.receive(10000)).getText());
//			System.out.println(((TextMessage) consumer.receive(10000)).getText());
//			System.out.println(((TextMessage) consumer.receive(10000)).getText());
		} catch (JMSException e1) {
			e1.printStackTrace();
		}

	}
}
