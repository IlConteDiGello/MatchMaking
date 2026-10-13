/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.MatchMaking;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author ilcontedigello
 */
public class ClientHandler extends Thread {
    private Socket socket;
    private MatchMaker matchmaker;
    private MainFrame interfacciaDelServer;

    public ClientHandler(Socket socket, MatchMaker matchmaker, MainFrame interfacciaDelServer) {
        this.socket = socket;
        this.matchmaker = matchmaker;
        this.interfacciaDelServer = interfacciaDelServer;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Giocatore giocatoreRicevuto = (Giocatore) in.readObject();

            System.out.println("Ricevuto giocatore: " + giocatoreRicevuto.getNickname() 
            + " (Livello: " + giocatoreRicevuto.getLivello() + ")");
            
            String IPClient = socket.getInetAddress().getHostAddress();
            String nicknameClient = giocatoreRicevuto.getNickname();
            int livello = giocatoreRicevuto.getLivello();
            
            String messaggioPerInterfaccia = "Client " + IPClient + "nick: " + nicknameClient + "LV: " + livello;
            
            if(interfacciaDelServer != null){
                interfacciaDelServer.aggiungiLog(messaggioPerInterfaccia);
            }

            matchmaker.aggiungiGiocatoreInFila(giocatoreRicevuto);

        } catch (Exception e) {
            System.err.println("errore del clienthandler: " + e.getMessage());
        }
    }
}