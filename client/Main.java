package main;

import java.net.*;

import trans.*;

import java.io.*;
 
public class Main 
{
  public static void main (String [] args ) throws IOException, Exception
    { 
      Socket s=new Socket("localhost",60);
      Client client=new Client(s);
      //client.sendFileServeurPrincipal("h1.mp3");   
      client.recuperer("h1.mp3");
      s.close(); 
    }
}