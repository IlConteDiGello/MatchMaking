/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.example.MatchMaking;

/**
 *
 * @author nano_machine
 */
public record ImpostazioniServer(
    int NUMERO_GIOCATORI_STANZA
    ) {

    public ImpostazioniServer(int NUMERO_GIOCATORI_STANZA) {
        this.NUMERO_GIOCATORI_STANZA = NUMERO_GIOCATORI_STANZA;
    }
    
}
