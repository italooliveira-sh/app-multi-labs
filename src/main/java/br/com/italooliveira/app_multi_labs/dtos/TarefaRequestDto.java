package br.com.italooliveira.app_multi_labs.dtos;

public record TarefaRequestDto(
    String titulo, 
    String descricao, 
    String status, 
    String prioridade
  ) {
  
}
