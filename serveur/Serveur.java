package trans;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;
 
public class Serveur{
    //attribut
    Socket sock;
    Socket s1;
    Socket s2;
    Socket s3;
    
    
    
    
    
    
    
    
    
    
    //getters and setters
    public Socket getSock() {
        return sock;
    }

    public void setSock(Socket sock) {
        this.sock = sock;
    }

    public Socket getS1() {
        return s1;
    }

    public void setS1(Socket s1) {
        this.s1 = s1;
    }

    public Socket getS2() {
        return s2;
    }

    public void setS2(Socket s2) {
        this.s2 = s2;
    }

    public Socket getS3() {
        return s3;
    }

    public void setS3(Socket s3) {
        this.s3 = s3;
    }






    
    //constructors
    public Serveur(Socket sock, Socket s1, Socket s2, Socket s3) {
        this.sock = sock;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    public Serveur(Socket sock) {
        this.sock = sock;
    }

    public Serveur() {
        
    }

    







    //fonctions
    public void sendFileServeurSecondaire2(String fileName) throws Exception
    {
        try 
        {
            BufferedWriter sortie = new BufferedWriter(new FileWriter("liste.txt", true));
            sortie.write(fileName+"\n");
            sortie.close();
            System.out.println("fichier importer avec succes");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        ObjectInputStream in=new ObjectInputStream(getSock().getInputStream());
        FileOutputStream out =new FileOutputStream(new File("fichier"));
        byte buf[] = new byte[1024];
        int n;
        int somme=0;
        System.out.println(in.available());
        while((n=in.read(buf))!=-1){
            out.write(buf,0,n);
            somme=somme+n;                                        
        }
        // System.out.println("somme="+somme);
        out.close();
    }

    public void sendFileServeurSecondaire(String fileName,String todo,ObjectInputStream in) throws Exception
    {
        try 
        {
            BufferedWriter sortie = new BufferedWriter(new FileWriter("liste.txt", true));
            sortie.write(fileName+"\n");
            sortie.close();
            System.out.println("fichier importer avec succes");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        FileOutputStream out =new FileOutputStream(new File("fichier"));
        byte buf[] = new byte[1024];
        int n;
        int somme=0;
        // System.out.println(in.available());
        while((n=in.read(buf))!=-1){
            out.write(buf,0,n);
            somme=somme+n;                                        
        }
        // System.out.println("somme="+somme);
        out.close();                    

        FileInputStream inf=new FileInputStream(new File("fichier"));
        ObjectOutputStream out2=new ObjectOutputStream(getS1().getOutputStream()); 
        
        out2.writeObject(todo);
        out2.writeObject(fileName);
        
        buf = new byte[1024];
        int somme2=0;            
        while((somme2<somme/3)){
            n=inf.read(buf);
            somme2=somme2+n;
            out2.write(buf,0,n);
        }
        // System.out.println("somme2="+somme2);
        out2.writeObject("fin");

        out2=new ObjectOutputStream(getS2().getOutputStream());
        
        out2.writeObject(todo);
        out2.writeObject(fileName);
        
        somme2=0;            
        while(somme2<somme/3){
            n=inf.read(buf);
            somme2=somme2+n;
            out2.write(buf,0,n);
        }
        System.out.println("somme2="+somme2);
        out2.writeObject("fin");

        out2=new ObjectOutputStream(getS3().getOutputStream());               
        
        out2.writeObject(todo);
        out2.writeObject(fileName);
        
        somme2=0;            
        while((n=inf.read(buf))!=-1){
            somme2=somme2+n;
            out2.write(buf,0,n);
        }
        // System.out.println("somme2="+somme2);
        out2.writeObject("fin");

        inf.close();
    }

    public void recuperer(String name,String todo) throws Exception
    {
        System.out.println("nom du fichier a recuperer recu:"+name);

        ObjectOutputStream dout=new ObjectOutputStream(getS1().getOutputStream());  
        dout.writeObject(todo);    
        dout.writeObject(name);    
        // System.out.println("nom du fichier a recuperer envoye au serveur secondaire 1:"+name);

        ObjectOutputStream out=new ObjectOutputStream(getSock().getOutputStream());
        
        ObjectInputStream in=new ObjectInputStream(getS1().getInputStream());
        byte buf[] = new byte[1024];
        int n;            
        while((n=in.read(buf))!=-1){
            out.write(buf,0,n);
        }
        // in.close();
        System.out.println("reccuperer pour le serveur 1");
        

        ObjectOutputStream dout1=new ObjectOutputStream(getS2().getOutputStream());  
        dout1.writeObject(todo);  
        dout1.writeObject(name);
        // System.out.println("nom du fichier a recuperer envoye au serveur secondaire 2:"+name);  
        in=new ObjectInputStream(getS2().getInputStream()); 
        while((n=in.read(buf))!=-1){
            out.write(buf,0,n);
        }
        // in.close();
        System.out.println("reccuperer pour le serveur 2");

        ObjectOutputStream dout2=new ObjectOutputStream(getS3().getOutputStream());  
        dout2.writeObject(todo);  
        dout2.writeObject(name);  
        // System.out.println("nom du fichier a recuperer envoye au serveur secondaire 3:"+name);  
        in=new ObjectInputStream(getS3().getInputStream());
        while((n=in.read(buf))!=-1){
            out.write(buf,0,n);
        }
        out.writeObject("fin");
        // in.close();
        System.out.println("reccuperer pour le serveur 3");
        // out.close();
        System.out.println("fichier envoyer au client:"+name);
    }

    public void test() throws Exception
    {
        DataInputStream dis=new DataInputStream(getSock().getInputStream());  
        String  name=(String)dis.readUTF();
        String  commande=(String)dis.readUTF();
        //String name="h1.mp3";
        // System.out.println("nom du fichier a recuperer recu:"+name);
        // System.out.println("nom du fichier a recuperer recu:"+commande);

        ObjectInputStream in=new ObjectInputStream(getSock().getInputStream());
        FileOutputStream out =new FileOutputStream(new File("h3.mp3"));
        byte buf[] = new byte[1024];
        int n;
        int somme=0;
        // System.out.println(in.available());
        while((n=in.read(buf))!=-1){
            out.write(buf,0,n);
            somme=somme+n;                                        
        }
        // System.out.println("somme="+somme);
        out.close();                    
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
        
        String[] r2=((String)v.get(1)).split(":",-2);
        String[] r22=r2[1].split(",",-2);
        
        String[] r3=((String)v.get(2)).split(":",-2);
        String[] r33=r3[1].split(",",-2);

        String[] r4=((String)v.get(3)).split(":",-2);
        String[] r44=r4[1].split(",",-2);

        Vector result=new Vector();
        result.add(r22);
        result.add(r33);
        result.add(r44);
        result.add(r1[1]);

        return result;
    }
}