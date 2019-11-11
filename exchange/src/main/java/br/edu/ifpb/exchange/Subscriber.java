package br.edu.ifpb.exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Subscriber {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);  
        String EXCHANGE_NAME = "logs";
        Connection connection = connectionFactory.newConnection();
        Channel canal = connection.createChannel();
    	
        String NOME_FILA = canal.queueDeclare().getQueue();        
        canal.queueBind(NOME_FILA, EXCHANGE_NAME, "");
        canal.exchangeDeclare(EXCHANGE_NAME, "fanout");
        
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        canal.basicConsume(NOME_FILA, true, deliverCallback, consumerTag -> { });
        
    }
}


