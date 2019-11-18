package br.edu.ifpb.pagamentoApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.ifpb.pagamentoApp.service.client.ClientSender;

@Controller
public class BancoController {
	
	@RequestMapping("/sendMessage")	
	public String sendMessagem() {
		ClientSender c = new ClientSender();
		try {
			c.sendMessage("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Enviado";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form() {				
		return "form";
	}
}
