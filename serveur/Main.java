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
        Serveur serveur=null;
        ThreadActionServeur threadActionServeur=null;
        try
        {
            sv=new ServerSocket(60);
            s=sv.accept();
            System.out.println("connecte");
            serveur=new Serveur(s,s1,s2,s3);
            // serveur.recuperer();
            // serveur.sendFileServeurSecondaire("h1.mp3");
            // serveur=new Serveur(s);
            
            ObjectInputStream in=new ObjectInputStream(s.getInputStream());
            String todo=(String)in.readObject();
            String fileName=(String)in.readObject();
            System.out.println("commande="+todo);
            System.out.println("fileName="+fileName);

            if(todo.equalsIgnoreCase("envoyer"))
            {  
                // System.out.println("nanao");
                // FileOutputStream out =new FileOutputStream(new File("fichier.mp3"));
                // byte buf[] = new byte[1024];
                // int n;
                // int somme=0;
                // while((n=in.read(buf))!=-1){
                //     out.write(buf,0,n);
                //     somme=somme+n;
                //     System.out.print("somme="+somme);                                        
                // }
                // System.out.println("somme="+somme);
                // out.close();
                // System.out.println("fichier copier sur le serveur principal");           
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