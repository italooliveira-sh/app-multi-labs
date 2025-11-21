package br.com.italooliveira.app_multi_labs.factory;

import br.com.italooliveira.app_multi_labs.dtos.TarefaRequestDto;
import br.com.italooliveira.app_multi_labs.dtos.TarefaUpdateRequestDto;

import java.util.List;

public class TarefaFactory {
  
  public static TarefaRequestDto novaTarefaRequest() {
    return new TarefaRequestDto(
        "Novo título", 
        "Nova descrição da tarefa",
        "em_andamento", 
        "baixa"
      );
  }

  public static TarefaRequestDto novaTarefaComPrioridadeInvalido() {
    return new TarefaRequestDto(
            "Novo título",
            "Nova descrição da tarefa",
            "em_andamento",
            "invalido"
    );
  }

  public static TarefaRequestDto novaTarefaComPrioridadeVazio() {
    return new TarefaRequestDto(
            "Novo título",
            "Nova descrição da tarefa",
            "em_andamento",
            ""
    );
  }

  public static TarefaRequestDto novaTarefaComStatusInvalido() {
    return new TarefaRequestDto(
            "Novo título",
            "Nova descrição da tarefa",
            "invalido",
            "baixa"
    );
  }

  public static TarefaRequestDto novaTarefaComStatusVazio() {
    return new TarefaRequestDto(
            "Novo título",
            "Nova descrição da tarefa",
            "",
            "baixa"
    );
  }

  public static List<TarefaRequestDto> listaTarefasRequest() {
    return List.of(
            new TarefaRequestDto(
                    "Tarefa 1",
                    "Descrição da tarefa 1",
                    "pendente",
                    "baixa"
            ),
            new TarefaRequestDto(
                    "Tarefa 2",
                    "Descrição da tarefa 2",
                    "em_andamento",
                    "media"
            ),
            new TarefaRequestDto(
                    "Tarefa 3",
                    "Descrição da tarefa 3",
                    "concluida",
                    "alta"
            )
    );
  }

  public static TarefaUpdateRequestDto updateTarefaRequest(String titulo,
                                                           String descricao,
                                                           String status,
                                                           String prioridade) {
    return new TarefaUpdateRequestDto(
            titulo,
            descricao,
            status,
            prioridade
    );
  }

  public static TarefaUpdateRequestDto updateTitulo(String titulo) {
    return updateTarefaRequest(titulo, null, null, null);
  }


  public static TarefaUpdateRequestDto updateDescricao(String descricao) {
    return updateTarefaRequest(null, descricao, null, null);
  }

  public static TarefaUpdateRequestDto updateStatus(String status) {
    return updateTarefaRequest(null, null, status, null);
  }

  public static TarefaUpdateRequestDto updatePrioridade(String prioridade) {
    return updateTarefaRequest(null, null, null, prioridade);
  }
}
