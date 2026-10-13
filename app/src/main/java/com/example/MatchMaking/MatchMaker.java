/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.MatchMaking;

import java.util.ArrayList;
import java.util.List;

/**
 *questo thread prende i dati dal clientHandler e li smista nelle stanze
 * 
 * @author ilcontedigello
 */
public class MatchMaker extends Thread{
    private ArrayList<StanzaGiocatori> listaStanzeAttive;
    
    public MatchMaker(){
        this.listaStanzeAttive = new ArrayList();
    }
    
    //questo è il metodo chiamato da ClientHandler
    //è synchronized perchè potrebbe essere chiamato da più thread allo stesso momento
    public synchronized void aggiungiGiocatoreInFila(Giocatore giocatore){
        boolean inserito = false;
        
        //ricerca in tutte le stanze
        for(StanzaGiocatori stanza : listaStanzeAttive){
            if(stanza.puoEntrare(giocatore)){//usa il metodo dentro la stanza per vedere se può entrare
                try{
                    stanza.accetta(giocatore);  //se può entrare lo accetta
                    inserito = true;
                    System.out.println("Giocatore aggiunto! divertiti!");
                    break;
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        
        if(!inserito){
            StanzaGiocatori StanzaNuova = new StanzaGiocatori(giocatore, 2);
            listaStanzeAttive.add(StanzaNuova);
            System.out.println("Creata una nuova stanza siccome non ce ne erano di compatibili per " + giocatore.getNickname());
        
        }
    }
    
    @Override
    
    public void run(){
        try{
            while(true){
                Thread.sleep(1000);
            }
        }catch(Exception e){
            System.out.println("Thread interrotto");   
        }
    }
}
