package br.com.italooliveira.app_multi_labs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.italooliveira.app_multi_labs.entity.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
  
}
