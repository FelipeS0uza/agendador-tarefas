package com.felipesouza.agendadortarefas.business.dto.mapper;

import com.felipesouza.agendadortarefas.business.dto.TarefasDTO;
import com.felipesouza.agendadortarefas.infrastrutucture.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    @Mapping(source = "id", target = "id")
    TarefasEntity paraTarefasEntity(TarefasDTO dto);

    TarefasDTO paraTarefaDTO(TarefasEntity entity);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> dtos);

    List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> entities);
}
