package main;

import java.net.*;
import trans.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class Main
{
    public static void main (String [] args ) throws IOException 
    {
        Socket s1=new Socket("localhost",61);
        Socket s2=new Socket("localhost",62);
        Socket s3=new Socket("localhost",63);
        Socket s;
        ServerSocket sv;
            
        Serveur serveur;
        try
        {
            sv=new ServerSocket(60);
            s=sv.accept();
            serveur=new Serveur(s,s1,s2,s3);
            // serveur.sendFileServeurSecondaire("h1.mp3");
            serveur.recuperer();
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();          
        }
    }
}