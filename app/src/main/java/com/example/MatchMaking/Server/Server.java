/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.MatchMaking.Server;

/**
 *
 * @author ilcontedigello
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

/**
 *
 * @author lucagiusti
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ServerSocket socketAccettazione;
        
        PrintWriter out;
        BufferedReader in;
        
        try
        {
            socketAccettazione = new ServerSocket(50000);
            System.out.println("In attesa del client....");
            Socket s = socketAccettazione.accept(); // BLOCCANTE!!!
            
            System.out.println("Connesso:"+s.getInetAddress().toString());
            
            // Aggancio l'outputStream al mio PrintWriter
            out = new PrintWriter(s.getOutputStream(),true);
            out.println("Benvenuto client!");
            // Aggancio l'inputStream al mio BufferedReader
            
        }
        catch(Exception e)
        {
            System.out.println("Errore: "+e.getMessage());
        } 
    }
    
}