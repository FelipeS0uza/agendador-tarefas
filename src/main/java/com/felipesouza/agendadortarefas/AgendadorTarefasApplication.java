package com.felipesouza.agendadortarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication	//Indica que é uma aplicação do spring e faz as configurações necessárias
@EnableFeignClients		//Habilita o uso do FeignClient para usar os metodos de requisições HTTP de outros microserviços
public class AgendadorTarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendadorTarefasApplication.class, args);
	}

}