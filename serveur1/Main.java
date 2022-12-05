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
            
        Serveur1 serveur;
        try
        {
            sv=new ServerSocket(61);
            s=sv.accept();
            serveur=new Serveur1(s);
            while(true)
            {
                DataInputStream dis=new DataInputStream(serveur.getSock().getInputStream());  
                String todo=(String)dis.readUTF();
                String fileName=(String)dis.readUTF();
                System.out.println("commande="+todo);
                System.out.println("fileName="+fileName);

                if(todo.equalsIgnoreCase("envoyer"))
                {
                    serveur.copyFile(fileName);
                }
            }
            // serveur.recuperer();
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();          
        }
    }
}