package com.felipesouza.agendadortarefas.business.dto.mapper;

import com.felipesouza.agendadortarefas.business.dto.TarefasDTO;
import com.felipesouza.agendadortarefas.infrastrutucture.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefasEntity(TarefasDTO dto);

    TarefasDTO paraTarefaDTO(TarefasEntity entity);
}
