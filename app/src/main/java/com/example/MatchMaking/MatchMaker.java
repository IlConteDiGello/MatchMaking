/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.MatchMaking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.SwingUtilities;

/**
 *questo thread prende i dati dal clientHandler e li smista nelle stanze
 * 
 * @author ilcontedigello
 */
public class MatchMaker implements Runnable{
    //private ArrayList<StanzaGiocatori> listaStanzeAttive;
    private NavigableSet<StanzaGiocatori> stanzeAttive;
    
    public MatchMaker(){
        this.stanzeAttive = Collections.synchronizedNavigableSet(new TreeSet<StanzaGiocatori>(new Comparator<StanzaGiocatori>() {
            @Override
            public int compare(StanzaGiocatori t, StanzaGiocatori t1) {
                return t.getLivello() - t1.getLivello();
            }
        }));
    }
    
    
    //questo è il metodo chiamato da ClientHandler
    //è synchronized perchè potrebbe essere chiamato da più thread allo stesso momento
    public synchronized void aggiungiGiocatoreInFila(Giocatore giocatore){
        StanzaGiocatori stanzaTemp = new StanzaGiocatori(giocatore, GlobalData.NUMERO_GIOCATORI_STANZA);
        
        StanzaGiocatori stanzaVicina;
        
        stanzaVicina = stanzeAttive.ceiling(stanzaTemp);
        if (stanzaVicina.accetta(giocatore))
            return;
        
        stanzaVicina = stanzeAttive.lower(stanzaTemp);
        if (stanzaVicina.accetta(giocatore))
            return;
        
        stanzeAttive.add(stanzaTemp);
        System.out.println("Creata una nuova stanza siccome non ce ne erano di compatibili per " + giocatore.getNickname());
        
        
    }
    
    @Override 
    public void run(){
        try{
            while(true){
                Thread.sleep(1000);
            }
        }catch(Exception e){
            System.out.println("Thread interrotto"); 
            SwingUtilities.invokeLater(this);
        }
    }
}
