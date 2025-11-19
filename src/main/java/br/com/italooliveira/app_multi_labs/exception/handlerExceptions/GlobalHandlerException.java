package br.com.italooliveira.app_multi_labs.exception.handlerExceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.italooliveira.app_multi_labs.exception.PrioridadeTarefaInvalida;
import br.com.italooliveira.app_multi_labs.exception.StatusTarefaInvalida;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalHandlerException {

  @ExceptionHandler(PrioridadeTarefaInvalida.class)
  public ResponseEntity<MensagemPadraoErro> handlerPrioridadeTarefaNotFound(PrioridadeTarefaInvalida ex,
                                                                            HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;

    var erro = new MensagemPadraoErro(request, status, ex.getMessage());
    return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(erro);
  }
  
  @ExceptionHandler(StatusTarefaInvalida.class)
  public ResponseEntity<MensagemPadraoErro> handlerStatusTarefaNotFound(StatusTarefaInvalida ex,
                                                                        HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;

    var erro = new MensagemPadraoErro(request, status, ex.getMessage());
    return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(erro);
  }
  
}
