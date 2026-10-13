/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.MatchMaking.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucagiusti
 */

public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        InterfacciaClient interfaccia = new InterfacciaClient();
        interfaccia.setVisible(true);
    }
    
}