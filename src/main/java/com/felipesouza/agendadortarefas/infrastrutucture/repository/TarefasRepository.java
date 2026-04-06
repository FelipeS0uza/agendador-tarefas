//Camda REPOSITORY é onde temos persistência e acesso ao bando de dados (CRUD)
// Converte objetos Java (Entities) em comandos SQL e vice-versa

package com.felipesouza.agendadortarefas.infrastrutucture.repository;

import com.felipesouza.agendadortarefas.infrastrutucture.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository     //Indica ao spring que é uma repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

    //Metodo que procura as tarefas pela data de agendamento entre um periodo específico, recebendo data inicial e final
    List<TarefasEntity> findByDataAgendamentoBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);

    //Metodo que procura as tarefas pelo email
    List<TarefasEntity> findByEmailUsuario(String email);
}