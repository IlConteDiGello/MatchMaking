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
public class SocketListener implements Runnable {

    /**
     * @param args the command line arguments
     */
    Logger logger;
    
    SocketListener(Logger logger) {
        this.logger = logger;
    }
    
    @Override
    public void run() { 
        try
        {
            System.out.println("Ciao");
            ServerSocket serversocket = new ServerSocket(50000);
        
            PrintWriter out;
            BufferedReader in;
            
            //Thread Main = Thread.currentThread();   //thread corrente
            Thread matchMaker = new Thread(new MatchMaker());
            matchMaker.start();
            
            ClientHandler clientHandler = new ClientHandler(logger);
            Thread clientHandlerThread = new Thread(clientHandler);
            clientHandler.start();
            
            Socket connessioneInEntrata;
            while(true) {
                connessioneInEntrata = serversocket.accept();
                clientHandler.gestisci(connessioneInEntrata);
                
            }
            
            
            //ClientHandler handler = new ClientHandler(socket, matchmaker, interfaccia); //creazione nuovo handler
               
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