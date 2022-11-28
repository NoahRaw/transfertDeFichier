package trans;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class Serveur3{
    //attribut
    Socket sock;
    
    
    
    
    
    
    
    
    






    //getters and setters
    public Socket getSock() {
        return sock;
    }
    public void setSock(Socket sock) {
        this.sock = sock;
    }






    
    //constructors
    public Serveur3(Socket sock) {
        this.sock = sock;
    }

    







    //fonctions
    public void copyFile(String fileName) throws Exception
    {
        ObjectInputStream in=new ObjectInputStream(getSock().getInputStream());
        FileOutputStream out =new FileOutputStream(new File(fileName));
        byte buf[] = new byte[1024];
        int n;
        while((n=in.read(buf))!=-1){
            out.write(buf,0,n);  
        }
        out.close();                    
        sock.close();
    }

    public void recuperer() throws Exception
    {
        DataInputStream dis=new DataInputStream(getSock().getInputStream());  
        String  name=(String)dis.readUTF();
        //String name="h1.mp3";
        System.out.println("nom recuperer:"+name);
        
        FileInputStream inf=new FileInputStream(new File(name));
        ObjectOutputStream out=new ObjectOutputStream(getSock().getOutputStream());               
        byte buf[] = new byte[1024];
        int n;            
        while((n=inf.read(buf))!=-1){
            out.write(buf,0,n);
        }
        inf.close();
        out.close();
        System.out.println("fichier a recuperer envoye:"+name);
    }
}