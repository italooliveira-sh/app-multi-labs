package br.com.italooliveira.app_multi_labs.exception;

public class StatusTarefaNotFound extends RuntimeException{
  
  public StatusTarefaNotFound(String mensagem) {
    super(mensagem);
  }
}
