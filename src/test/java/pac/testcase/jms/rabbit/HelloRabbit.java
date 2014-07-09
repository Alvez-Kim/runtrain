package pac.testcase.jms.rabbit;

import java.io.IOException;
import java.util.Date;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class HelloRabbit {
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] args) throws IOException {

		// 创建Connection
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		// 定义目标队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "Hello World!";
		System.out.println(" [x] Sent '" + message + "'");
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

		channel.close();
		connection.close();
	}
}

class RabbitConsumer {
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, true, consumer);

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(" [x] Received '" + message + "'" + new Date());
		}
	}
}
