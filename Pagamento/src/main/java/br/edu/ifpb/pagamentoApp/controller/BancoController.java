package br.edu.ifpb.pagamentoApp.controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.ifpb.pagamentoApp.service.client.ClientSender;

@Controller
public class BancoController {
	
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)	
	public String sendMessagem(HttpServletRequest request, @RequestBody String postPayload) throws UnsupportedEncodingException {

		String result = java.net.URLDecoder.decode(postPayload, StandardCharsets.UTF_8.name());
		if (result != null && result.length() > 0 && result.charAt(result.length() - 1) == '=') {
			result = result.substring(0, result.length() - 1);
	    }
		ClientSender c = new ClientSender();
		try {
			System.out.println(result);
			c.sendMessage(result);
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
