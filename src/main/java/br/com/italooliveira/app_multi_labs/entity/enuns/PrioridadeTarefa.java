package br.com.italooliveira.app_multi_labs.entity.enuns;

import lombok.Getter;
import java.util.Arrays;

import br.com.italooliveira.app_multi_labs.exception.PrioridadeTarefaNotFound;

@Getter
public enum PrioridadeTarefa {
    BAIXA,
    MEDIA,
    ALTA,
    CRITICA;

    public static PrioridadeTarefa fromString(String prioridade) {
        if (prioridade == null || prioridade.isBlank()) {
            throw new PrioridadeTarefaNotFound("Prioridade não pode ser vazia");
        }

        return Arrays.stream(PrioridadeTarefa.values())
                .filter(v -> v.name().equalsIgnoreCase(prioridade.trim()))
                .findFirst()
                .orElseThrow(() -> new PrioridadeTarefaNotFound(String.format("Prioridade inválida: %s", prioridade)));
    }
}

