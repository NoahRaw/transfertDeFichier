package trans;

import java.net.*;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.io.*;
 
public class Client {
    Socket s;
  
    public Client(Socket s) {
        this.s = s;
    }

    public Client() {
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
            String[] cut=fileName.split("\\\\",-2);             
            sortie.write(cut[cut.length-1]+"\n");
            sortie.close();
            System.out.println("fichier importe avec succes");
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        FileInputStream inf=new FileInputStream(new File(fileName));
        ObjectOutputStream out=new ObjectOutputStream(getS().getOutputStream());
        
        out.writeObject("envoyer");  
        String[] cut=fileName.split("\\\\",-2);             
        out.writeObject(cut[cut.length-1]);
        
        byte buf[] = new byte[1024];
        int n;            
        while((n=inf.read(buf))!=-1){
            out.write(buf,0,n);
        }
        inf.close();
        out.writeObject("fin");  
        // out.close();           
    }

    public void recuperer(String name) throws Exception
    {
        ObjectOutputStream dout=new ObjectOutputStream(getS().getOutputStream());  
        dout.writeObject("recuperer");  
        dout.writeObject(name);  
        // dout.flush();  
        // dout.close();  
        // s.close();

        ObjectInputStream in=new ObjectInputStream(getS().getInputStream());
        FileOutputStream out =new FileOutputStream(new File("fichier_recuperer\\"+name));
        byte buf[] = new byte[1024];
        int n;            
        while((n=in.read(buf))!=-1){
            out.write(buf,0,n);
        }
        System.out.println("fichier recuperer avec succes");
        // in.close();
        out.close();
        // dout.close();
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

     public Vector<String> configurer(Vector v)
     {
        String[] r1=((String)v.get(0)).split(":",-2);
        String[] r2=((String)v.get(1)).split(":",-2);

        Vector<String> result=new Vector<String>();
        result.add(r1[1]);
        result.add(r2[1]);

        return result;
    }
}