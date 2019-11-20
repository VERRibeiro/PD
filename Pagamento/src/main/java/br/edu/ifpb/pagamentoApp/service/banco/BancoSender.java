package br.edu.ifpb.pagamentoApp.service.banco;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class BancoSender {

	public void sendMessage(String message, String bandeira) throws IOException, TimeoutException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection(); Channel canal = connection.createChannel()) {

			canal.queueDeclare(bandeira, true, false, false, null);			
			canal.basicPublish("", bandeira, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));			
			System.out.println("Enviando para o banco " + bandeira);
		}
	}
}