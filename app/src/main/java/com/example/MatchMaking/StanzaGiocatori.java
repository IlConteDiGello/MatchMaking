/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.MatchMaking;

/**
 *
 * @author nano_machine
 */
public class StanzaGiocatori {
    private Giocatore[] giocatori;
    private int tolleranzaLivello;
    private float percentualeSbilanciamentoMassimo;
    private int numeroGiocatori;
    private int numeroMassimoGiocatori;
    private int livello;
    
    StanzaGiocatori(Giocatore capogruppo, int numeroMassimoGiocatori) {
        giocatori = new Giocatore[numeroMassimoGiocatori];
        giocatori[0] = capogruppo;
        this.numeroMassimoGiocatori = numeroMassimoGiocatori;
        livello = capogruppo.getLivello();
        numeroGiocatori = 1;
    }
    
    StanzaGiocatori(Giocatore capogruppo, int numeroMassimoGiocatori, int tolleranzaLivello, float percentualeSbilanciamentoMassimo) {
        giocatori = new Giocatore[numeroMassimoGiocatori];
        giocatori[0] = capogruppo;
        livello = capogruppo.getLivello();
        this.numeroMassimoGiocatori = numeroMassimoGiocatori;
        this.tolleranzaLivello = tolleranzaLivello;
        this.percentualeSbilanciamentoMassimo = percentualeSbilanciamentoMassimo;
        numeroGiocatori = 1;
    }
    
    boolean puoEntrare(Giocatore nuovoGiocatore) {
        if(nuovoGiocatore.getLivello() >= giocatori[0].getLivello() - tolleranzaLivello && nuovoGiocatore.getLivello() <= giocatori[0].getLivello() + tolleranzaLivello) {
            float media = 0;
            for(int i = 0; i < numeroGiocatori; i++) 
                media += giocatori[i].getLivello();
            media += nuovoGiocatore.getLivello();
            media /= (numeroGiocatori + 1);
            
            float sbilanciamento = Math.abs(media - giocatori[0].getLivello()) / tolleranzaLivello;
            
            if(sbilanciamento <= percentualeSbilanciamentoMassimo) {
                return true;
            }
        }
        
        return false;
    }
    
    public void accetta(Giocatore nuovoGiocatore) throws GiocatoreNonPuoEntrare {
        if (puoEntrare(nuovoGiocatore)) {
            giocatori[numeroGiocatori] = nuovoGiocatore;
            numeroGiocatori++;
        }else {
            throw new GiocatoreNonPuoEntrare();
        }
    }
    
}
