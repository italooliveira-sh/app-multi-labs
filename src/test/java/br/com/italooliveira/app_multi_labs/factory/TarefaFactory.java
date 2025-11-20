package br.com.italooliveira.app_multi_labs.factory;

import br.com.italooliveira.app_multi_labs.dtos.TarefaRequestDto;

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
}
