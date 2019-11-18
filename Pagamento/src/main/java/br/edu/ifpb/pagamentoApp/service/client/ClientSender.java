package br.edu.ifpb.pagamentoApp.service.client;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import br.edu.ifpb.pagamentoApp.model.Cartao;

public class ClientSender {

	public void sendMessage(String mensagem) throws IOException, TimeoutException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		connectionFactory.setPort(5672);
		String NOME_FILA = "BANCO";
		String[] bandeiras = {"VISA", "MASTER", "BRADESCO"};
		Gson g = new Gson();
		int i = (int) (Math.random() * 3);
		if(mensagem.isEmpty())
		{			
			Cartao c = new Cartao("005", "10", "9851-9590-5405-4563", bandeiras[i]);
			mensagem = g.toJson(c);
		}
		try (Connection connection = connectionFactory.newConnection(); Channel canal = connection.createChannel();) {
			
			
			canal.queueDeclare(NOME_FILA, true, false, false, null);			
			
			System.out.println("[x] Enviado '" + mensagem + "'");
			// â€‹(exchange, routingKey, mandatory, immediate, props, byte[] body)
			canal.basicPublish("", NOME_FILA, MessageProperties.PERSISTENT_TEXT_PLAIN, mensagem.getBytes());
		

		}
	}

}
