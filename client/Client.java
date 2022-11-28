package trans;

import java.net.*;
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
        FileInputStream inf=new FileInputStream(new File(fileName));
        ObjectOutputStream out=new ObjectOutputStream(getS().getOutputStream());               
        byte buf[] = new byte[1024];
        int n;            
        while((n=inf.read(buf))!=-1){
            out.write(buf,0,n);
        }
        inf.close();
        out.close();           
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
    }
}