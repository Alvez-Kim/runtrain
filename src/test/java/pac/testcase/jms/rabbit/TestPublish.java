package pac.testcase.jms.rabbit;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.ExchangeTypes;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class TestPublish {
	
}

final class ChannelFactory {
	
	final static String EXCHANGE_NAME = "log";
	final static String EXCHANGE_NAME_ = "log2";
	
	private static final ConnectionFactory factory = new ConnectionFactory();
	
	static{
		try {
			Channel temp = getChannel();
			temp.exchangeDeclare(EXCHANGE_NAME, ExchangeTypes.FANOUT);
			temp.exchangeDeclare(EXCHANGE_NAME_, ExchangeTypes.FANOUT);
			closeChannel(temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ChannelFactory() {
	}

	public static Channel getChannel() {
		try {
			return factory.newConnection().createChannel();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Channel getChannel(int channelNumber) {
		try {
			return factory.newConnection().createChannel();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void closeChannel(Channel channel) {
		try {
			channel.close();
			channel.getConnection().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

class Publisher {
	public static void main(String[] args) throws IOException {

		Channel channel = ChannelFactory.getChannel();

		String message = "Here is the content";
		channel.basicPublish(ChannelFactory.EXCHANGE_NAME, StringUtils.EMPTY, null,
				("EXCHANGE_NAME 1:::"+message).getBytes());
		channel.basicPublish(ChannelFactory.EXCHANGE_NAME_, StringUtils.EMPTY, null,
				("EXCHANGE_NAME 2:::"+message).getBytes());
		
		ChannelFactory.closeChannel(channel);
	}
}
class Publisher_ {
	public static void main(String[] args) throws IOException {
		
		Channel channel = ChannelFactory.getChannel();
		
		String message = "Here is the content2";
		channel.basicPublish(ChannelFactory.EXCHANGE_NAME_, StringUtils.EMPTY, null,
				message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");
		ChannelFactory.closeChannel(channel);
	}
}



class Logger {
	public static void main(String[] args) throws IOException,
			ShutdownSignalException, ConsumerCancelledException,
			InterruptedException {
		Channel channel = ChannelFactory.getChannel();

		String queue = channel.queueDeclare().getQueue();
		System.out.println("temporary queue name::"+queue);

		channel.queueBind(queue, ChannelFactory.EXCHANGE_NAME, "");
		channel.queueBind(queue, ChannelFactory.EXCHANGE_NAME_, "");

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queue, true, consumer);

		while (true) {
			System.out.println(new String(consumer.nextDelivery().getBody()));
		}
	}
}