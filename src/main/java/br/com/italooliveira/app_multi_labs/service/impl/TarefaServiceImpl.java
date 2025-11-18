package br.com.italooliveira.app_multi_labs.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.italooliveira.app_multi_labs.dtos.TarefaRequestDto;
import br.com.italooliveira.app_multi_labs.dtos.TarefaResponseDto;
import br.com.italooliveira.app_multi_labs.entity.Tarefa;
import br.com.italooliveira.app_multi_labs.mapper.TarefaMapper;
import br.com.italooliveira.app_multi_labs.repository.TarefaRepository;
import br.com.italooliveira.app_multi_labs.service.TarefaService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarefaServiceImpl implements TarefaService{

  final private TarefaRepository tarefaRepository;
  final private TarefaMapper tarefaMapper;

  @Override
  @Transactional
  public TarefaResponseDto save(TarefaRequestDto requestDto) {
    Tarefa tarefa = tarefaMapper.toEntity(requestDto);
    return tarefaMapper.fromEntity(tarefaRepository.save(tarefa));
  }
  
}
