package com.felipesouza.agendadortarefas.infrastrutucture.client;

import com.felipesouza.agendadortarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")   //Indica que o metodo é um GET
    //RequestParam indica que estou passando um parametro no corpo da requisição, nesse caso o email
    //Busca os dados somente do email informado
    UsuarioDTO buscaUsuarioPorEmail(@RequestParam("email") String email,
                                    @RequestHeader("Authorization") String token);
}
