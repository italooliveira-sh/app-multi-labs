package br.com.italooliveira.app_multi_labs.dtos;

public record TarefaUpdateRequestDto(String titulo,
                                     String descricao,
                                     String status,
                                     String prioridade ) {

}
