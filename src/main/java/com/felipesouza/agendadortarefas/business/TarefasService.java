package com.felipesouza.agendadortarefas.business;

import com.felipesouza.agendadortarefas.business.dto.TarefasDTO;
import com.felipesouza.agendadortarefas.business.dto.mapper.TarefasConverter;
import com.felipesouza.agendadortarefas.infrastrutucture.entity.TarefasEntity;
import com.felipesouza.agendadortarefas.infrastrutucture.enums.StatusNotificacaoEnum;
import com.felipesouza.agendadortarefas.infrastrutucture.repository.TarefasRepository;
import com.felipesouza.agendadortarefas.infrastrutucture.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefa(String token, TarefasDTO dto) {
        String email = jwtUtil.extractUsername(token.substring(7));

        dto.setEmailUsuario(email);
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        TarefasEntity entity = tarefaConverter.paraTarefasEntity(dto);

        return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
    }
}
