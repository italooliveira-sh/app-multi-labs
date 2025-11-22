package br.com.italooliveira.app_multi_labs.controller.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.italooliveira.app_multi_labs.exception.PrioridadeTarefaInvalidaException;
import br.com.italooliveira.app_multi_labs.exception.StatusTarefaInvalidaException;
import br.com.italooliveira.app_multi_labs.exception.TarefaNotFoundException;
import br.com.italooliveira.app_multi_labs.mapper.TarefaMapper;
import br.com.italooliveira.app_multi_labs.service.TarefaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.italooliveira.app_multi_labs.factory.TarefaFactory;
import br.com.italooliveira.app_multi_labs.repository.TarefaRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TarefaControllerImplTest {
  
  public static final String BASE_URI = "/api/tarefa";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private TarefaRepository tarefaRepository;

  @Autowired
  private TarefaService tarefaService;

  @Autowired
  private TarefaMapper tarefaMapper;

  @Autowired
  private ObjectMapper objectMapper;

  @AfterEach
  void cleanup() {
      tarefaRepository.deleteAll();
  }

  @Test
  public void novaTarefaDeveRetornarStatusOk() throws Exception {
    final var request = TarefaFactory.novaTarefaRequest();
    final var json = objectMapper.writeValueAsString(request);

    mockMvc.perform(
      post(BASE_URI)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(json)
    )
    .andExpect(status().isCreated())
    .andExpect(jsonPath("$.id").isNotEmpty())
    .andExpect(jsonPath("$.titulo").value(request.titulo()))
    .andExpect(jsonPath("$.descricao").value(request.descricao()))
    .andExpect(jsonPath("$.status").value("EM_ANDAMENTO"))
    .andExpect(jsonPath("$.prioridade").value("BAIXA"))
    .andExpect(jsonPath("$.criadoEm").isNotEmpty());
  }

  @Test
  public void novaTarefaDeveRetornarStatusBadRequestQuandoStatusTarefaForInvalido() throws Exception {
    var request = TarefaFactory.novaTarefaComStatusInvalido();
    var json = objectMapper.writeValueAsString(request);
    mockMvc.perform(
            post(BASE_URI)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(json)
    )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.path").value(BASE_URI))
            .andExpect(result -> assertInstanceOf(StatusTarefaInvalidaException.class, result.getResolvedException()));
  }

  @Test
  public void novaTarefaDeveRetornarStatusBadRequestQuandoStatusTarefaForVazio() throws Exception {
    var request = TarefaFactory.novaTarefaComStatusVazio();
    var json = objectMapper.writeValueAsString(request);
    mockMvc.perform(
                    post(BASE_URI)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(json)
            )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.path").value(BASE_URI))
            .andExpect(jsonPath("$.mensagem").value("Status não pode ser vazio"))
            .andExpect(result -> assertInstanceOf(StatusTarefaInvalidaException.class, result.getResolvedException()));
  }

  @Test
  public void novaTarefaDeveRetornarStatusBadRequestQuandoPrioridadeTarefaForInvalido() throws Exception {
    var request = TarefaFactory.novaTarefaComPrioridadeInvalido();
    var json = objectMapper.writeValueAsString(request);
    mockMvc.perform(
            post(BASE_URI)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(json)
    )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.path").value(BASE_URI))
            .andExpect(result -> assertInstanceOf(PrioridadeTarefaInvalidaException.class, result.getResolvedException()));
  }

  @Test
  public void novaTarefaDeveRetornarStatusBadRequestQuandoPrioridadeTarefaForVazio() throws Exception {
    var request = TarefaFactory.novaTarefaComPrioridadeVazio();
    var json = objectMapper.writeValueAsString(request);
    mockMvc.perform(
                    post(BASE_URI)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(json)
            )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.path").value(BASE_URI))
            .andExpect(result -> assertInstanceOf(PrioridadeTarefaInvalidaException.class, result.getResolvedException()));
  }

  @Test
  public void obterTarefaPeloIdDeveRetornarStatusOkQuandoIdTarefaValido() throws Exception {
    var request = TarefaFactory.novaTarefaRequest();
    var tarefaSalva = tarefaService.save(request);
    var id = tarefaSalva.id();

    mockMvc.perform(
            get(BASE_URI + "/{idTarefa}", id)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.titulo").value(request.titulo()))
            .andExpect(jsonPath("$.descricao").value(request.descricao()))
            .andExpect(jsonPath("$.status").value("EM_ANDAMENTO"))
            .andExpect(jsonPath("$.prioridade").value("BAIXA"))
            .andExpect(jsonPath("$.criadoEm").isNotEmpty());
  }

  @Test
  public void obterTarefaPeloIdDeveRetornarStatusNotFoundQuandoIdTarefaInvalido() throws Exception {
    var id = 1000L;
    mockMvc.perform(
            get(BASE_URI + "/{idTarefa}", id)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
    )
            .andExpect(status().isNotFound())
            .andExpect(result -> assertInstanceOf(TarefaNotFoundException.class, result.getResolvedException()));
  }

  @Test
  public void obterTodasTarefaDeveRetornarStatusOk() throws Exception {
    var request = TarefaFactory.listaTarefasRequest();
    var listaTarefasEntity = request.stream().map(tarefaMapper::toEntity).toList();
    tarefaRepository.saveAll(listaTarefasEntity);

    mockMvc.perform(
                    get(BASE_URI)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(3))
            .andExpect(jsonPath("$[0].id").isNotEmpty())
            .andExpect(jsonPath("$[0].titulo").value(request.get(0).titulo()))
            .andExpect(jsonPath("$[1].titulo").value(request.get(1).titulo()))
            .andExpect(jsonPath("$[2].titulo").value(request.get(2).titulo()))
            .andExpect(jsonPath("$[0].status").value(request.get(0).status().toUpperCase()))
            .andExpect(jsonPath("$[1].status").value(request.get(1).status().toUpperCase()))
            .andExpect(jsonPath("$[2].status").value(request.get(2).status().toUpperCase()));
  }

  @Test
  public void atualizarTarefaDeveRetornarStatusOkQuandoEnviarSomenteTitulo() throws Exception {
    var salvarTarefa = TarefaFactory.novaTarefaRequest();
    var save = tarefaService.save(salvarTarefa);

    var updateTitulo = TarefaFactory.updateTitulo("Novo titulo atualizado");
    var json = objectMapper.writeValueAsString(updateTitulo);

    mockMvc.perform(
            put(BASE_URI + "/{idTarefa}", save.id())
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(json)
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.titulo").value(updateTitulo.titulo()));

  }

  @Test
  public void atualizarTarefaDeveRetornarStatusOkQuandoEnviarSomenteDescricao() throws Exception {
    var salvarTarefa = TarefaFactory.novaTarefaRequest();
    var save = tarefaService.save(salvarTarefa);

    var updateDescricao = TarefaFactory.updateDescricao("Novo descrição atualizado");
    var json = objectMapper.writeValueAsString(updateDescricao);

    mockMvc.perform(
                    put(BASE_URI + "/{idTarefa}", save.id())
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(json)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.descricao").value(updateDescricao.descricao()));

  }

  @Test
  public void atualizarTarefaDeveRetornarStatusOkQuandoEnviarSomenteStatus() throws Exception {
    var salvarTarefa = TarefaFactory.novaTarefaRequest();
    var save = tarefaService.save(salvarTarefa);

    var updateStatus = TarefaFactory.updateStatus("pendente");
    var json = objectMapper.writeValueAsString(updateStatus);

    mockMvc.perform(
                    put(BASE_URI + "/{idTarefa}", save.id())
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(json)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value(updateStatus.status().toUpperCase()));

  }

  @Test
  public void atualizarTarefaDeveRetornarStatusOkQuandoEnviarSomentePrioridade() throws Exception {
    var salvarTarefa = TarefaFactory.novaTarefaRequest();
    var save = tarefaService.save(salvarTarefa);

    var updatePrioridade = TarefaFactory.updatePrioridade("critica");
    var json = objectMapper.writeValueAsString(updatePrioridade);

    mockMvc.perform(
                    put(BASE_URI + "/{idTarefa}", save.id())
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(json)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.prioridade").value(updatePrioridade.prioridade().toUpperCase()));

  }

  @Test
  public void atualizarTarefaDeveRetornarStatusNotFoundQuandoIdForInvalido() throws Exception {
    var updatePrioridade = TarefaFactory.updatePrioridade("critica");
    var json = objectMapper.writeValueAsString(updatePrioridade);

    mockMvc.perform(
                    put(BASE_URI + "/{idTarefa}", 1)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(json)
            )
            .andExpect(status().isNotFound())
            .andExpect(result -> assertInstanceOf(TarefaNotFoundException.class, result.getResolvedException()));

  }

  @Test
  public void deletarTarefaDeveRetornarStatusNoContentQuandoOIdForValido() throws Exception {
    var request = TarefaFactory.novaTarefaRequest();
    var tarefaSalva = tarefaService.save(request);

    mockMvc.perform(
            delete(BASE_URI + "/{idTarefa}", tarefaSalva.id())
    )
            .andExpect(status().isNoContent());

    assertFalse(tarefaRepository.findById(tarefaSalva.id()).isPresent());
  }

  @Test
  public void deletarTarefaDeveRetornarStatusNotFoundQuandoOIdForInvalido() throws Exception {

    mockMvc.perform(
            delete(BASE_URI + "/{idTarefa}", 1)
    )
            .andExpect(status().isNotFound());
  }

}
