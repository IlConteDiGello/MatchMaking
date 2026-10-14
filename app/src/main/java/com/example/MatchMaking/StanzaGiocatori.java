/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.MatchMaking;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Consumer;

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
    private Consumer stanzaPiena;

    public int getLivello() {
        return livello;
    }

    public Giocatore[] getGiocatori() {
        return giocatori;
    }
    
    
    
    StanzaGiocatori(Giocatore capogruppo, int numeroMassimoGiocatori) {
        giocatori = new Giocatore[numeroMassimoGiocatori];
        giocatori[0] = capogruppo;
        this.numeroMassimoGiocatori = numeroMassimoGiocatori;
        livello = capogruppo.getLivello();
        numeroGiocatori = 1;
    }
    
    StanzaGiocatori(Giocatore capogruppo, int numeroMassimoGiocatori, int tolleranzaLivello, float percentualeSbilanciamentoMassimo, Consumer stanzaPiena) {
        giocatori = new Giocatore[numeroMassimoGiocatori];
        giocatori[0] = capogruppo;
        livello = capogruppo.getLivello();
        this.numeroMassimoGiocatori = numeroMassimoGiocatori;
        this.tolleranzaLivello = tolleranzaLivello;
        this.percentualeSbilanciamentoMassimo = percentualeSbilanciamentoMassimo;
        numeroGiocatori = 1;
        this.stanzaPiena = stanzaPiena;
    }
    
    private boolean puoEntrare(Giocatore nuovoGiocatore) {
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
    
    public boolean accetta(Giocatore nuovoGiocatore) {
        if (puoEntrare(nuovoGiocatore)) {
            giocatori[numeroGiocatori] = nuovoGiocatore;
            numeroGiocatori++;
            if (numeroGiocatori >= GlobalData.NUMERO_GIOCATORI_STANZA) {
                System.out.println("Stanza piena livello " + giocatori[0].getLivello());
                stanzaPiena.accept(this);
            }
            return true;
        } 
        else {
            return false;
        }
    }

    void iniziaPartita() {
        System.out.println("Avverto i giocatori");
        for(Giocatore g : giocatori) {
            g.avvertiClient(this);
        }
    }
    
}
