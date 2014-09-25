package pac.testcase.jms.rabbit;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import org.springframework.amqp.core.ExchangeTypes;


import java.io.IOException;
import java.util.Date;

import static pac.testcase.jms.rabbit.ChannelFactory_.EXCHANGE_NAME;

/**
 * Created by Alvez on 14-7-13.
 */
public class TestTopic {

    public static void main(String[] args) {

    }
}

final class ChannelFactory_{
    private final static ConnectionFactory connFactory = new ConnectionFactory();

    public  final static String EXCHANGE_NAME = "topic_exchange";
    public final static String[] SEVERITY = {"sql.info","sql.warning","sql.error"};

    static {
        Channel temp = getChannel();
        try {
            temp.exchangeDeclare(EXCHANGE_NAME, ExchangeTypes.TOPIC);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Channel getChannel(int channelNumber){
        try {
            Connection connection = connFactory.newConnection();
            return connection.createChannel(channelNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }return null;
    }

    public static Channel getChannel(){
        try {
            Connection connection = connFactory.newConnection();
            return connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }return null;
    }

    public static void  closeChannel(Channel channel) throws IOException {
        channel.close();
        channel.getConnection().close();
    }

}

class DirectPublisher{
    public static void main(String[] args) throws IOException {
        Channel channel = ChannelFactory_.getChannel();
        String content = "message #$#$#$#$#$#$";

        channel.basicPublish(EXCHANGE_NAME,"warning.sql.connection.close",null,content.getBytes());
        channel.basicPublish(EXCHANGE_NAME,"error.sql.syntax",null,content.getBytes());

        ChannelFactory_.closeChannel(channel);
    }
}

class DirectConsumer{
    public static void main(String[] args) throws IOException, InterruptedException {
        Channel channel = ChannelFactory_.getChannel();

        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, ChannelFactory_.EXCHANGE_NAME,"warning.sql.#");
        channel.queueBind(queueName, ChannelFactory_.EXCHANGE_NAME,"error.#");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName,true,consumer);
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            String routingKey = delivery.getEnvelope().getRoutingKey();

            System.out.println(" [x] Received '" + routingKey + "':'" + message + "'");
        }

    }
}
