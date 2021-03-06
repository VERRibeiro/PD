package br.edu.ifpb.pagamentoApp.service.opcaoPagamento;


import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import br.edu.ifpb.pagamentoApp.model.Cartao;

public class OpcaoPagamentoBradesco {
	public static void main(String[] args) throws Exception {
		String EXCHANGE_NAME = "processarCartao";
		Gson g = new Gson();
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection conexao = connectionFactory.newConnection();        
        Channel canal = conexao.createChannel();             
        String NOME_FILA = "BRADESCO";
//        canal.exchangeDeclare(EXCHANGE_NAME, "direct");
//        String NOME_FILA = canal.queueDeclare().getQueue();                
//        canal.queueBind(NOME_FILA, EXCHANGE_NAME, "BRADESCO");
        canal.queueDeclare(NOME_FILA, true, false, false, null);
        System.out.println("BRADESCO");
        
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        	String mensagem = new String (delivery.getBody(), "UTF-8");
        	Cartao c = g.fromJson(mensagem, Cartao.class);
        	System.out.println ("[x] Recebido pela bradesco'" + c + "'");
        	   
			canal.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        	   
        	 };
        	 boolean autoAck = false;  // ack é feito aqui. Como está autoAck, enviará automaticamente        	 
        	 canal.basicConsume (NOME_FILA, autoAck, deliverCallback, consumerTag -> {});
    }
}
