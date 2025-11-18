package br.com.italooliveira.app_multi_labs.entity.enuns;

import java.util.Arrays;

import br.com.italooliveira.app_multi_labs.exception.StatusTarefaNotFound;

public enum StatusTarefa {
  PENDENTE,
  EM_ANDAMENTO,
  CONCLUIDA,
  CANCELADA;

  public static StatusTarefa fromString(String status) {
    if (status.equals("") || status.isBlank()) {
      throw new StatusTarefaNotFound("Status não pode ser vazio");
    }

    return Arrays.stream(StatusTarefa.values())
              .filter(s -> s.name().equalsIgnoreCase(status.trim()))
              .findFirst()
              .orElseThrow(() -> new StatusTarefaNotFound(String.format("Status inválido: %s", status)));
  }
}
