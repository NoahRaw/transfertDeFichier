package main;

import java.net.*;
import trans.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class Main{
  public static void main (String [] args ) throws IOException 
  {
        Socket s;
        ServerSocket sv;
            
        Serveur3 serveur;
        try
        {
            sv=new ServerSocket(63);
            s=sv.accept();
            serveur=new Serveur3(s);
            // serveur.copyFile("h1.mp3");
            // serveur.recuperer();
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();          
        }
    }
}