package br.com.italooliveira.app_multi_labs.dtos;

public record TarefaResponseDto(Long id,
                                String titulo,
                                String descricao,
                                String status,
                                String prioridade,
                                String criadoEm) {
  
}
