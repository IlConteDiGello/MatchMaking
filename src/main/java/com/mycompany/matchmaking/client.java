
package com.mycompany.matchmaking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PrintWriter out;
        BufferedReader in;
        
        try 
        {
            System.out.println("Mi connetto...");
            Socket s = new Socket("127.0.0.1",50000);
            
            System.out.println("Connesso!");
            
            
             // Aggancio l'outputStream al mio PrintWriter
            out = new PrintWriter(s.getOutputStream(),true);
            
            // Aggancio l'inputStream al mio BufferedReader
            
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            
            // Leggo se il server mi ha mandato qualcosa
            
            String messaggio = in.readLine();
            
            System.out.println("Il server dice:"+messaggio);
            
            out.println("Ciao server!");
        } 
        
        catch (IOException ex)
        {
            System.out.println("Errore di connessione!");
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}