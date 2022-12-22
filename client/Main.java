package main;

import java.net.*;
import java.util.Vector;

import trans.*;

import java.io.*;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
 
public class Main 
{
  public static void main (String [] args ) throws IOException, Exception
    { 
      Client client=new Client();
      Vector<String> configuration=client.configurer(client.lire(new File("configuration.txt")));
      Socket s=new Socket(configuration.get(0),Integer.parseInt(configuration.get(1)));
      client=new Client(s);

      InterfaceClient interfaceClient=new InterfaceClient(0,client);
    }
}