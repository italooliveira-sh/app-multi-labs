package br.com.italooliveira.app_multi_labs.exception.handlerExceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.italooliveira.app_multi_labs.exception.PrioridadeTarefaNotFound;
import br.com.italooliveira.app_multi_labs.exception.StatusTarefaNotFound;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalHandlerException {

  @ExceptionHandler(PrioridadeTarefaNotFound.class)
  public ResponseEntity<MensagemPadraoErro> handlerPrioridadeTarefaNotFound(PrioridadeTarefaNotFound ex, 
                                                                            HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;

    var erro = new MensagemPadraoErro();
    erro.setPath(request.getRequestURI());
    erro.setMethod(request.getMethod());
    erro.setStatus(status.value());
    erro.setStatusText(status.getReasonPhrase());
    erro.setMensagem(ex.getMessage());
    erro.setTimestamp(LocalDateTime.now());
    return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(erro);
  }
  
  @ExceptionHandler(StatusTarefaNotFound.class)
  public ResponseEntity<MensagemPadraoErro> handlerStatusTarefaNotFound(StatusTarefaNotFound ex, 
                                                                        HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;

    var erro = new MensagemPadraoErro();
    erro.setPath(request.getRequestURI());
    erro.setMethod(request.getMethod());
    erro.setStatus(status.value());
    erro.setStatusText(status.getReasonPhrase());
    erro.setMensagem(ex.getMessage());
    erro.setTimestamp(LocalDateTime.now());
    return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(erro);
  }
  
}
