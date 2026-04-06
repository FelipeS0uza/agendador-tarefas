package com.felipesouza.agendadortarefas.infrastrutucture.client;

import com.felipesouza.agendadortarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/* O OpenFeign é um cliente HTTP para a comunição entre microserviços.
   Ele reduz o código repetitivo e facilita a implementação não precisando escrever a implementação da chamada REST  */

//Anotação para indicar que aqui será feito a comunicação com outro microserviço
//O nome é para nomear a API e a url é aquela que será consumida (Definida como variável no Application.properties)
@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    //O login é feito no MS Usuario, o get retorna os dados do usuario e o token no header.

    @GetMapping("/usuario")   //Indica que o metodo é um GET
    //RequestParam indica que estou passando um parametro no corpo da requisição, nesse caso o email
    //Busca os dados somente do email informado
    UsuarioDTO buscaUsuarioPorEmail(@RequestParam("email") String email,
                                    @RequestHeader("Authorization") String token);
}