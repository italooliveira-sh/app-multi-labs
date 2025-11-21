package br.com.italooliveira.app_multi_labs.service.impl;

import br.com.italooliveira.app_multi_labs.dtos.TarefaUpdateRequestDto;
import br.com.italooliveira.app_multi_labs.exception.TarefaNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.italooliveira.app_multi_labs.dtos.TarefaRequestDto;
import br.com.italooliveira.app_multi_labs.dtos.TarefaResponseDto;
import br.com.italooliveira.app_multi_labs.entity.Tarefa;
import br.com.italooliveira.app_multi_labs.mapper.TarefaMapper;
import br.com.italooliveira.app_multi_labs.repository.TarefaRepository;
import br.com.italooliveira.app_multi_labs.service.TarefaService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaServiceImpl implements TarefaService {

  private final TarefaRepository tarefaRepository;
  private final TarefaMapper tarefaMapper;

  @Override
  @Transactional
  public TarefaResponseDto save(TarefaRequestDto requestDto) {
    Tarefa tarefa = tarefaMapper.toEntity(requestDto);
    return tarefaMapper.fromEntity(tarefaRepository.save(tarefa));
  }

  @Override
  @Transactional(readOnly = true)
  public TarefaResponseDto getById(Long idTarefa) {
    return tarefaMapper.fromEntity(findTarefaById(idTarefa));
  }

  @Override
  public List<TarefaResponseDto> getAll() {
    return tarefaRepository.findAll().stream().map(tarefaMapper::fromEntity).toList();
  }

  @Override
  public TarefaResponseDto update(Long idTarefa, TarefaUpdateRequestDto requestDto) {
    Tarefa tarefa = findTarefaById(idTarefa);
    Tarefa tarefaAtualizada = tarefaRepository.save(tarefaMapper.updateEnity(tarefa, requestDto));
    return tarefaMapper.fromEntity(tarefaAtualizada);
  }

  private Tarefa findTarefaById(Long idTarefa) {
    return tarefaRepository.findById(idTarefa).orElseThrow(
            () -> new TarefaNotFoundException(String.format("Tarefa com o id: %d não foi encontrado", idTarefa))
    );
  }

}
