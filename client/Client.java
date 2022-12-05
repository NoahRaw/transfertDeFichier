package trans;

import java.net.*;
import java.util.concurrent.ExecutionException;
import java.io.*;
 
public class Client {
    Socket s;
  
    public Client(Socket s) {
        this.s = s;
    }

    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
    }

    public void sendFileServeurPrincipal(String fileName) throws Exception
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

        FileInputStream inf=new FileInputStream(new File(fileName));
        ObjectOutputStream out=new ObjectOutputStream(getS().getOutputStream());               
        byte buf[] = new byte[1024];
        int n;            
        while((n=inf.read(buf))!=-1){
            out.write(buf,0,n);
        }
        inf.close();
        // out.close();           
    }

    public void recuperer(String name) throws Exception
    {
        DataOutputStream dout=new DataOutputStream(getS().getOutputStream());  
        dout.writeUTF(name);  
        // dout.flush();  
        // dout.close();  
        // s.close();

        ObjectInputStream in=new ObjectInputStream(getS().getInputStream());
        FileOutputStream out =new FileOutputStream(new File("h3.mp3"));
        byte buf[] = new byte[1024];
        int n;            
        while((n=in.read(buf))!=-1){
            out.write(buf,0,n);
        }
        in.close();
        out.close();
        dout.close();
    }

    public void test() throws Exception
    {
        DataOutputStream dout=new DataOutputStream(getS().getOutputStream());  
        dout.writeUTF("h1.mp3");
        dout.writeUTF("envoyer");

        FileInputStream inf=new FileInputStream(new File("h1.mp3"));
        ObjectOutputStream out=new ObjectOutputStream(getS().getOutputStream());               
        byte buf[] = new byte[1024];
        int n;            
        while((n=inf.read(buf))!=-1){
            out.write(buf,0,n);
        }
        inf.close();
        out.close();
        dout.close();
    }
}