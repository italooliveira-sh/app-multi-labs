package br.com.italooliveira.app_multi_labs.exception.handlerExceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Data
public class MensagemPadraoErro {
  
  private String path;
  private String method;
  private Integer status;
  private String statusText;
  private String mensagem;
  private LocalDateTime timestamp;

  public MensagemPadraoErro(HttpServletRequest request, HttpStatus status, String mensagem) {

    this.path = request.getRequestURI();
    this.method = request.getMethod();
    this.status = status.value();
    this.statusText = status.getReasonPhrase();
    this.mensagem = mensagem;
    this.timestamp = LocalDateTime.now();
  }
}
