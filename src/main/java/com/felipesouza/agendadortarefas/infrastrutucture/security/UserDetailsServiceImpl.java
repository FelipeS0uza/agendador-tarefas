/*Foi excluido o metodo loadUserByUsername pois como se trata de uma interface, não é possivel alterar o código
  Então foi criado o metodo carregaDadosUsuario, pois é preciso passar como parâmetro o email e o token*/

package com.felipesouza.agendadortarefas.infrastrutucture.security;

import com.felipesouza.agendadortarefas.business.dto.UsuarioDTO;
import com.felipesouza.agendadortarefas.infrastrutucture.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired //Injeção de dependencias
    private UsuarioClient client;

    public UserDetails carregaDadosUsuario(String email, String token) {
        UsuarioDTO usuarioDTO = client.buscaUsuarioPorEmail(email, token);

        return User
                .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }
}