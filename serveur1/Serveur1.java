package trans;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;
 
public class Serveur1{
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
    public Serveur1(Socket sock) {
        this.sock = sock;
    }

    public Serveur1() {
        
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
        // sock.close();
    }

    public void recuperer(String name) throws Exception
    {
        // System.out.println("nom recuperer:"+name);
        
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

    public Vector lire(File file)
     {
         Vector list=new Vector();
         try
        {
            int i=0;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String chaine=br.readLine();
            while (chaine!=null)
            {
                list.add(chaine);
                chaine=br.readLine();
            }
            br.close();

            } catch(IOException e)
            {
                e.printStackTrace();
            }
        return list;
     }

     public Vector configurer(Vector v)
     {
        String[] r1=((String)v.get(0)).split(":",-2);

        Vector result=new Vector();
        result.add(r1[1]);

        return result;
    }
}