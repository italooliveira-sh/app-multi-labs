package br.com.italooliveira.app_multi_labs.controller.impl;

import java.net.URI;
import java.util.List;

import br.com.italooliveira.app_multi_labs.dtos.TarefaUpdateRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.italooliveira.app_multi_labs.controller.TarefaController;
import br.com.italooliveira.app_multi_labs.dtos.TarefaRequestDto;
import br.com.italooliveira.app_multi_labs.dtos.TarefaResponseDto;
import br.com.italooliveira.app_multi_labs.service.TarefaService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TarefaControllerImpl implements TarefaController {

  private final TarefaService tarefaService;

  @Override
  public ResponseEntity<TarefaResponseDto> novaTarefa(TarefaRequestDto requestDto) {
    TarefaResponseDto tarefaSalva = tarefaService.save(requestDto);
    URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(tarefaSalva.id())
                .toUri();

    return ResponseEntity.created(uri).body(tarefaSalva);
  }

  @Override
  public ResponseEntity<TarefaResponseDto> obterTarefaPeloId(Long idTarefa) {

    return ResponseEntity.ok(tarefaService.getById(idTarefa));
  }

  @Override
  public ResponseEntity<List<TarefaResponseDto>> obterTodasTarefa() {
    return ResponseEntity.ok(tarefaService.getAll());
  }

  @Override
  public ResponseEntity<TarefaResponseDto> atualizarTarefa(Long idTarefa, TarefaUpdateRequestDto requestDto) {
    TarefaResponseDto tarefaAtualizada = tarefaService.update(idTarefa, requestDto);
    return ResponseEntity.ok(tarefaAtualizada);
  }

}
