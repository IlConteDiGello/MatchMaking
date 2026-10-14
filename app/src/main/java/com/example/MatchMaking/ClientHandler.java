/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.MatchMaking;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.SwingUtilities;

/**
 *
 * @author ilcontedigello
 */
public class ClientHandler extends Thread {
    private MatchMaker matchmaker;
    private Logger logger;
    private ConcurrentLinkedQueue<Socket> coda;
    

    public ClientHandler(Logger logger) {
        this.logger = logger;
        this.coda = new ConcurrentLinkedQueue();
        this.matchmaker = new MatchMaker();
    }

    @Override
    public void run() {
        while(true) {
            Socket connessione = coda.poll();
            if(connessione != null) {
                try {
                    ObjectInputStream in = new ObjectInputStream(connessione.getInputStream());
                    Giocatore giocatoreRicevuto = (Giocatore) in.readObject();
                    giocatoreRicevuto.setConnessione(connessione);
                    //System.out.println("Ricevuto giocatore: " + giocatoreRicevuto.getNickname() + " (Livello: " + giocatoreRicevuto.getLivello() + ")");

                    String IPClient = connessione.getInetAddress().getHostAddress();
                    String nicknameClient = giocatoreRicevuto.getNickname();
                    int livello = giocatoreRicevuto.getLivello();

                    String messaggioLog = giocatoreRicevuto.toString();

                    if(logger != null){
                        logger.log(messaggioLog);
                    }

                    matchmaker.aggiungiGiocatoreInFila(giocatoreRicevuto);

                } catch (Exception e) {
                    System.err.println("errore del clienthandler: " + e.getMessage());
                }
            
            }
            else {
                try{
                    Thread.sleep(10);
                }catch(InterruptedException e){
                    System.out.println(e);
                }
            }
        }
    }
    
    public void gestisci(Socket connessioneInEntrata) {
        coda.add(connessioneInEntrata);
    }
}