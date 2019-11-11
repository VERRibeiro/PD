import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;


public class Worker {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection conexao = connectionFactory.newConnection();
        Channel canal = conexao.createChannel();
        canal.basicQos(1);
        String NOME_FILA = "plica"
                + "";
        canal.queueDeclare(NOME_FILA, false, false, false, null);
        System.out.println("WORKER 3");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        	   String mensagem = new String (delivery.getBody (), "UTF-8");

        	   System.out.println ("[x] Recebido '" + mensagem + "'");
        	   try {
        	     doWork (mensagem);
        	   } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
        	     System.out.println ("[x] Feito");
        	     canal.basicAck(delivery.getEnvelope(). getDeliveryTag(), false);
        	   }
        	 };
        	 boolean autoAck = false;  // ack é feito aqui. Como está autoAck, enviará automaticamente
        	 canal.basicConsume (NOME_FILA, autoAck, deliverCallback, consumerTag -> {});
    }
    private static void doWork (String task) throws InterruptedException {
        for (char ch: task.toCharArray ()) {
            if (ch == '.') Thread.sleep (1000);
        }
    }
}


