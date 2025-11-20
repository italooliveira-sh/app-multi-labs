package br.com.italooliveira.app_multi_labs.service;

import br.com.italooliveira.app_multi_labs.dtos.TarefaRequestDto;
import br.com.italooliveira.app_multi_labs.dtos.TarefaResponseDto;

public interface TarefaService {

  TarefaResponseDto save(TarefaRequestDto requestDto);
  
}
