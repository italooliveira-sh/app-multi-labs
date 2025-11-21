package br.com.italooliveira.app_multi_labs.mapper;

import br.com.italooliveira.app_multi_labs.dtos.TarefaUpdateRequestDto;
import org.springframework.stereotype.Component;

import br.com.italooliveira.app_multi_labs.dtos.TarefaRequestDto;
import br.com.italooliveira.app_multi_labs.dtos.TarefaResponseDto;
import br.com.italooliveira.app_multi_labs.entity.Tarefa;
import br.com.italooliveira.app_multi_labs.entity.enuns.PrioridadeTarefa;
import br.com.italooliveira.app_multi_labs.entity.enuns.StatusTarefa;

@Component
public class TarefaMapper {

  public Tarefa toEntity(TarefaRequestDto requestDto) {
    Tarefa tarefa = new Tarefa();
    tarefa.setTitulo(requestDto.titulo());
    tarefa.setDescricao(requestDto.descricao());
    tarefa.setStatus(StatusTarefa.fromString(requestDto.status()));
    tarefa.setPrioridade(PrioridadeTarefa.fromString(requestDto.prioridade()));
    return tarefa;
  }

  public TarefaResponseDto fromEntity(Tarefa tarefa) {
    return new TarefaResponseDto(
            tarefa.getId(),
            tarefa.getTitulo(),
            tarefa.getDescricao(),
            tarefa.getStatus().toString(),
            tarefa.getPrioridade().toString(),
            tarefa.getCriadoEm().toString()
    );
  }

  public Tarefa updateEnity(Tarefa tarefa, TarefaUpdateRequestDto requestDto) {

    if (requestDto.titulo() != null) {
      tarefa.setTitulo(requestDto.titulo());
    }

    if (requestDto.descricao() != null) {
      tarefa.setDescricao(requestDto.descricao());
    }

    if (requestDto.status() != null) {
      tarefa.setStatus(StatusTarefa.fromString(requestDto.status()));
    }

    if (requestDto.prioridade() != null) {
      tarefa.setPrioridade(PrioridadeTarefa.fromString(requestDto.prioridade()));
    }

    return tarefa;
  }
}
