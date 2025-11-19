package br.com.italooliveira.app_multi_labs.entity.enuns;

import lombok.Getter;
import java.util.Arrays;

import br.com.italooliveira.app_multi_labs.exception.PrioridadeTarefaInvalida;

@Getter
public enum PrioridadeTarefa {
    BAIXA,
    MEDIA,
    ALTA,
    CRITICA;

    public static PrioridadeTarefa fromString(String prioridade) {
        if (prioridade == null || prioridade.isBlank()) {
            throw new PrioridadeTarefaInvalida("Prioridade não pode ser vazia");
        }

        return Arrays.stream(PrioridadeTarefa.values())
                .filter(v -> v.name().equalsIgnoreCase(prioridade.trim()))
                .findFirst()
                .orElseThrow(() -> new PrioridadeTarefaInvalida(String.format("Prioridade inválida: %s", prioridade)));
    }
}

