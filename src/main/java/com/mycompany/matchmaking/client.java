
/*
SERVER DI MATCH MAKING  
Il server dovrà essere in ascolto su una porta TCP nota ed accettare le richieste di match-making dei vari  client. Nei parametri di configurazione il server dovrà avere necessariamente:  
• numero di giocatori per gruppo (ngg)  
• differenza massima di livello tra i giocatori (dml)  
Quando un giocatore si connette al server, questi dovrà registrarlo in un pool di giocatori in attesa di essere  abbinati e metterlo in attesa.  
Non appena risulti possibile creare un gruppo di ngg giocatori, il server dovrà risvegliare i rispettivi client  comunicando loro la lista dei propri compagni.  
Tale lista dovrà contenere almeno queste informazioni (per ogni giocatore):  
• nickname del giocatore  
• indirizzo ip della suo client  
• livello del giocatore  
Nota: il server dovrà lavorare a ciclo continuo. Una volta avviato, solo l’amministratore potrà decidere la  sua terminazione.  

 */
package com.mycompany.matchmaking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class client {

    String nickName;
    int livello;

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