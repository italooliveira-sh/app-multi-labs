package br.com.italooliveira.app_multi_labs.entity.enuns;

import java.util.Arrays;

import br.com.italooliveira.app_multi_labs.exception.StatusTarefaInvalida;

public enum StatusTarefa {
  PENDENTE,
  EM_ANDAMENTO,
  CONCLUIDA,
  CANCELADA;

  public static StatusTarefa fromString(String status) {
    if (status == null || status.isBlank()) {
      throw new StatusTarefaInvalida("Status não pode ser vazio");
    }

    return Arrays.stream(StatusTarefa.values())
              .filter(s -> s.name().equalsIgnoreCase(status.trim()))
              .findFirst()
              .orElseThrow(() -> new StatusTarefaInvalida(String.format("Status inválido: %s", status)));
  }
}
