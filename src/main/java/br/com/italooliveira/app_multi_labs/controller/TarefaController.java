package br.com.italooliveira.app_multi_labs.controller;

import br.com.italooliveira.app_multi_labs.dtos.TarefaUpdateRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.italooliveira.app_multi_labs.dtos.TarefaRequestDto;
import br.com.italooliveira.app_multi_labs.dtos.TarefaResponseDto;

import java.util.List;

@RequestMapping("/api/tarefa")
public interface TarefaController {
  
  @PostMapping
  ResponseEntity<TarefaResponseDto> novaTarefa(@RequestBody TarefaRequestDto requestDto);

  @GetMapping("/{idTarefa}")
  ResponseEntity<TarefaResponseDto> obterTarefaPeloId(@PathVariable Long idTarefa);

  @GetMapping
  ResponseEntity<List<TarefaResponseDto>> obterTodasTarefa();

  @PutMapping("/{idTarefa}")
  ResponseEntity<TarefaResponseDto> atualizarTarefa(@PathVariable Long idTarefa, @RequestBody TarefaUpdateRequestDto requestDto);
}
