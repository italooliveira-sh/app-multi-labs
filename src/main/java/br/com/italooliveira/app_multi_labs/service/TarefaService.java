package br.com.italooliveira.app_multi_labs.service;

import br.com.italooliveira.app_multi_labs.dtos.TarefaRequestDto;
import br.com.italooliveira.app_multi_labs.dtos.TarefaResponseDto;
import br.com.italooliveira.app_multi_labs.dtos.TarefaUpdateRequestDto;

import java.util.List;

public interface TarefaService {

  TarefaResponseDto save(TarefaRequestDto requestDto);

  TarefaResponseDto getById(Long idTarefa);

  List<TarefaResponseDto> getAll();

  TarefaResponseDto update(Long idTarefa, TarefaUpdateRequestDto requestDto);

  void delete(Long idTarefa);
  
}
