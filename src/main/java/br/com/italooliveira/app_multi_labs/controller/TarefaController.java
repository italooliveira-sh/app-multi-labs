package br.com.italooliveira.app_multi_labs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.italooliveira.app_multi_labs.dtos.TarefaRequestDto;
import br.com.italooliveira.app_multi_labs.dtos.TarefaResponseDto;

@RequestMapping("/api/tarefa")
public interface TarefaController {
  
  @PostMapping
  ResponseEntity<TarefaResponseDto> novaTarefa(@RequestBody TarefaRequestDto requestDto);

  @GetMapping("/{idTarefa}")
  ResponseEntity<TarefaResponseDto> obterTarefaPeloId(@PathVariable Long idTarefa);
}
