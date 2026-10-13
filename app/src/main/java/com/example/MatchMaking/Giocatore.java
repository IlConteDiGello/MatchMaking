/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.MatchMaking;
import java.io.Serializable;

/**
 *
 * @author nano_machine
 */
public class Giocatore implements Serializable{
    //deve essere serializzabile siccome deve essere mandata al server
    private static final long serialVersionUID = 1L;
    
    private int livello;
    private String nickname;
    
    public Giocatore(int livello, String nickname){
        this.livello = livello;
        this.nickname = nickname;
    }
    
    //setter
    public void setLivello(int livello) {
        this.livello = livello; 
    }
    
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    
    //getter
    public int getLivello() {
        return this.livello;
    }    
    
    public String getNickname(){
        return this.nickname;
    }
}
