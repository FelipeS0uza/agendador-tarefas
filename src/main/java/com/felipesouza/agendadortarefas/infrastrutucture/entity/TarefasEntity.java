//Camada ENTITY é onde representa a estrutura de dados da aplicação
// Define as tabelas do banco de dados mapeadas para classes Java

package com.felipesouza.agendadortarefas.infrastrutucture.entity;

import com.felipesouza.agendadortarefas.infrastrutucture.enums.StatusNotificacaoEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter //Cria todos os getters
@Setter //Cria todos os setters
@AllArgsConstructor //  Cria o construtor recebendo todos os argumentos
@NoArgsConstructor  //Cria o construtor não recebendo nenhum argumento
@Builder    //Simplifica a instância de classes fornecendo os parâmetros na ordem desejada e de uma só vez.
@Document("tarefa")     //Nome da tabela NoSQL
public class TarefasEntity {

    @Id     //Indica que o atributo é o id
    private String id;
    private String nomeTarefa;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAgendamento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;
}