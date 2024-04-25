package com.ufes.pi.chat;

import java.util.LinkedList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class OChatDaAulaDeProjetoIntegradoApplication {
	private Mensagens mensagens = new Mensagens();

	public static void main(String[] args) {
		SpringApplication.run(OChatDaAulaDeProjetoIntegradoApplication.class, args);
	}

	@PostMapping("/chat")
	public void hello(@RequestBody Mensagem msg) {
		mensagens.addMensagens(msg.comHorario());
	}

	@GetMapping("/chat")
	public LinkedList<Mensagem> hello() {
		return this.mensagens.getMensagens();
	}
}
