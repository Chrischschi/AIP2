package extern.hapsaaPayment;




import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Send {

	private final static String QUEUE_NAME = "hapsaaPayment";

	public static void main(String[] argv) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		Boolean running = true;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "";
		
		while (running) {
			message = br.readLine();
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println(" [x] Sent '" + message + "'");
		}

		channel.close();
		connection.close();
	}
}
