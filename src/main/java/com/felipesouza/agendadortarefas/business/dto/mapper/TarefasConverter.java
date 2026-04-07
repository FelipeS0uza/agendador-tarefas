//Aqui foi usado o MapStruct, que é uma biblicoteca de mapeamento que faz a conversão dos dados em tempo de compilação
//Sem a necessidade do Builder

package com.felipesouza.agendadortarefas.business.dto.mapper;

import com.felipesouza.agendadortarefas.business.dto.TarefasDTO;
import com.felipesouza.agendadortarefas.infrastrutucture.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

//Indica que a interface fará o mapeamento e assim o spring fará a injeção de dependências necessárias
@Mapper(componentModel = "spring")
public interface TarefasConverter {

    //Usado para caso o titulo do dado esteja diferente entre dto e entity, exemplo id e idDto
    @Mapping(source = "id", target = "id")

    //Dessa forma é feito o mapeamento do objeto recebido como parametro e já convertido para a classe esperada
    TarefasEntity paraTarefasEntity(TarefasDTO dto);

    TarefasDTO paraTarefaDTO(TarefasEntity entity);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> dtos);

    List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> entities);
}