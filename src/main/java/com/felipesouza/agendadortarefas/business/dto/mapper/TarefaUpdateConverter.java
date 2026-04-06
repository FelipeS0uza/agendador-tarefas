//Aqui foi usado o MapStruct, que é uma biblicoteca de mapeamento que faz a conversão dos dados em tempo de compilação
//Sem a necessidade do Builder

package com.felipesouza.agendadortarefas.business.dto.mapper;

import com.felipesouza.agendadortarefas.business.dto.TarefasDTO;
import com.felipesouza.agendadortarefas.infrastrutucture.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

//Indica que a interface fará o mapeamento e assim o spring fará a injeção de dependências necessárias
//O segundo argumento diz para ignorar quando os valores forem nulos e pegar os dados do outro objeto
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    //Faz o mapeamento dos objetos recebidos como parametro e atualiza na entity os dados recebidos no DTO
    //Caso seja valor nulo no DTO, é usado o dado que já possui na entity, ou seja, no BD.
    void updateTarefas(TarefasDTO dto, @MappingTarget TarefasEntity entity);
}