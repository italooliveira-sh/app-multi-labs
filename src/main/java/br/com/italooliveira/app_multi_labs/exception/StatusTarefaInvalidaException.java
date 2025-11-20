package br.com.italooliveira.app_multi_labs.exception;

public class StatusTarefaInvalidaException extends RuntimeException{
  
  public StatusTarefaInvalidaException(String mensagem) {
    super(mensagem);
  }
}
