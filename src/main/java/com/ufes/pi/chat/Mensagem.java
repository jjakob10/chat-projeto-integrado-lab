package com.ufes.pi.chat;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Mensagem {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  private LocalDateTime horario;
  private String remetente;
  private String mensagem;

  
  public void print(){
    System.out.println(this.horario);
    System.out.println(this.remetente);
    System.out.println(this.mensagem);
  }

  public Mensagem(){

  }

  public Mensagem( String remetente, String mensagem){
      this.remetente = remetente;
      this.mensagem = mensagem;
  }

  public void comHorario(){
    this.horario = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public LocalDateTime getHorario() {
    return horario;
  }

  public String getRemetente() {
    return remetente;
  }

  public String getMensagem() {
    return mensagem;
  }


}