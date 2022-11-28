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
            
        Serveur2 serveur;
        try
        {
            sv=new ServerSocket(62);
            s=sv.accept();
            serveur=new Serveur2(s);
            // serveur.copyFile("h1.mp3");
            //serveur.recuperer();
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();          
        }
    }
}