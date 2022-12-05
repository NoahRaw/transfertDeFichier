package trans;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
 
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

    







    //fonctions
    public void sendFileServeurSecondaire2(String fileName) throws Exception
    {
        try 
        {
            BufferedWriter sortie = new BufferedWriter(new FileWriter("liste.txt", true));
            sortie.write(fileName+"\n");
            sortie.close();
            System.out.println("Le texte a été écrit avec succès");
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
        System.out.println("somme="+somme);
        out.close();
    }

    public void sendFileServeurSecondaire(String fileName) throws Exception
    {
        try 
        {
            BufferedWriter sortie = new BufferedWriter(new FileWriter("liste.txt", true));
            sortie.write(fileName+"\n");
            sortie.close();
            System.out.println("Le texte a été écrit avec succès");
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
        System.out.println("somme="+somme);
        out.close();                    

        FileInputStream inf=new FileInputStream(new File("fichier"));
        ObjectOutputStream out2=new ObjectOutputStream(getS1().getOutputStream());               
        buf = new byte[1024];
        int somme2=0;            
        while((somme2<somme/3)){
            n=inf.read(buf);
            somme2=somme2+n;
            out2.write(buf,0,n);
        }
        System.out.println("somme2="+somme2);
        out.close();

        out2=new ObjectOutputStream(getS2().getOutputStream());               
        somme2=0;            
        while(somme2<somme/3){
            n=inf.read(buf);
            somme2=somme2+n;
            out2.write(buf,0,n);
        }
        System.out.println("somme2="+somme2);
        out.close();

        out2=new ObjectOutputStream(getS3().getOutputStream());               
        somme2=0;            
        while((n=inf.read(buf))!=-1){
            somme2=somme2+n;
            out2.write(buf,0,n);
        }
        System.out.println("somme2="+somme2);
        out2.close();
        inf.close();
    }

    public void recuperer() throws Exception
    {
        DataInputStream dis=new DataInputStream(getSock().getInputStream());  
        String  name=(String)dis.readUTF();
        //String name="h1.mp3";
        System.out.println("nom du fichier a recuperer recu:"+name);

        DataOutputStream dout=new DataOutputStream(getS1().getOutputStream());  
        dout.writeUTF(name);  
        // dout.flush();  
        // dout.close();  
        System.out.println("nom du fichier a recuperer envoye au serveur secondaire:"+name);

        ObjectInputStream in=new ObjectInputStream(getS1().getInputStream());
        ObjectOutputStream out=new ObjectOutputStream(getSock().getOutputStream());
        byte buf[] = new byte[1024];
        int n;            
        while((n=in.read(buf))!=-1){
            out.write(buf,0,n);
        }
        in.close();
        System.out.println("s1 reccuperer");
        

        DataOutputStream dout1=new DataOutputStream(getS2().getOutputStream());  
        dout1.writeUTF(name);  
        in=new ObjectInputStream(getS2().getInputStream()); 
        while((n=in.read(buf))!=-1){
            out.write(buf,0,n);
        }
        in.close();
        System.out.println("s2 reccuperer");

        DataOutputStream dout2=new DataOutputStream(getS3().getOutputStream());  
        dout2.writeUTF(name);  
        in=new ObjectInputStream(getS3().getInputStream());
        while((n=in.read(buf))!=-1){
            out.write(buf,0,n);
        }
        in.close();
        System.out.println("s3 reccuperer");
        out.close();
        System.out.println("fichier envoyer au client:"+name);
    }

    public void test() throws Exception
    {
        DataInputStream dis=new DataInputStream(getSock().getInputStream());  
        String  name=(String)dis.readUTF();
        String  commande=(String)dis.readUTF();
        //String name="h1.mp3";
        System.out.println("nom du fichier a recuperer recu:"+name);
        System.out.println("nom du fichier a recuperer recu:"+commande);

        ObjectInputStream in=new ObjectInputStream(getSock().getInputStream());
        FileOutputStream out =new FileOutputStream(new File("h3.mp3"));
        byte buf[] = new byte[1024];
        int n;
        int somme=0;
        System.out.println(in.available());
        while((n=in.read(buf))!=-1){
            out.write(buf,0,n);
            somme=somme+n;                                        
        }
        System.out.println("somme="+somme);
        out.close();                    
    }
}