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
            
            ObjectInputStream dis=new ObjectInputStream(serveur.getSock().getInputStream());  
            String todo=(String)dis.readObject();
            String fileName=(String)(String)dis.readObject();
            System.out.println("commande="+todo);
            System.out.println("fileName="+fileName);
    
            if(todo.equalsIgnoreCase("envoyer"))
            {
                serveur.copyFile(fileName,dis);
            }
            else
            {
                serveur.recuperer(fileName);
            }
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();          
        }
    }
}