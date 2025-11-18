package br.com.italooliveira.app_multi_labs.exception.handlerExceptions;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MensagemPadraoErro {
  
  private String path;
  private String Method;
  private Integer status;
  private String statusText;
  private String mensagem;
  private LocalDateTime timestamp;
}
