package br.com.italooliveira.app_multi_labs.service;

import br.com.italooliveira.app_multi_labs.dtos.TarefaRequestDto;
import br.com.italooliveira.app_multi_labs.dtos.TarefaResponseDto;

import java.util.List;

public interface TarefaService {

  TarefaResponseDto save(TarefaRequestDto requestDto);

  TarefaResponseDto getById(Long idTarefa);

  List<TarefaResponseDto> getAll();
  
}
