//Camada SERVICE é onde se desenvolve as regras de negócio e toda a lógica e recebe as requisições do controller
//Intermedia a comunicação entre o Controller e o Repository

package com.felipesouza.agendadortarefas.business;

import com.felipesouza.agendadortarefas.business.dto.TarefasDTO;
import com.felipesouza.agendadortarefas.business.dto.mapper.TarefaUpdateConverter;
import com.felipesouza.agendadortarefas.business.dto.mapper.TarefasConverter;
import com.felipesouza.agendadortarefas.infrastrutucture.entity.TarefasEntity;
import com.felipesouza.agendadortarefas.infrastrutucture.enums.StatusNotificacaoEnum;
import com.felipesouza.agendadortarefas.infrastrutucture.exceptions.ResourceNotFoundException;
import com.felipesouza.agendadortarefas.infrastrutucture.repository.TarefasRepository;
import com.felipesouza.agendadortarefas.infrastrutucture.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service    //Indica ao spring que é uma Service
@RequiredArgsConstructor    //Gera um construtor que inicializa apenas os campos PRIVATE FINAL
public class TarefasService {

    //Injeção de dependências
    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    //Metodo para gravar a tarefa
    public TarefasDTO gravarTarefa(String token, TarefasDTO dto) {
        //Exclui a palavra Bearer , extrai o token JWT e a partir dele extrai o email
        String email = jwtUtil.extractUsername(token.substring(7));

        //O email setado é o do usuario autenticado
        dto.setEmailUsuario(email);
        //Não é preciso informar a data de criação pois o código já pega a data atual no momento da criação
        dto.setDataCriacao(LocalDateTime.now());
        //Toda tarefa criada já recebe o status de PENDENTE, assim não é preciso informar
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);

        //Recebe os dados informados como dto, faz a conversão para entidade e salva como uma entity
        TarefasEntity entity = tarefaConverter.paraTarefasEntity(dto);

        //Salva a entidade nova tarefa no banco de dados e o retorno é convertido novamente em dto
        return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
    }

    //Metodo para buscar as tarefas por periodo
    public List<TarefasDTO> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        //Busca as tarefas dentro do periodo recebido no parametro e o retorno é convertido para DTO
        return tarefaConverter.paraListaTarefasDTO(tarefasRepository.findByDataAgendamentoBetween(dataInicial, dataFinal));
    }

    //Metodo que busca as tarefas pelo email do usuario autenticado
    public List<TarefasDTO> buscaTarefasPorEmail(String token) {
        //Exclui a palavra Bearer , extrai o token JWT e a partir dele extrai o email
        String email = jwtUtil.extractUsername(token.substring(7));

        //Busca as tarefas pelo email recebido e o retorno é convertido em DTO
        return tarefaConverter.paraListaTarefasDTO(tarefasRepository.findByEmailUsuario(email));
    }

    //Metodo que deleta a tarefa pelo id
    public void deletaTarefaPorId(String id) {
        try{
            //Exclui a tarefa rebenco o id como parametro
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            //Caso já tenha sido deletada, retorna um erro
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id - Id não encontrado: " + id, e.getCause());
        }
    }

    //Metodo que altera o status da tarefa pelo id
    public TarefasDTO alteracaoStatus(StatusNotificacaoEnum status, String id) {
        try {
            //Procura a tarefa no BD e caso não exista lança uma erro
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Tarefa não encontrada " + id));

            //Seta o novo status passado como parametro
            entity.setStatusNotificacaoEnum(status);

            //Salva o novo status no BD e o retorno é convertido para DTO
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            //Se der algum erro durante o processo é lançado um erro com a causa dele
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }
    }

    //Metodo que atualiza os dados da tarefa
    public TarefasDTO updateTarefas(TarefasDTO dto, String id) {
        try{
            //Procura a tarefa no BD e caso não exista lança uma erro
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Tarefa não encontrada " + id));

            //O objeto entity será atualizado com os novos valores passado no DTO, por isso não pegamos o retorno
            tarefaUpdateConverter.updateTarefas(dto, entity);

            //Salva os novos dados no BD e o retorno é convertido para DTO
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));

        } catch (ResourceNotFoundException e) {
            //Se der algum erro durante o processo é lançado um erro com a causa dele
            throw new ResourceNotFoundException("Erro ao alterar tarefa " + e.getCause());
        }
    }
}