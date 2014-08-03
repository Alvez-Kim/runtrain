package pac.testcase.jms.rabbit;

import com.rabbitmq.client.*;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Alvez on 14-8-2.
 */
public class AboutExchangeTypes {

    private static String TOPIC_EXCHANGE = "TOPIC_EXCHAGNE";
    private static String DIRECT_EXCHANGE = "DIRECT_EXCHAGNE";
    private static String FANOUT_EXCHANGE = "FANOUT_EXCHANGE";
    private static String HEADERS_EXCHANGE = "HEADERS_EXCHANGE";

    static class DirectProducer {
        public static void main(String[] args) throws IOException {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            String content = "I miss the conversation";
            channel.exchangeDeclare(HEADERS_EXCHANGE, ExchangeTypes.HEADERS);
            AMQP.BasicProperties properties = new AMQP.BasicProperties();
            Map<String,Object> map = new HashMap<>();
            map.put("key1","val1");
            map.put("key2","val2");
            map.put("key3","val3");

            properties.setHeaders(map);

            channel.basicPublish(HEADERS_EXCHANGE, "alvez", properties, content.getBytes());

        }
    }

    static class DirectConsumer {
        public static void main(String[] args) throws IOException, InterruptedException {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            String queueName = channel.queueDeclare().getQueue();
            Map<String, Object> headers = new HashMap<>();
            headers.put("key1","val1");
            headers.put("key2","val2");
            headers.put("key3","val3");
            headers.put("key4","val4");
            channel.queueBind(queueName, HEADERS_EXCHANGE, "",headers);

            QueueingConsumer consumer = new QueueingConsumer(channel);
            String s = channel.basicConsume(queueName, true, consumer);
            System.out.println(s);
            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                String routingKey = delivery.getEnvelope().getRoutingKey();

                System.out.println("From:" + routingKey + "':'" + message + "'");
            }

        }

    }

}


