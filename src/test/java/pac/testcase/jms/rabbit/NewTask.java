package pac.testcase.jms.rabbit;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NewTask {

	private static final String TASK_QUEUE_NAME = "task_queue";

	/*private static String getMessage(String[] strings) {
		if (strings.length < 1)
			return "Hello...!";
		return joinStrings(strings, " ");
	}

	private static String joinStrings(String[] strings, String delimiter) {
		int length = strings.length;
		if (length == 0)
			return "";
		StringBuilder words = new StringBuilder(strings[0]);
		for (int i = 1; i < length; i++) {
			words.append(delimiter).append(strings[i]);
		}
		return words.toString();
	}*/

	public static void main(String[] argv) throws java.io.IOException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

		String message = "Hello...";

		for (int i = 0; i < 20; i++) {
			channel.basicPublish("", TASK_QUEUE_NAME,
					MessageProperties.PERSISTENT_TEXT_PLAIN, message.concat(i+1+"").getBytes());
			System.out.println(" [x] Sent '" + message + (i + 1) + "'  "
					+ (i + 1) + " times");
		}

		channel.close();
		connection.close();
	}
}
