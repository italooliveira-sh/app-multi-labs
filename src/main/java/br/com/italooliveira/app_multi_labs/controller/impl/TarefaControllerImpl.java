package br.com.italooliveira.app_multi_labs.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.italooliveira.app_multi_labs.controller.TarefaController;
import br.com.italooliveira.app_multi_labs.dtos.TarefaRequestDto;
import br.com.italooliveira.app_multi_labs.dtos.TarefaResponseDto;
import br.com.italooliveira.app_multi_labs.service.TarefaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TarefaControllerImpl implements TarefaController{

  final private TarefaService tarefaService;

  @Override
  public ResponseEntity<TarefaResponseDto> novaTarefa(TarefaRequestDto requestDto) {
    TarefaResponseDto tarefaSalva = tarefaService.save(requestDto);
    return ResponseEntity.ok(tarefaSalva);
  }
  
}
