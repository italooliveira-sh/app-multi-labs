package br.com.italooliveira.app_multi_labs.exception.handlerExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.italooliveira.app_multi_labs.exception.PrioridadeTarefaInvalidaException;
import br.com.italooliveira.app_multi_labs.exception.StatusTarefaInvalidaException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalHandlerException {

  @ExceptionHandler(PrioridadeTarefaInvalidaException.class)
  public ResponseEntity<MensagemPadraoErro> handlerPrioridadeTarefaNotFound(PrioridadeTarefaInvalidaException ex,
                                                                            HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;

    var erro = new MensagemPadraoErro(request, status, ex.getMessage());
    return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(erro);
  }
  
  @ExceptionHandler(StatusTarefaInvalidaException.class)
  public ResponseEntity<MensagemPadraoErro> handlerStatusTarefaNotFound(StatusTarefaInvalidaException ex,
                                                                        HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;

    var erro = new MensagemPadraoErro(request, status, ex.getMessage());
    return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(erro);
  }
  
}
