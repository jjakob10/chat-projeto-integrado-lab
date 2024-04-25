package com.ufes.pi.chat;

import java.time.LocalDateTime;

public record Mensagem(LocalDateTime horario, String remetente, String mensagem) {
  public Mensagem comHorario() {
    return new Mensagem(LocalDateTime.now(), this.remetente, this.mensagem);
  }

}