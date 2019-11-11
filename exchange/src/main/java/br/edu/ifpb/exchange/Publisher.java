package br.edu.ifpb.exchange;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * Hello world!
 *
 */
public class Publisher 
{
    public static void main( String[] args ) throws Exception
    {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        String EXCHANGE_NAME = "logs";
        try (
                Connection connection = connectionFactory.newConnection();
                Channel canal = connection.createChannel();          		
        ) {
        	canal.exchangeDeclare(EXCHANGE_NAME, "fanout");          
            String mensagem = "Testando ";

            
            System.out.println ("[x] Enviado '" + mensagem + "'");

            // â€‹(exchange, routingKey, mandatory, immediate, props, byte[] body)
            canal.basicPublish(EXCHANGE_NAME, "", null, mensagem.getBytes("UTF-8"));
        }
    }
}
