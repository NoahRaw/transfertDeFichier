package main;

import java.net.*;

import trans.*;

import java.io.*;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
 
public class Main 
{
  public static void main (String [] args ) throws IOException, Exception
    { 
      Socket s=new Socket("localhost",60);
      Client client=new Client(s);

      InterfaceClient interfaceClient=new InterfaceClient(0,client);

      // client.sendFileServeurPrincipal("h1.mp3");   
      // client.recuperer("h1.mp3");
      // client.test();

      // EnvoyerAction envoyerAction=new EnvoyerAction(0, client);
      // envoyerAction.start();
      // ThreadTransfererFenetre threadTransfererFenetre=new ThreadTransfererFenetre(envoyerAction, 1, client);
      // threadTransfererFenetre.start(); 
      // FichierEnregistre f=new FichierEnregistre();
      // InterfaceClient i=new InterfaceClient(0,client);
      // s.close();
    }
}