//Camada CONTROLLER recebe as requisições HTTP, mapeia os endpoints, chama a Service para usar os metodos
// e retorna respostas HTTP conforme o necessário.
// Não deve conter lógica de negócio (cálculos, validações complexas, regras de banco)

package com.felipesouza.agendadortarefas.controller;

import com.felipesouza.agendadortarefas.business.TarefasService;
import com.felipesouza.agendadortarefas.business.dto.TarefasDTO;
import com.felipesouza.agendadortarefas.infrastrutucture.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController  //Indica para o spring que essa classe é o controlador e que vai lidar com as requisições (Padrão REST)
@RequestMapping("/tarefas")     //Responsável por apontar qual é a URI da controller
@RequiredArgsConstructor        //Gera um construtor que inicializa apenas os campos PRIVATE FINAL
public class TarefasController {

    //Injeção de dependências
    private final TarefasService tarefasService;

    //ResponseEntity<> é uma classe que indica que o metodo vai retornar uma resposta HTTP do tipo que estiver dentro de <>
    //RequestBody indica que estou passando um objeto no corpo da requisição
    //RequestHeader indica que receberá no Header, na chave Authorization o valor do token
    //RequestParam indica que estou passando um parametro para extrair no corpo da url

    @PostMapping    //Indica que o metodo é um POST
    public ResponseEntity<TarefasDTO> gravarTarefas(@RequestBody TarefasDTO dto,
                                                    @RequestHeader("Authorization") String token) {
        //Caso esteja tudo ok, então a tarefa é salva no banco de dados com o email do usuario autenticado
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")     //Inidica que o metodo é um GET na url /eventos
    public ResponseEntity<List<TarefasDTO>> buscarListaDeTarefasPorPeriodo(
            //DateTimeFormat faz a formatação da data informada para o formato ISO, que é o aceito no banco de dados
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {

        //É feito a busca das tarefas no periodo e caso esteja tudo ok retorna a lista com todas elas
        return ResponseEntity.ok(tarefasService.buscarTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping     //Inidica que o metodo é um GET
    public ResponseEntity<List<TarefasDTO>> buscaTarefasPorEmail(@RequestHeader("Authorization") String token) {
        //É feito a busca das tarefas pelo email extraido do token e caso esteja tudo ok retorna a lista com todas elas
        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));
    }

    @DeleteMapping      //Indica que o metodo é um DELETE
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id) {
        //Chama o metodo de deletar a tarefa
        tarefasService.deletaTarefaPorId(id);

        //Caso esteja tudo ok é deletado no banco de dados
        return ResponseEntity.ok().build();
    }

    @PatchMapping   //Indica que o metodo é um PATCH
    public ResponseEntity<TarefasDTO> alteraStatusNotificacao(@RequestParam("status")StatusNotificacaoEnum status,
                                                              @RequestParam("id") String id) {
        //É feito a alteração do status e caso esteja tudo ok, é salvo no bando de dados
        return ResponseEntity.ok(tarefasService.alteracaoStatus(status, id));
    }

    @PutMapping     //Indica que o metodo é um PUT
    public ResponseEntity<TarefasDTO> updateTarefas(@RequestBody TarefasDTO dto, @RequestParam("id") String id) {
        //É feito a alteração dos dados e caso esteja tudo ok, é salvo no bando de dados
        return ResponseEntity.ok(tarefasService.updateTarefas(dto, id));
    }
}