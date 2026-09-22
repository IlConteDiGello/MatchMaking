package com.mycompany.matchmaking;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

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
            
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            
            String risposta = in.readLine();
            
            System.out.println("Il client dice: " + risposta);
            
        }
        catch(Exception e)
        {
            System.out.println("Errore: "+e.getMessage());
        }
                
    }
    
}
