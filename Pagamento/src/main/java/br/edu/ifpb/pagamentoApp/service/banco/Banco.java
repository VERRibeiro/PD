package br.edu.ifpb.pagamentoApp.service.banco;


import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import br.edu.ifpb.pagamentoApp.model.Cartao;

public class Banco {
	public static void main(String[] args) throws Exception {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		connectionFactory.setPort(5672);
		Connection conexao = connectionFactory.newConnection();
		Channel canal = conexao.createChannel();
		BancoSender bs = new BancoSender();
		String NOME_FILA = "BANCO";
		canal.queueDeclare(NOME_FILA, true, false, false, null);
		System.out.println("BANCO");
		Gson g = new Gson();
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String mensagem = new String(delivery.getBody(), "UTF-8");
			Cartao c = g.fromJson(mensagem, Cartao.class);
			System.out.println("[x] Recebido");
			
			try {
				bs.sendMessage(mensagem, c.getBandeira());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {				
				canal.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
			}
		};
		boolean autoAck = false; // ack é feito aqui. Como está autoAck, enviará automaticamente
		canal.basicConsume(NOME_FILA, autoAck, deliverCallback, consumerTag -> {
		});
	}
}
