//Camada DTO - Data Transfer Object
//É um modelo de projeto usado para transportar dados, evitando passar dados sensíveis para outro serviço.

//Foi criado o UsuarioDTO para receber os dados do usuário autenticado para realizar as requisições nas tarefas

package com.felipesouza.agendadortarefas.business.dto;

import lombok.*;

@Getter //Cria todos os getters
@Setter //Cria todos os setters
@AllArgsConstructor //  Cria o construtor recebendo todos os argumentos
@NoArgsConstructor  //Cria o construtor não recebendo nenhum argumento
@Builder    //Simplifica a instância de classes fornecendo os parâmetros na ordem desejada e de uma só vez.
public class UsuarioDTO {

    private String email;
    private String senha;
}