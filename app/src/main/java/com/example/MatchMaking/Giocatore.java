/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.MatchMaking;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author nano_machine
 */
public class Giocatore implements Serializable{
    //deve essere serializzabile siccome deve essere mandata al server
    private static final long serialVersionUID = 1L;
    
    private int livello;
    private String nickname;
    private Socket connessione;
    
    public Giocatore(int livello, String nickname, Socket connessione){
        this.livello = livello;
        this.nickname = nickname;
        this.connessione = connessione;
    }
    
    //setter
    public void setLivello(int livello) {
        this.livello = livello; 
    }
    
    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public void setConnessione(Socket connessione) {
        this.connessione = connessione;
    }
    
    //getter
    public int getLivello() {
        return this.livello;
    }    
    
    public String getNickname(){
        return this.nickname;
    }

    public Socket getConnessione() {
        return connessione;
    }

    void avvertiClient(StanzaGiocatori sg) {
        try {
            PrintWriter out = new PrintWriter(connessione.getOutputStream(),true);
            out.println("Match trovato: ");
            for (Giocatore g : sg.getGiocatori()) {
                out.println(g.toString());
                System.out.println("Avvertito");
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
    
    @Override
    public String toString() {
        return "Client " + connessione.getInetAddress() + " nick: " + nickname + "  LV: " + livello;
    }
    
}
