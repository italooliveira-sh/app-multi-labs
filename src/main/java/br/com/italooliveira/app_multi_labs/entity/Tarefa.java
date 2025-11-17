package br.com.italooliveira.app_multi_labs.entity;

import java.time.LocalDateTime;

import br.com.italooliveira.app_multi_labs.entity.enuns.PrioridadeTarefa;
import br.com.italooliveira.app_multi_labs.entity.enuns.StatusTarefa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tarefa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String titulo;
  private String descricao;

  @Enumerated(EnumType.STRING)
  private StatusTarefa status;

  @Enumerated(EnumType.STRING)
  private PrioridadeTarefa prioridade;

  @Column(name = "criado_em", updatable = false)
  private LocalDateTime criadoEm;

  @Column(name = "atualizado_em")
  private LocalDateTime atualizadoEm;

  @PrePersist
  public void prePerist() {
    this.criadoEm = LocalDateTime.now();
  }

  @PreUpdate
  public void preUpdate() {
    this.atualizadoEm = LocalDateTime.now();
  }
}
