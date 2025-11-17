package br.com.italooliveira.app_multi_labs.entity.enuns;

import java.util.Arrays;

public enum StatusTarefa {
  PENDENTE,
  EM_ANDAMENTO,
  CONCLUIDA,
  CANCELADA;

  public static StatusTarefa fromString(String status) {
    if (status.equals("") || status.isBlank()) {
      throw new IllegalArgumentException("Status não pode ser vazio");
    }

    return Arrays.stream(StatusTarefa.values())
              .filter(s -> s.name().equalsIgnoreCase(status.trim()))
              .findFirst()
              .orElseThrow(() -> new IllegalArgumentException("Status inválido: " + status));
  }
}
