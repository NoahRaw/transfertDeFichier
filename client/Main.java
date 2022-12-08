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
    }
}