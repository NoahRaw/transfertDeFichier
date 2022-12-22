package main;

import java.net.*;
import trans.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;
 
public class Main
{
    public static void main (String [] args ) throws IOException 
    {
        Serveur serveur=new Serveur();
        Vector configuration=serveur.configurer(serveur.lire(new File("configuration.txt")));
        String[] cs1=(String[])configuration.get(0);
        String[] cs2=(String[])configuration.get(1);
        String[] cs3=(String[])configuration.get(2);
        Socket s1=new Socket(cs1[0],Integer.parseInt(cs1[1]));
        Socket s2=new Socket(cs2[0],Integer.parseInt(cs2[1]));
        Socket s3=new Socket(cs3[0],Integer.parseInt(cs3[1]));
        Socket s;
        ServerSocket sv;
        ThreadActionServeur threadActionServeur=null;
        try
        {
            sv=new ServerSocket(Integer.parseInt((String)configuration.get(3)));
            s=sv.accept();
            System.out.println("connecte");
            serveur=new Serveur(s,s1,s2,s3);
            
            ObjectInputStream in=new ObjectInputStream(s.getInputStream());
            String todo=(String)in.readObject();
            String fileName=(String)in.readObject();
            // System.out.println("commande="+todo);
            // System.out.println("fileName="+fileName);

            if(todo.equalsIgnoreCase("envoyer"))
            {             
                serveur.sendFileServeurSecondaire(fileName,todo,in);
            }
            else
            {
                serveur.recuperer(fileName,todo);
            }
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();          
        }
        // s.close();
    }
}