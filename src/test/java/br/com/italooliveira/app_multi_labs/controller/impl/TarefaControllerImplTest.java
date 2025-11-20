package br.com.italooliveira.app_multi_labs.controller.impl;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.italooliveira.app_multi_labs.exception.PrioridadeTarefaInvalidaException;
import br.com.italooliveira.app_multi_labs.exception.StatusTarefaInvalidaException;
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
}
