package br.com.italooliveira.app_multi_labs.entity.enuns;

import lombok.Getter;
import java.util.Arrays;

@Getter
public enum PrioridadeTarefa {
    BAIXA,
    MEDIA,
    ALTA,
    CRITICA;

    public static PrioridadeTarefa fromString(String prioridade) {
        if (prioridade == null || prioridade.isBlank()) {
            throw new IllegalArgumentException("Prioridade não pode ser vazia");
        }

        return Arrays.stream(PrioridadeTarefa.values())
                .filter(v -> v.name().equalsIgnoreCase(prioridade.trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Prioridade inválida: " + prioridade));
    }
}

