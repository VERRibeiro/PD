import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NewTask {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        int prefetchCount = 1;
        try (
                Connection connection = connectionFactory.newConnection();
                Channel canal = connection.createChannel();        		        		
        		
        ) {
            
            String NOME_FILA = "plica";
            
            String mensagem = "tarefa de victor ";

            
            System.out.println ("[x] Enviado '" + mensagem + "'");
            //(queue, passive, durable, exclusive, autoDelete, arguments)
            canal.queueDeclare(NOME_FILA, false, false, false, null);

            // ​(exchange, routingKey, mandatory, immediate, props, byte[] body)
            for(int i = 0; i< 10; i++) {
            	mensagem = mensagem + ".";
            	canal.basicPublish ("", NOME_FILA,  MessageProperties.PERSISTENT_TEXT_PLAIN, mensagem.getBytes ());
                System.out.println("Enviei mensagem: " + mensagem + i);
            }
            

        }
    }
}


