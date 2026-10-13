/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.MatchMaking;

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
        
        InterfacciaServer interfaccia = new InterfacciaServer();
        
        try
        {
            interfaccia.setVisible(true);
            interfaccia.aggiungiLog("Inizializzato server, aspettando client");
            ServerSocket serversocket = new ServerSocket(50000);
        
            PrintWriter out;
            BufferedReader in;
            
            Thread Main = Thread.currentThread();   //thread corrente
            MatchMaker matchmaker = new MatchMaker();   //creazione thread matchMaking
        
            //start thread del matchMaking
            matchmaker.start();
            
            System.out.println("Server avviato, aspettando client...");
            
            while(true){
                Socket socket = serversocket.accept();  //accettazione socket in ciclo infinito
                System.out.println("Connesse nuovo client! wow!");
                
                ClientHandler handler = new ClientHandler(socket, matchmaker, interfaccia); //creazione nuovo handler
                
                
            }
            /*socketAccettazione = new ServerSocket(50000);
            System.out.println("In attesa del client....");
            Socket s = socketAccettazione.accept(); // BLOCCANTE!!!
            
            System.out.println("Connesso:"+s.getInetAddress().toString());
            
            // Aggancio l'outputStream al mio PrintWriter
            out = new PrintWriter(s.getOutputStream(),true);
            out.println("Benvenuto client!");
            // Aggancio l'inputStream al mio BufferedReader*/
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}