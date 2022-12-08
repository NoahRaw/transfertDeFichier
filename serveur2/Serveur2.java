package trans;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class Serveur2{
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
    public Serveur2(Socket sock) {
        this.sock = sock;
    }

    







    //fonctions
    public void copyFile(String fileName,ObjectInputStream in) throws Exception
    {
        FileOutputStream out =new FileOutputStream(new File("liste_fichier\\"+fileName));
        byte buf[] = new byte[1024];
        int n;
        while((n=in.read(buf))!=-1){
            out.write(buf,0,n);  
        }
        out.close();                    
    }

    public void recuperer(String name) throws Exception
    {
        System.out.println("nom recuperer:"+name);
        
        FileInputStream inf=new FileInputStream(new File("liste_fichier\\"+name));
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